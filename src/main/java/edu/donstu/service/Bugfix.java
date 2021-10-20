package edu.donstu.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.donstu.service.services.security.UserService;

@Component
public class Bugfix {
    private static Bugfix bugfix;
    private UserService userService;

    @Autowired
    Bugfix(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void fillInstance() {
        bugfix = this;
    }

    public static UserService getUserService() {
        return bugfix.userService;
    }
}
