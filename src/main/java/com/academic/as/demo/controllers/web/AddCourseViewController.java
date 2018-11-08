package com.academic.as.demo.controllers.web;

import com.academic.as.demo.models.Course;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class AddCourseViewController implements WebMvcConfigurer {

    @Autowired
    CoursesService coursesService;

    @Autowired
    SpecializationService specializationService;

    @GetMapping("/add/course")
    public String addCourseView(Model model) {
        model.addAttribute(new Course());
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "add_course";
    }

    @PostMapping("/add/course")
    public String addCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult , Model model) {

        System.out.print("errors");
        System.out.print(bindingResult.getAllErrors());
        if (bindingResult.hasErrors()) {
            return "add_course";
        }
//TODO redirect to the same page
        model.addAttribute("response", coursesService.addCourseByDepName(course));
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "add_course";
    }


}
