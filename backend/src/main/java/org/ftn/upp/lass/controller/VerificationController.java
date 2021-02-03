package org.ftn.upp.lass.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.ftn.upp.lass.common.Constants;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiEndpoints;
import org.ftn.upp.lass.common.api.RestApiRequestParameters;
import org.ftn.upp.lass.service.VerificationCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(RestApiEndpoints.USER)
@RequiredArgsConstructor
@Slf4j
public class VerificationController {

    private final RuntimeService runtimeService;
    private final VerificationCodeService verificationCodeService;

    @GetMapping(RestApiEndpoints.VERIFY_ACCOUNT)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void verifyAccount(@RequestParam(RestApiRequestParameters.VERIFICATION_CODE) @NotBlank String verificationCode,
                              @RequestParam(RestApiRequestParameters.PROCESS_INSTANCE_ID) @NotBlank String processInstanceId) {
        log.info(LogMessages.VERIFYING_ACCOUNT, verificationCode, processInstanceId);

        this.verificationCodeService.markVerificationCodeAsUsed(verificationCode);
        this.runtimeService.createMessageCorrelation(Constants.ProcessConstants.USER_ACCOUNT_VERIFIED_MESSAGE)
                .processInstanceId(processInstanceId)
                .correlateWithResult();

        log.info(LogMessages.VERIFIED_ACCOUNT, verificationCode, processInstanceId);
    }
}
