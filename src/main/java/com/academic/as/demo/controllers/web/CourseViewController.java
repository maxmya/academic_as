package com.academic.as.demo.controllers.web;

import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.CoursesResponse;
import com.academic.as.demo.models.Course;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.repositories.CourseRepository;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.SpecializationService;
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
import java.util.List;
import java.util.logging.Logger;

@Controller
public class CourseViewController implements WebMvcConfigurer {

    @Autowired
    CoursesService coursesService;

    @Autowired
    SpecializationService specializationService;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/add/course")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCourseView(Model model) {
        model.addAttribute(new Course());
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "add_course";
    }

    @PostMapping("/add/course")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("departments", specializationService.getAllDepartments().getData());
            return "add_course";
        }
        BaseResponse response = coursesService.addCourseByDepName(course);
        if (response.getCode().equalsIgnoreCase("400"))
            model.addAttribute(course);
        else
            model.addAttribute(new Course());
        model.addAttribute("response", response);
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "add_course";
    }
    //Todo prevent user to put anyother type of id as integer
    @GetMapping("/course/{ID}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCourseView(@PathVariable(value="ID") Integer id,Model model) {
        CoursesResponse coursesResponse = coursesService.getCourse(id);
        if(coursesResponse.getCode() == "400") {
            return "404";
        }
        model.addAttribute("course", coursesResponse.getData());
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "edit_course";
    }

    @PostMapping("/course/{ID}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult,Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("departments", specializationService.getAllDepartments().getData());
            return "edit_course";
        }
        BaseResponse response = coursesService.SaveCourse(course,course.getId());
        if (response.getCode().equalsIgnoreCase("200"))
            model.addAttribute(course);
        model.addAttribute("response", response);
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "edit_course";
    }

}
