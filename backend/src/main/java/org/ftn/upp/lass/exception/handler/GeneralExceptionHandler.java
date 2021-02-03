package org.ftn.upp.lass.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.dto.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles general exceptions.
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleGeneralException(Exception e) {
        log.error(LogMessages.EXCEPTION, e.toString());

        return ErrorResponse.fromGeneralException();
    }
}
