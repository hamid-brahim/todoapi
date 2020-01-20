package com.hamid.springboot.tut.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Hamid Ait Brahim
 * @Created 09/01/2020
 */
@Data
public class ErrorDetails {
    private String message;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ErrorDetails()
    {
        this.timestamp = new Date();
    }
    public ErrorDetails(String message,String url)
    {
        this();
        this.message= message;
        this.url = url;
    }

}
