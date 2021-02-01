package org.ftn.upp.lass.model;

import lombok.Getter;

@Getter
public enum VerificationCodeStatus {
    NEW(0, "New"),
    EXPIRED(1, "Expired"),
    USED(2, "Used");

    private final int code;
    private final String message;

    VerificationCodeStatus(int code, String message) {
        if (code < 0 || code > 2)
            throw new IllegalArgumentException("code");
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");

        this.code = code;
        this.message = message.trim();
    }

    public static VerificationCodeStatus parse(String message) {
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");
        for (var enumValue : VerificationCodeStatus.values())
            if (enumValue.getMessage().equalsIgnoreCase(message))
                return enumValue;
        throw new IndexOutOfBoundsException("name");
    }

    public static VerificationCodeStatus fromValue(int value) {
        if (value < 0 || value > 2)
            throw new IndexOutOfBoundsException("value");
        for (var enumValue : VerificationCodeStatus.values())
            if (enumValue.getCode() == value)
                return enumValue;
        throw new IndexOutOfBoundsException("value");
    }
}
