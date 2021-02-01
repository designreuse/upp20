package org.ftn.upp.lass.model;

import lombok.Getter;

@Getter
public enum ReviewResult {
    IN_PROGRESS(0, "In Progress"),
    ACCEPTED(1, "Accepted"),
    RESUBMISSION_REQUIRED(2, "Resubmission Required"),
    REJECTED(3, "Rejected");

    private final int code;
    private final String message;

    ReviewResult(int code, String message) {
        if (code < 0 || code > 3)
            throw new IllegalArgumentException("code");
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");

        this.code = code;
        this.message = message.trim();
    }

    public static ReviewResult parse(String message) {
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");
        for (var enumValue : ReviewResult.values())
            if (enumValue.getMessage().equalsIgnoreCase(message))
                return enumValue;
        throw new IndexOutOfBoundsException("name");
    }

    public static ReviewResult fromValue(int value) {
        if (value < 0 || value > 3)
            throw new IndexOutOfBoundsException("value");
        for (var enumValue : ReviewResult.values())
            if (enumValue.getCode() == value)
                return enumValue;
        throw new IndexOutOfBoundsException("value");
    }
}
