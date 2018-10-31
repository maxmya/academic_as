package com.academic.as.demo.controllers.web;


import com.academic.as.demo.controllers.web.models.ConfirmPassword;
import com.academic.as.demo.controllers.web.models.UserRole;
import com.academic.as.demo.enums.UserRoles;
import com.academic.as.demo.models.*;
import com.academic.as.demo.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.validation.Valid;

@Controller
public class RegisterViewController implements WebMvcConfigurer {



    @Autowired
    RegisterService registerService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String registerUserView(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", UserRoles.values());
        model.addAttribute("role", new UserRole());
        model.addAttribute("confrimPassword",new ConfirmPassword());
        return "add_user";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,
                               @ModelAttribute("role") UserRole role , Model model) {

        //todo : this code should not be in the controller class , it should be hold by another service

        if (bindingResult.hasErrors()) {
            return "add_user";
        }
        switch (role.getRole()) {
            case "SYSTEM":
                model.addAttribute("response", registerService.addUser(user));
                break;
            case "ADMIN":
                Admin admin = new Admin();
                admin.setUser(user);
                model.addAttribute("response", registerService.addAdmin(admin));
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
        return "home";
    }

}
