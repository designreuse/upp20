package org.ftn.upp.lass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiEndpoints;
import org.ftn.upp.lass.common.api.RestApiRequestParameters;
import org.ftn.upp.lass.dto.request.FormSubmissionRequest;
import org.ftn.upp.lass.dto.response.TaskInfoResponse;
import org.ftn.upp.lass.service.FormManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(RestApiEndpoints.FORM_MANAGEMENT)
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FormManagementController {

    private final FormManagementService formManagementService;

    @PostMapping(RestApiEndpoints.SUBMIT_FORM)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TaskInfoResponse submitForm(@RequestBody @Valid FormSubmissionRequest formSubmissionRequest,
                                       @RequestParam(RestApiRequestParameters.FORM_SUBMISSION_TYPE) @NotBlank String formSubmissionType) {
        log.info(LogMessages.SUBMITTING_FORM, formSubmissionRequest.getTaskId());
        final var processInstanceId = this.formManagementService.submitForm(formSubmissionRequest, this.resolveFormSubmissionType(formSubmissionType));
        log.info(LogMessages.FORM_SUBMITTED, formSubmissionRequest.getTaskId(), processInstanceId);

        return TaskInfoResponse.builder()
                .processInstanceId(processInstanceId)
                .taskId(formSubmissionRequest.getTaskId())
                .build();
    }

    @PostMapping(RestApiEndpoints.SUBMIT_FORM_WITHOUT_ASSIGNEE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TaskInfoResponse submitFormWithoutAssignee(@RequestBody @Valid FormSubmissionRequest formSubmissionRequest,
                                                      @RequestParam(RestApiRequestParameters.FORM_SUBMISSION_TYPE) @NotBlank String formSubmissionType) {
        log.info(LogMessages.SUBMITTING_FORM, formSubmissionRequest.getTaskId());
        final var processInstanceId = this.formManagementService.submitFormWithoutAssignee(formSubmissionRequest, this.resolveFormSubmissionType(formSubmissionType));
        log.info(LogMessages.FORM_SUBMITTED, formSubmissionRequest.getTaskId(), processInstanceId);

        return TaskInfoResponse.builder()
                .processInstanceId(processInstanceId)
                .taskId(formSubmissionRequest.getTaskId())
                .build();
    }

    private String resolveFormSubmissionType(String formSubmissionType) {
        switch (formSubmissionType) {
            case Constants.FormDataVariables.USER_DATA_FORM:
            case Constants.FormDataVariables.BETA_ACCESS_GENRES_FORM:
                return formSubmissionType;
            default:
                return Constants.FormDataVariables.GENERIC_FORM_DATA;
        }
    }
}
