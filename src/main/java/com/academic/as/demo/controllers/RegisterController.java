package com.academic.as.demo.controllers;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.models.UserEntity;
import com.academic.as.demo.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;


    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public RegisterResponse registerUser(@RequestBody UserEntity user) {
        return registerService.addUser(user);
    }
}
