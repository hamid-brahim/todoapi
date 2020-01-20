package com.hamid.springboot.tut.error;

import org.springframework.http.HttpStatus;

/**
 * @author Hamid Ait Brahim
 * @Created 13/01/2020
 */
public class ConflictException extends ApiBaseException {
    public ConflictException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
