package com.hamid.springboot.tut.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hamid Ait Brahim
 * @Created 07/01/2020
 */
@RestController
public class HomeController {

    @RequestMapping(value = "/")
    public String greeting()
    {
        return "Welcome to SpringBoot";
    }
    @GetMapping(value = "/{name}")
    public String greetingWithName(@PathVariable String name)
    {
        return String.format("Welcome %s to our app", name);
    }
}
