package com.academic.as.demo.controllers.web;

import com.academic.as.demo.models.Course;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AddCourseViewController {

    @Autowired
    CoursesService coursesService;

    @Autowired
    SpecializationService specializationService;

    @GetMapping("/addcoursee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCourseView(Model model) {
        model.addAttribute(new Course());
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "addcourse";
    }

    @PostMapping("/addcoursee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCourse(@ModelAttribute("course") Course course, Model model) {
        model.addAttribute("response", coursesService.addCourseByDepName(course));
        return "home";
    }


}
