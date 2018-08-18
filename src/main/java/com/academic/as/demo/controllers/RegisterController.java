package com.academic.as.demo.controllers;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.models.Admin;
import com.academic.as.demo.models.User;
import com.academic.as.demo.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/register")
@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping(value = "/admin")
    public RegisterResponse registerAdmin(@RequestBody Admin admin) {
        RegisterResponse response = registerService.addUser(admin.getUser());
        if (response.getCode() == "200")
            response = registerService.addAdmin(admin);
        return response;
    }

    @PostMapping(value = "/user")
    public RegisterResponse registerUser(@RequestBody User user) {
        return registerService.addUser(user);
    }
}

