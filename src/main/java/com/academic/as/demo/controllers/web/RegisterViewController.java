package com.academic.as.demo.controllers.web;


import com.academic.as.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterViewController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }
}
