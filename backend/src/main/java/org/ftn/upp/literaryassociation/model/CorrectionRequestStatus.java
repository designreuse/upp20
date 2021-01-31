package org.ftn.upp.literaryassociation.model;

import lombok.Getter;

@Getter
public enum CorrectionRequestStatus {
    REQUESTED(0, "Requested"),
    COMMITTED(1, "Committed"),
    RESOLVED(2, "Resolved");

    private final int code;
    private final String message;

    CorrectionRequestStatus(int code, String message) {
        if (code < 0 || code > 2)
            throw new IllegalArgumentException("code");
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");

        this.code = code;
        this.message = message.trim();
    }

    public static CorrectionRequestStatus parse(String message) {
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");
        for (var enumValue : CorrectionRequestStatus.values())
            if (enumValue.getMessage().equalsIgnoreCase(message))
                return enumValue;
        throw new IndexOutOfBoundsException("name");
    }

    public static CorrectionRequestStatus fromValue(int value) {
        if (value < 0 || value > 2)
            throw new IndexOutOfBoundsException("value");
        for (var enumValue : CorrectionRequestStatus.values())
            if (enumValue.getCode() == value)
                return enumValue;
        throw new IndexOutOfBoundsException("value");
    }
}
