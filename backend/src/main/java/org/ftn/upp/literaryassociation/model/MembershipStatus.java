package org.ftn.upp.literaryassociation.model;

import lombok.Getter;

@Getter
public enum MembershipStatus {
    REQUESTED(0, "Requested"),
    NOT_PAID(1, "Not Paid"),
    ACTIVE(2, "Active"),
    INACTIVE(3, "Inactive");

    private final int code;
    private final String message;

    MembershipStatus(int code, String message) {
        if (code < 0 || code > 3)
            throw new IllegalArgumentException("code");
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");

        this.code = code;
        this.message = message.trim();
    }

    public static MembershipStatus parse(String message) {
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");
        for (var enumValue : MembershipStatus.values())
            if (enumValue.getMessage().equalsIgnoreCase(message))
                return enumValue;
        throw new IndexOutOfBoundsException("name");
    }

    public static MembershipStatus fromValue(int value) {
        if (value < 0 || value > 3)
            throw new IndexOutOfBoundsException("value");
        for (var enumValue : MembershipStatus.values())
            if (enumValue.getCode() == value)
                return enumValue;
        throw new IndexOutOfBoundsException("value");
    }
}
