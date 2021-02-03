package org.ftn.upp.lass.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidatorException;
import org.camunda.bpm.engine.task.Task;
import org.ftn.upp.lass.dto.request.FormSubmissionRequest;
import org.ftn.upp.lass.exception.BadRequestResponseCode;
import org.ftn.upp.lass.security.JwtTokenDetailsUtil;
import org.ftn.upp.lass.service.FormManagementService;
import org.ftn.upp.lass.util.ErrorMessageUtil;
import org.ftn.upp.lass.util.ExceptionUtils;
import org.ftn.upp.lass.util.FormSubmissionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FormManagementServiceImpl implements FormManagementService {

    private final TaskService taskService;
    private final FormService formService;
    private final RuntimeService runtimeService;

    @Override
    public String submitForm(FormSubmissionRequest formSubmissionRequest, @NotBlank String targetProcessVariable) {
        final var task = this.taskService.createTaskQuery().taskId(formSubmissionRequest.getTaskId()).singleResult();
        ExceptionUtils.throwBadRequestExceptionIf(task == null || task.isSuspended(), BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.taskIsNotActive(formSubmissionRequest.getTaskId()));
        ExceptionUtils.throwBadRequestExceptionIf(task.getAssignee() == null, BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.taskIsNotAssigned(task.getId()));
        ExceptionUtils.throwInsufficientPrivilegesExceptionIf(!task.getAssignee().equals(JwtTokenDetailsUtil.getCurrentUserUsername()));

        return this.submitForm(formSubmissionRequest, targetProcessVariable, task);
    }

    @Override
    public String submitFormWithoutAssignee(FormSubmissionRequest formSubmissionRequest, @NotBlank String targetProcessVariable) {
        final var task = this.taskService.createTaskQuery().taskId(formSubmissionRequest.getTaskId()).singleResult();
        ExceptionUtils.throwBadRequestExceptionIf(task == null || task.isSuspended(), BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.taskIsNotActive(formSubmissionRequest.getTaskId()));
        ExceptionUtils.throwBadRequestExceptionIf(task.getAssignee() == null && !JwtTokenDetailsUtil.isAnonymousUserAuthenticationToken(), BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.taskIsNotAssigned(formSubmissionRequest.getTaskId()));

        return this.submitForm(formSubmissionRequest, targetProcessVariable, task);
    }

    private String submitForm(FormSubmissionRequest formSubmissionRequest, String targetProcessVariable, Task task) {
        final var processInstanceId = task.getProcessInstanceId();
        try {
            this.runtimeService.setVariable(processInstanceId, targetProcessVariable, formSubmissionRequest.getFormSubmissionFields());
            this.formService.submitTaskForm(formSubmissionRequest.getTaskId(), FormSubmissionUtils.convertFormSubmissionFields(formSubmissionRequest.getFormSubmissionFields()));
        } catch (ProcessEngineException e) {
            ExceptionUtils.throwBadRequestException(BadRequestResponseCode.INVALID_REQUEST_DATA, e.getMessage());
        }

        return processInstanceId;
    }
}
