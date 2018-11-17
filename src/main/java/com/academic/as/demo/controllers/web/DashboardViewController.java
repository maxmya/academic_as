package com.academic.as.demo.controllers.web;

import com.academic.as.demo.models.*;
import com.academic.as.demo.services.GetUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardViewController {


    @Autowired
    GetUsersService usersService;


    @GetMapping("/all/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllUsers(Model model) {
        return "dashboard";
    }

    @GetMapping("/all/admins")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllAdmins(Model model) {
        List<Admin> admins =  (List<Admin>) usersService.getAdmins().getData();
        model.addAttribute("admins" , admins);
        return "all_admins";
    }

    @GetMapping("/all/supervisors")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllSupervisors(Model model) {

        model.addAttribute("supervisors" , (List<Supervisor>) usersService.getSupervisors().getData());
        return "all_supervisors";
    }

    @GetMapping("/all/students")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllStudents(Model model) {

        model.addAttribute("students" , (List<Student>) usersService.getStudents().getData());
        return "all_students";
    }

    @GetMapping("/all/assistants")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllAssistants(Model model) {

        model.addAttribute("assistants" , (List<Assistant>) usersService.getAssistants().getData());
        return "all_assistants";
    }

    @GetMapping("/all/professors")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllProfessors(Model model) {

        model.addAttribute("professors" , (List<Professor>) usersService.getProfessors().getData());
        return "all_professors";
    }
}
