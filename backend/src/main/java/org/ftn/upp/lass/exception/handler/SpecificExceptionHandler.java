package org.ftn.upp.lass.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.ftn.upp.lass.common.LogMessages;
import org.ftn.upp.lass.common.api.RestApiResponseParameters;
import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.InsufficientPrivilegesException;
import org.ftn.upp.lass.exception.NotFoundException;
import org.ftn.upp.lass.dto.response.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

/**
 * Handles specific custom exceptions.
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class SpecificExceptionHandler {

    // =================================================================================================================
    // BAD REQUEST EXCEPTIONS
    // =================================================================================================================

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBadRequestException(BadRequestException e) {
        log.error(LogMessages.EXCEPTION, e.getMessage());

        return ErrorResponse.fromBadRequestException(e);
    }

    // =================================================================================================================
    // NOT FOUND EXCEPTIONS
    // =================================================================================================================

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleBadCredentialsException(BadCredentialsException e) {
        log.error(LogMessages.EXCEPTION, e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());

        return errorResponse;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        log.error(LogMessages.EXCEPTION, e.getMessage());

        return ErrorResponse.fromNotFoundException(e);
    }

    // =================================================================================================================
    // FORBIDDEN EXCEPTIONS
    // =================================================================================================================

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
        log.error(LogMessages.EXCEPTION, e.getMessage());

        return ErrorResponse.fromInsufficientPrivilegesException();
    }

    @ExceptionHandler(InsufficientPrivilegesException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleInsufficientPrivilegesException(InsufficientPrivilegesException e) {
        log.error(LogMessages.EXCEPTION, e.getMessage());

        return ErrorResponse.fromInsufficientPrivilegesException();
    }
}
