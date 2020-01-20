package com.hamid.springboot.tut.error;

import org.springframework.http.HttpStatus;

/**
 * @author Hamid Ait Brahim
 * @Created 13/01/2020
 */
public class NotFoundException extends ApiBaseException {
    public NotFoundException(String message)
    {
        super(message);
    }
    @Override
    public HttpStatus getStatusCode(){
        return HttpStatus.NOT_FOUND;
    }
}
