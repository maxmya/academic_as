package com.academic.as.demo.controllers.web;


import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.UsersResponse;
import com.academic.as.demo.controllers.web.models.UserRole;
import com.academic.as.demo.enums.UserRoles;
import com.academic.as.demo.models.*;
import com.academic.as.demo.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

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

    @GetMapping("/user/{ID}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editUserView(@PathVariable(value="ID") Integer id, Model model) {
        UsersResponse usersResponse = registerService.getUser(id);
        if(usersResponse.getCode() != "200"){
            return "404";
        }
        model.addAttribute("user", registerService.getUser(id).getData());
        model.addAttribute("roles", UserRoles.values());
        model.addAttribute("role", new UserRole());
        return "edit_user";
    }

    @PostMapping("/user/{ID}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editUser(@ModelAttribute("course") @Valid User user, @ModelAttribute("role") UserRole role ,BindingResult bindingResult,Model model) {

        System.out.println(bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "edit_user";
        }
        BaseResponse response = registerService.saveUser(user,user.getId());
        System.out.println(response.getCode());
        if (response.getCode().equalsIgnoreCase("200"))
            model.addAttribute(user);
        model.addAttribute("response", response);
        model.addAttribute("roles", UserRoles.values());
        model.addAttribute("role", new UserRole());
        return "edit_user";
    }
}