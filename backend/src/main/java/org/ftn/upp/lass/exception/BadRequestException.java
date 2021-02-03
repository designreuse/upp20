package org.ftn.upp.lass.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BadRequestException extends RuntimeException {

    private final BadRequestResponseCode badRequestResponseCode;
    private final String message;
}