package org.ftn.upp.lass.model;

import lombok.Getter;

@Getter
public enum BookSubmissionStatus {
    WAITING_FOR_DOCUMENT(0, "Waiting for Document"),
    DOCUMENT_SUBMITTED(1, "Document Submitted"),
    REVIEW_IN_PROGRESS(2, "Review in Progress"),
    CORRECTION_REQUESTED(3, "Correction Requested"),
    FAILED_PLAGIARISM_CHECK(4, "Failed Plagiarism Check"),
    ACCEPTED(5, "Accepted"),
    REJECTED(6, "Rejected");

    private final int code;
    private final String message;

    BookSubmissionStatus(int code, String message) {
        if (code < 0 || code > 6)
            throw new IllegalArgumentException("code");
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");

        this.code = code;
        this.message = message.trim();
    }

    public static BookSubmissionStatus parse(String message) {
        if (message == null || message.trim().length() == 0)
            throw new IllegalArgumentException("name");
        for (var enumValue : BookSubmissionStatus.values())
            if (enumValue.getMessage().equalsIgnoreCase(message))
                return enumValue;
        throw new IndexOutOfBoundsException("name");
    }

    public static BookSubmissionStatus fromValue(int value) {
        if (value < 0 || value > 6)
            throw new IndexOutOfBoundsException("value");
        for (var enumValue : BookSubmissionStatus.values())
            if (enumValue.getCode() == value)
                return enumValue;
        throw new IndexOutOfBoundsException("value");
    }
}
