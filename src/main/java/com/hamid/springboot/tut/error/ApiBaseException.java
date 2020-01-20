package com.hamid.springboot.tut.error;

import org.springframework.http.HttpStatus;

/**
 * @author Hamid Ait Brahim
 * @Created 13/01/2020
 */
public abstract class ApiBaseException extends RuntimeException{

    public ApiBaseException(String message)
    {
        super(message);
    }
    public abstract HttpStatus getStatusCode();
}
