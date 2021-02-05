package org.ftn.upp.lass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiEndpoints;
import org.ftn.upp.lass.common.api.RestApiRequestParameters;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(RestApiEndpoints.PAYMENT)
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final RuntimeService runtimeService;

    @PostMapping(RestApiEndpoints.PAY_MEMBERSHIP_MOCK)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void payMembershipMock(@RequestParam(RestApiRequestParameters.PROCESS_INSTANCE_ID) @NotBlank String processInstanceId) {
        log.info(LogMessages.PROCESSING_MEMBERSHIP_PAYMENT, processInstanceId);

        this.runtimeService.createMessageCorrelation(Constants.ProcessConstants.MEMBERSHIP_PAID_MESSAGE)
                .processInstanceId(processInstanceId)
                .correlateWithResult();

        log.info(LogMessages.PROCESSED_MEMBERSHIP_PAYMENT, processInstanceId);
    }
}
