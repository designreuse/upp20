package org.ftn.upp.lass.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Custom response codes for BAD_REQUEST response type.
 */
@Getter
@RequiredArgsConstructor
public enum BadRequestResponseCode {

    REQUEST_DATA_MISSING(1, "Request does not contain mandatory data."),
    REQUEST_PARAM_MISSING(2, "Request is missing required parameters."),
    INVALID_DATA(3, "Invalid data on server."),
    INVALID_REQUEST_DATA(4, "Request contains invalid data."),
    INVALID_MEDIA_TYPE(5, "Request contains invalid media type."),
    INVALID_METHOD_ARGUMENT(6, "Request contains invalid method arguments."),
    INSUFFICIENT_PRIVILEGES(7, "Access denied due to insufficient privileges."),
    METHOD_NOT_SUPPORTED(8, "Server does not support requested method."),
    MULTIPART_REQUEST_REQUIRED(9, "Request is not a multipart request."),
    INTERNAL_SERVER_ERROR(10, "Something went wrong on the server's side.");

    private final int code;
    private final String message;
}