package com.academic.as.demo.controllers.web;


import com.academic.as.demo.models.User;
import com.academic.as.demo.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterViewController {

    @Autowired
    RegisterService registerService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/register")
    public String registerUserView(Model model) {
        model.addAttribute(new User());
        return "add_user";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("response", registerService.addUser(user));
        return "home";
    }

}
