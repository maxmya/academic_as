package com.academic.as.demo.controllers.WebControllers;

import com.academic.as.demo.models.User;
import com.academic.as.demo.services.GetUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
@Controller
public class UserController {

    @Autowired
    GetUsersService usersService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String addUserView(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/AddUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, Model model) {
        System.out.print(user);
        //TODO check the type the use methods of register service
        return "index";
    }

}
