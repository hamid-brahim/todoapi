package com.hamid.springboot.tut.util;

import com.hamid.springboot.tut.security.AppUser;
import com.hamid.springboot.tut.security.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Hamid Ait Brahim
 * @Created 20/01/2020
 */
@Component
public class FirstTimeInitializer implements CommandLineRunner {
    private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if(userService.findAll().isEmpty())
        {
            logger.info("No Users account found. Creating some users");

            AppUser user = new AppUser("brahamid@gmail.com", "password", "hamid");
            userService.save(user);
        }

    }
}
