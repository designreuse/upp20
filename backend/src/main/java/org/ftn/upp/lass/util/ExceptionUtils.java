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

    public static void throwBadRequestExceptionIf(boolean condition, BadRequestResponseCode badRequestResponseCode, String message) throws BadRequestException {
        if (condition) {
            throwBadRequestException(badRequestResponseCode, message);
        }
    }

    public static void throwInsufficientPrivilegesExceptionIf(boolean condition) throws InsufficientPrivilegesException {
        if (condition) {
            throwInsufficientPrivilegesException();
        }
    }

    public static void throwNotFoundExceptionIf(boolean condition, String message) throws NotFoundException {
        if (condition) {
            throwNotFoundException(message);
        }
    }

    public static void throwBadRequestException(BadRequestResponseCode badRequestResponseCode, String message) throws BadRequestException {
        throw new BadRequestException(badRequestResponseCode, message);
    }

    public static void throwInsufficientPrivilegesException() throws InsufficientPrivilegesException {
        throw new InsufficientPrivilegesException();
    }

    public static void throwNotFoundException(String message) throws NotFoundException {
        throw new NotFoundException(message);
    }
}
