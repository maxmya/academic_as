package com.academic.as.demo.controllers;


import com.academic.as.demo.api.responses.UsersResponse;
import com.academic.as.demo.services.GetUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/users")
@RestController
public class GetUsersController {

    @Autowired
    GetUsersService getUsersService;

    @GetMapping(value = "/admins")
    public UsersResponse getAdmins() {
        return getUsersService.getAdmins();
    }

//    @GetMapping(value = "admin/{id}")
//    public UsersResponse getAdmin(@PathVariable("id") Integer id) {
//        return usersService.getAdmin(id);
//    }


    @GetMapping(value = "/students")
    public UsersResponse getStudents() {
        return getUsersService.getStudents();
    }

//    @GetMapping(value = "student/{id}")
//    public UsersResponse getStudent(@PathVariable("id") Integer id) {
//        return usersService.getStudent(id);
//    }

    @GetMapping(value = "/assistants")
    public UsersResponse getAssistants() {
        return getUsersService.getAssistants();
    }

//    @GetMapping(value = "assistant/{id}")
//    public UsersResponse getAssistant(@PathVariable("id") Integer id) {
//        return usersService.getAssistant(id);
//    }

    @GetMapping(value = "/supervisors")
    public UsersResponse getSupervisors() {
        return getUsersService.getSupervisors();
    }

//    @GetMapping(value = "supervisor/{id}")
//    public UsersResponse getSupervisor(@PathVariable("id") Integer id) {
//        return usersService.getSupervisor(id);
//    }

    @GetMapping(value = "/professors")
    public UsersResponse getProfessors() {
        return getUsersService.getProfessors();
    }

//    @GetMapping(value = "professor/{id}")
//    public UsersResponse gerProfessor(@PathVariable("id") Integer id) {
//        return usersService.getProfessor(id);
//    }

}
