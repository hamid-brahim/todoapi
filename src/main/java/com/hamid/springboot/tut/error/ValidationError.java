package com.hamid.springboot.tut.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Hamid Ait Brahim
 * @Created 13/01/2020
 */
@Data
@AllArgsConstructor
public class ValidationError {
    private List<String> errors;
    private String uri;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ValidationError()
    {
        this.timestamp = new Date();
        this.errors = new ArrayList<>();
    }

    public void addError(String error)
    {
        errors.add(error);
    }

}
