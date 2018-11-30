package com.academic.as.demo.controllers.web;
import com.academic.as.demo.api.requests.CourseInstanceRequest;
<<<<<<< HEAD
import com.academic.as.demo.models.Hall;
import com.academic.as.demo.services.CoursesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
=======
import com.academic.as.demo.models.CourseInstance;
import com.academic.as.demo.models.Hall;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.SpecializationService;
>>>>>>> e65971b86c8063bc20d76348cd4f470527ca507c
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class AddHallViewController implements WebMvcConfigurer {
=======


@Controller
public class AddHallViewController {
>>>>>>> e65971b86c8063bc20d76348cd4f470527ca507c

    @Autowired
    CoursesService coursesService;

    @GetMapping("/add/hall")
<<<<<<< HEAD
    @PreAuthorize("hasRole('ROLE_ADMIN')")
=======
>>>>>>> e65971b86c8063bc20d76348cd4f470527ca507c
    public String addHallView(Model model) {
        model.addAttribute(new Hall());
        return "add_hall";
    }

    @PostMapping("/add/hall")
<<<<<<< HEAD
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addHall(@ModelAttribute("hall") @Valid Hall hall , BindingResult bindingResult ,CourseInstanceRequest courseInstanceRequest,Model model) {
        if (bindingResult.hasErrors()) {
            return "add_hall";
        }
=======
    public String addHall(@ModelAttribute("hall")Hall hall,CourseInstanceRequest courseInstanceRequest,Model model) {
>>>>>>> e65971b86c8063bc20d76348cd4f470527ca507c
        model.addAttribute("response",coursesService.addHall(hall));
        return "add_hall";
    }

}
