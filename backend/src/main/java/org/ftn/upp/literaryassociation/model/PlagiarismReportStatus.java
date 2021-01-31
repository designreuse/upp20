package org.ftn.upp.literaryassociation.model;

import lombok.Getter;

@Getter
public enum PlagiarismReportStatus {
    REPORTED(0, "Reported"),
    IN_REVIEW(1, "In Review"),
    NO_ACTION_TAKEN(2, "No Action Taken"),
    CONFIRMED(3, "Confirmed");

    private final int code;
    private final String message;

    PlagiarismReportStatus(int code, String message) {
        if (code < 0 || code > 3)
            throw new IllegalArgumentException("code");
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");

        this.code = code;
        this.message = message.trim();
    }

    public static PlagiarismReportStatus parse(String message) {
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");
        for (var enumValue : PlagiarismReportStatus.values())
            if (enumValue.getMessage().equalsIgnoreCase(message))
                return enumValue;
        throw new IndexOutOfBoundsException("name");
    }

    public static PlagiarismReportStatus fromValue(int value) {
        if (value < 0 || value > 3)
            throw new IndexOutOfBoundsException("value");
        for (var enumValue : PlagiarismReportStatus.values())
            if (enumValue.getCode() == value)
                return enumValue;
        throw new IndexOutOfBoundsException("value");
    }
}
