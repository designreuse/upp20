package org.ftn.upp.literaryassociation.model;

import lombok.Getter;

@Getter
public enum UserRole {
    READER(0, "Reader"),
    BETA_ACCESS_READER(1, "Beta-access reader"),
    AUTHOR(2, "Author"),
    PROOFREADER(3, "Proofreader"),
    BOARD_MEMBER(4, "Board member"),
    EDITOR(5, "Editor"),
    EDITOR_IN_CHIEF(6, "Editor-in-chief"),
    SYSTEM_ADMIN(7, "System Admin");

    private final int code;
    private final String message;

    UserRole(int code, String message) {
        if (code < 0 || code > 7)
            throw new IllegalArgumentException("code");
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");

        this.code = code;
        this.message = message.trim();
    }

    public static UserRole parse(String message) {
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");
        for (var enumValue : UserRole.values())
            if (enumValue.getMessage().equalsIgnoreCase(message))
                return enumValue;
        throw new IndexOutOfBoundsException("name");
    }

    public static UserRole fromValue(int value) {
        if (value < 0 || value > 7)
            throw new IndexOutOfBoundsException("value");
        for (var enumValue : UserRole.values())
            if (enumValue.getCode() == value)
                return enumValue;
        throw new IndexOutOfBoundsException("value");
    }
}
