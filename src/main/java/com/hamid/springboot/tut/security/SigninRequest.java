package com.hamid.springboot.tut.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hamid Ait Brahim
 * @Created 20/01/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {
    private String username;
    private String password;

}
