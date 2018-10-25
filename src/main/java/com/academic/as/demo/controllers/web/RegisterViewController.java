package com.academic.as.demo.controllers.web;


import com.academic.as.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterViewController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String registerUserView(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/register")
    public String registerUser(Model model,@ModelAttribute("user") User user) {
        System.out.println(user.getEmail());
        return "index";
    }

}
