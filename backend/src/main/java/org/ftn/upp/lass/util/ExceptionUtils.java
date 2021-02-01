package org.ftn.upp.lass.util;

import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.BadRequestResponseCode;
import org.ftn.upp.lass.exception.InsufficientPrivilegesException;
import org.ftn.upp.lass.exception.NotFoundException;

/**
 * Contains utility methods for exception handling.
 */
public final class ExceptionUtils {

    private ExceptionUtils() { }

    public static void throwBadRequestExceptionIf(boolean condition, BadRequestResponseCode badRequestResponseCode, String message) {
        if (condition) {
            throwBadRequestException(badRequestResponseCode, message);
        }
    }

    public static void throwInsufficientPrivilegesExceptionIf(boolean condition) {
        if (condition) {
            throwInsufficientPrivilegesException();
        }
    }

    public static void throwNotFoundExceptionIf(boolean condition, String message) {
        if (condition) {
            throwNotFoundException(message);
        }
    }

    public static void throwBadRequestException(BadRequestResponseCode badRequestResponseCode, String message) {
        throw new BadRequestException(badRequestResponseCode, message);
    }

    public static void throwInsufficientPrivilegesException() {
        throw new InsufficientPrivilegesException();
    }

    public static void throwNotFoundException(String message) {
        throw new NotFoundException(message);
    }
}
