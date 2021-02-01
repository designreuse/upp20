package org.ftn.upp.lass.util;

/**
 * Contains utility methods for constructing error messages.
 */
public final class ErrorMessageUtil {

    private ErrorMessageUtil() {}

    public static String processWithGivenNameNotFound(String processName) {
        return String.format("Process with name '%s' was not found.", processName);
    }

    public static String activeTaskForProcessInstanceNotFound(String processInstanceId) {
        return String.format("Active task for process instance '%s' was not found.", processInstanceId);
    }

    public static String taskNotFound(String taskId) {
        return String.format("Task with ID '%s' was not found.", taskId);
    }

    public static String taskIsNotActive(String taskId) {
        return String.format("Task with ID '%s' is not active.", taskId);
    }

    public static String taskIsNotAssigned(String taskId) {
        return String.format("Task with ID '%s' is not assigned to any user.", taskId);
    }
}