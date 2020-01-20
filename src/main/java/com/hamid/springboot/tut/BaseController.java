package com.hamid.springboot.tut;

import com.hamid.springboot.tut.security.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Hamid Ait Brahim
 * @Created 20/01/2020
 */
public abstract class BaseController {

    public AppUser getCurrentUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = (AppUser) authentication.getPrincipal();

        return user;
    }
}
