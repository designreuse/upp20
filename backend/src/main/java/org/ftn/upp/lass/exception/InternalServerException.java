package org.ftn.upp.lass.exception;

public class InternalServerException extends RuntimeException {

    public InternalServerException() { }

    public InternalServerException(Exception e) {
        super(e);
    }
}