package com.academic.as.demo.controllers;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.models.*;
import com.academic.as.demo.services.RegisterService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/register")
@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;


    @PostMapping(value = "/admin")
    public RegisterResponse registerAdmin(@RequestBody Admin admin) {
        return registerService.addAdmin(admin);
    }

    @PostMapping(value = "/student")
    public RegisterResponse registerStudent(@RequestBody Student student) {
        return registerService.addStudent(student);
    }

    @PostMapping(value = "/supervisor")
    public RegisterResponse registerSupervisor(@RequestBody Supervisor supervisor) {
        return registerService.addSupervisor(supervisor);
    }

    @PostMapping(value = "/assistant")
    public RegisterResponse registerAssistant(@RequestBody Assistant assistant) {
        return registerService.addAssistant(assistant);
    }

    @PostMapping(value = "/professor")
    public RegisterResponse registerProfessor(@RequestBody Professor professor) {
        return registerService.addProfessor(professor);
    }

    @PostMapping(value = "/user")
    public RegisterResponse registerUser(@RequestBody User user) {

        return registerService.addUser(user);
    }
}

