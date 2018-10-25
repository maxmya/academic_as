package com.academic.as.demo.controllers.web;

import com.academic.as.demo.models.Course;
import com.academic.as.demo.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class AddCourseViewController {

    @Autowired
    CoursesService coursesService ;
    
    @GetMapping("/addcoursee")
    public String addCourseView(Model model) {
        model.addAttribute(new Course());
        return "addcourse";
    }

    @PostMapping("/addcoursee")
    public String addCourse(@ModelAttribute("course") Course course, Model model) {
        model.addAttribute("response",coursesService.addCourseByDepName(course));
        return "home";
    }



}
