package org.ftn.upp.lass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiEndpoints;
import org.ftn.upp.lass.dto.request.FormSubmissionRequest;
import org.ftn.upp.lass.dto.response.TaskInfoResponse;
import org.ftn.upp.lass.service.FormManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestApiEndpoints.FORM_MANAGEMENT)
@RequiredArgsConstructor
@Slf4j
public class FormManagementController {

    private final FormManagementService formManagementService;

    @PostMapping(RestApiEndpoints.SUBMIT_FORM)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public TaskInfoResponse submitForm(@RequestBody FormSubmissionRequest formSubmissionRequest) {
        log.info(LogMessages.SUBMITTING_FORM, formSubmissionRequest.getTaskId());
        var processInstanceId = this.formManagementService.submitForm(formSubmissionRequest);
        log.info(LogMessages.FORM_SUBMITTED, formSubmissionRequest.getTaskId(), processInstanceId);

        return TaskInfoResponse.builder()
                .processInstanceId(processInstanceId)
                .taskId(formSubmissionRequest.getTaskId())
                .build();
    }
}
