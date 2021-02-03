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

    public static String userDoesNotExist(String username) {
        return String.format("User with username %s was not found.", username);
    }

    public static String verificationCodeDoesNotExist(String verificationCode) {
        return String.format("Verification code %s was not found.", verificationCode);
    }

    public static String verificationCodeAlreadyUsed(String verificationCode) {
        return String.format("Verification code %s already used.", verificationCode);
    }

    public static String verificationCodeExpired(String verificationCode) {
        return String.format("Verification code %s expired.", verificationCode);
    }
}