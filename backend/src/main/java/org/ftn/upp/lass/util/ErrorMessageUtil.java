package org.ftn.upp.lass.util;

/**
 * Contains utility methods for constructing error messages.
 */
public final class ErrorMessageUtil {

    private ErrorMessageUtil() {}

    public static String processWithGivenNameNotFound(String processName) {
        return String.format("Process with name '%s' was not found.", processName);
    }
}