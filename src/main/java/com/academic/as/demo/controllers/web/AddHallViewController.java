package com.academic.as.demo.controllers.web;
import com.academic.as.demo.api.requests.CourseInstanceRequest;
import com.academic.as.demo.models.Hall;
import com.academic.as.demo.services.CoursesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class AddHallViewController implements WebMvcConfigurer {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/add/hall")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addHallView(Model model) {
        model.addAttribute(new Hall());
        return "add_hall";
    }

    @PostMapping("/add/hall")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addHall(@ModelAttribute("hall") @Valid Hall hall , BindingResult bindingResult ,CourseInstanceRequest courseInstanceRequest,Model model) {
        if (bindingResult.hasErrors()) {
            return "add_hall";
        }
        model.addAttribute("response",coursesService.addHall(hall));
        return "add_hall";
    }

}
