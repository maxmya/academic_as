package com.academic.as.demo.controllers.web;


import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.controllers.web.models.UserRole;
import com.academic.as.demo.enums.UserRoles;
import com.academic.as.demo.models.*;
import com.academic.as.demo.services.GetUsersService;
import com.academic.as.demo.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterViewController implements WebMvcConfigurer {


    @Autowired
    RegisterService registerService;


    @GetMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String registerUserView(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", UserRoles.values());
        model.addAttribute("role", new UserRole());
        return "add_user";
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                               @ModelAttribute("role") UserRole role, Model model) {

        System.out.println("in post");
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("user", new User());
            model.addAttribute("roles", UserRoles.values());
            model.addAttribute("role", new UserRole());
            return "add_user";
        }
        model.addAttribute("user", new User());
        model.addAttribute("roles", UserRoles.values());
        model.addAttribute("role", new UserRole());
        switch (role.getRole()) {
            case "SYSTEM":
                model.addAttribute("response", registerService.addUser(user));
                break;
            case "ADMIN":
                Admin admin = new Admin();
                admin.setUser(user);
                BaseResponse res = registerService.addAdmin(admin);
                model.addAttribute("response", res);
                System.out.println(res.getMessage());
                break;
            case "STUDENT":
                Student student = new Student();
                student.setUser(user);
                model.addAttribute("response", registerService.addStudent(student));
                break;
            case "ASSISTANT":
                Assistant assistant = new Assistant();
                assistant.setUser(user);
                model.addAttribute("response", registerService.addAssistant(assistant));
                break;
            case "PROFESSOR":
                Professor professor = new Professor();
                professor.setUser(user);
                model.addAttribute("response", registerService.addProfessor(professor));
                break;
            case "SUPERVISOR":
                Supervisor supervisor = new Supervisor();
                supervisor.setUser(user);
                model.addAttribute("response", registerService.addSupervisor(supervisor));
                break;
        }
        return "add_user";
    }
}