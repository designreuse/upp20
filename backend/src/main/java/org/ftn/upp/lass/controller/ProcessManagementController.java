package org.ftn.upp.lass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.exception.NullValueException;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiEndpoints;
import org.ftn.upp.lass.common.api.RestApiRequestParameters;
import org.ftn.upp.lass.dto.response.ProcessInfoResponse;
import org.ftn.upp.lass.exception.NotFoundException;
import org.ftn.upp.lass.util.ErrorMessageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestApiEndpoints.PROCESS_MANAGEMENT)
@RequiredArgsConstructor
@Slf4j
public class ProcessManagementController {

    private final RuntimeService runtimeService;

    @GetMapping(RestApiEndpoints.START_PROCESS_INSTANCE)
    public ResponseEntity<ProcessInfoResponse> startProcessInstance(@RequestParam(RestApiRequestParameters.PROCESS_NAME) String processName) {
        log.info(LogMessages.STARTING_PROCESS, processName);

        final ProcessInstance processInstance;
        try {
            processInstance = this.runtimeService.startProcessInstanceByKey(processName);
        } catch (NullValueException e) {
            throw new NotFoundException(ErrorMessageUtil.processWithGivenNameNotFound(processName));
        }

        var responseBody = ProcessInfoResponse.builder()
                .processInstanceId(processInstance.getId())
                .build();

        log.info(LogMessages.PROCESS_STARTED, processInstance.getId(), processName);
        return ResponseEntity.ok(responseBody);
    }
}
