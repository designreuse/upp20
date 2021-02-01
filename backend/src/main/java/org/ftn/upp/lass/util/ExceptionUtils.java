package org.ftn.upp.lass.util;

import org.ftn.upp.lass.exception.BadRequestException;
import org.ftn.upp.lass.exception.BadRequestResponseCode;

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

    public static void throwBadRequestException(BadRequestResponseCode badRequestResponseCode, String message) {
        throw new BadRequestException(badRequestResponseCode, message);
    }
}
