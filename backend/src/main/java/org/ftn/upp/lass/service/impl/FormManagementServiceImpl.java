package org.ftn.upp.lass.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.*;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.dto.request.FormSubmissionField;
import org.ftn.upp.lass.dto.request.FormSubmissionRequest;
import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.BadRequestResponseCode;
import org.ftn.upp.lass.security.JwtTokenDetailsUtil;
import org.ftn.upp.lass.service.FormManagementService;
import org.ftn.upp.lass.util.ErrorMessageUtil;
import org.ftn.upp.lass.util.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormManagementServiceImpl implements FormManagementService {

    private final TaskService taskService;
    private final FormService formService;
    private final RuntimeService runtimeService;

    @Override
    public String submitForm(FormSubmissionRequest formSubmissionRequest) throws BadRequestException {
        final var task = this.taskService.createTaskQuery().taskId(formSubmissionRequest.getTaskId()).singleResult();
        ExceptionUtils.throwBadRequestExceptionIf(task.isSuspended(), BadRequestResponseCode.INVALID_DATA, ErrorMessageUtil.taskIsNotActive(task.getId()));
        ExceptionUtils.throwBadRequestExceptionIf(task.getAssignee() == null, BadRequestResponseCode.INVALID_DATA, ErrorMessageUtil.taskIsNotAssigned(task.getId()));
        ExceptionUtils.throwInsufficientPrivilegesExceptionIf(!task.getAssignee().equals(JwtTokenDetailsUtil.getCurrentUserUsername()));

        final var processInstanceId = task.getProcessInstanceId();
        try {
            final Map<String, Object> formFieldsMap = formSubmissionRequest.getFormSubmissionFields().stream()
                    .collect(Collectors.toMap(FormSubmissionField::getId, FormSubmissionField::getValue));

            this.runtimeService.setVariable(processInstanceId, Constants.ProcessVariables.FORM_DATA, formSubmissionRequest.getFormSubmissionFields());
            this.formService.submitTaskForm(formSubmissionRequest.getTaskId(), formFieldsMap);
        } catch (ProcessEngineException e) {
            ExceptionUtils.throwBadRequestException(BadRequestResponseCode.INVALID_DATA, e.getMessage());
        }

        return processInstanceId;
    }
}
