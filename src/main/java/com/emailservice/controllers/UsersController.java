package com.emailservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by murad on 9/29/16.
 */

@Controller
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model, HttpSession session){
        return "users/login";
    }

    @RequestMapping(value = "/users/list",method = RequestMethod.GET)
    public String admins(){
        return "users/list";
    }
}
