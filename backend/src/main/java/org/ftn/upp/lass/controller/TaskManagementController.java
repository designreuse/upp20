package org.ftn.upp.lass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiEndpoints;
import org.ftn.upp.lass.common.api.RestApiRequestParameters;
import org.ftn.upp.lass.dto.response.FormFieldsResponse;
import org.ftn.upp.lass.dto.response.TaskInfoResponse;
import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.BadRequestResponseCode;
import org.ftn.upp.lass.util.ErrorMessageUtil;
import org.ftn.upp.lass.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(RestApiEndpoints.TASK_MANAGEMENT)
@RequiredArgsConstructor
@Slf4j
public class TaskManagementController {

    private final TaskService taskService;
    private final FormService formService;

    @GetMapping(RestApiEndpoints.CLAIM_TASK)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TaskInfoResponse claimTask(@RequestParam(RestApiRequestParameters.TASK_ID) @NotBlank String taskId) {
        log.info(LogMessages.ASSIGNING_TASK_TO_CURRENT_USER, taskId);

        final var taskToBeClaimed = this.getTask(taskId);
        // TODO (fivkovic): Assign task to the currently active user.
        this.taskService.saveTask(taskToBeClaimed);

        log.info(LogMessages.ASSIGNED_TASK_TO_CURRENT_USER, taskId, "USER_ID");
        return TaskInfoResponse.builder()
                .taskId(taskToBeClaimed.getId())
                .build();
    }

    @GetMapping(RestApiEndpoints.CURRENTLY_ACTIVE_TASK_FORM)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public FormFieldsResponse getCurrentlyActiveTaskForm(@RequestParam(RestApiRequestParameters.PROCESS_INSTANCE_ID) @NotBlank String processInstanceId) {
        log.info(LogMessages.RETRIEVEING_CURRENTLY_ACTIVE_TASK_FORM, processInstanceId);

        final Task currentlyActiveTask;
        try {
            currentlyActiveTask = this.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        } catch (ProcessEngineException e) {
            throw new BadRequestException(BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.activeTaskForProcessInstanceNotFound(processInstanceId));
        }
        ExceptionUtils.throwBadRequestExceptionIf(
                currentlyActiveTask == null,
                BadRequestResponseCode.INVALID_REQUEST_DATA,
                ErrorMessageUtil.activeTaskForProcessInstanceNotFound(processInstanceId));

        final var formFields = this.formService.getTaskFormData(currentlyActiveTask.getId()).getFormFields();

        log.info(LogMessages.RETRIEVED_CURRENTLY_ACTIVE_TASK_FORM, currentlyActiveTask.getId(), processInstanceId);
        return FormFieldsResponse.builder()
                .processInstanceId(processInstanceId)
                .taskId(currentlyActiveTask.getId())
                .formFields(formFields)
                .build();
    }

    @GetMapping(RestApiEndpoints.TASK_FORM)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public FormFieldsResponse getTaskForm(@RequestParam(RestApiRequestParameters.TASK_ID) @NotBlank String taskId) {
        log.info(LogMessages.RETRIEVEING_TASK_FORM, taskId);

        final var requestedTask = this.getTask(taskId);
        final var formFields = this.formService.getTaskFormData(taskId).getFormFields();

        log.info(LogMessages.RETRIEVED_TASK_FORM, taskId, requestedTask.getProcessInstanceId());
        return FormFieldsResponse.builder()
                .processInstanceId(requestedTask.getProcessInstanceId())
                .taskId(requestedTask.getId())
                .formFields(formFields)
                .build();
    }

    private Task getTask(@NotBlank String taskId) {
        final Task requestedTask;
        try {
            requestedTask = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        } catch (ProcessEngineException e) {
            throw new BadRequestException(BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.taskNotFound(taskId));
        }
        ExceptionUtils.throwBadRequestExceptionIf(requestedTask == null, BadRequestResponseCode.INVALID_REQUEST_DATA, ErrorMessageUtil.taskNotFound(taskId));

        return requestedTask;
    }
}
