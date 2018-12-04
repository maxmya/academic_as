package com.academic.as.demo.controllers.web;

import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.CourseInstanceResponse;
import com.academic.as.demo.api.responses.CoursesResponse;
import com.academic.as.demo.models.Course;

import com.academic.as.demo.models.CourseInstance;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.repositories.CourseRepository;
import com.academic.as.demo.repositories.DepartmentRepository;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.SpecializationService;
import jdk.nashorn.internal.runtime.logging.Logger;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseViewController implements WebMvcConfigurer {

    @Autowired
    CoursesService coursesService;

    @Autowired
    SpecializationService specializationService;


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
    public String editCourseView(@PathVariable(value = "ID") Integer id, Model model) {
        CoursesResponse coursesResponse = coursesService.getCourse(id);
        if (coursesResponse.getCode() == "400") {
            return "404";
        }
        model.addAttribute("course", coursesResponse.getData());
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "edit_course";
    }

    @PostMapping("/course/{ID}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("departments", specializationService.getAllDepartments().getData());
            return "edit_course";
        }

        BaseResponse response = coursesService.SaveCourse(course, course.getId());
        if (response.getCode().equalsIgnoreCase("200"))
            model.addAttribute(course);
        model.addAttribute("response", response);
        model.addAttribute("departments", specializationService.getAllDepartments().getData());
        return "edit_course";
    }

    @GetMapping("/courses/{userName}/register")
    public String getRegistercoursesView(@PathVariable(value = "userName") String userName, Model model) {
        CourseInstanceResponse courseData = coursesService.ViewregisterCoursesToStudent(userName);
        List<CourseInstance> courseInstancesdata = (List<CourseInstance>) courseData.getData();
        List<CourseInstance> courseList = new ArrayList<>();
        if (courseData.getCode().equalsIgnoreCase("200")) {
            model.addAttribute(new CourseInstance());
            model.addAttribute("courseData", courseInstancesdata);
            model.addAttribute("listcourses", courseList);
        }
        model.addAttribute("response", courseData.getData());
        return "registe_course";
    }

//    @PostMapping("/courses/{userName}/register")
//    public String getRegisterCourses(@ModelAttribute("course") @PathVariable(value = "userName") @Valid CourseInstance courseInstance, String userName, BindingResult bindingResult, Model model) {
//        CourseInstanceResponse courseData = coursesService.ViewregisterCoursesToStudent(userName);
//        CourseInstanceResponse response = coursesService.ViewregisterCoursesToStudent(userName);
//
//        List<CourseInstance> courseInstancesdata = (List<CourseInstance>) courseData.getData();
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            model.addAttribute("courses", coursesService.ViewregisterCoursesToStudent(userName).getData());
//            return "registe_course";
//        }else if (response.getCode().equalsIgnoreCase("200")) {
//            model.addAttribute("courseData", courseInstancesdata);
//
//
//        } else {
//            model.addAttribute(new CourseInstance());
//            model.addAttribute("response", response);
//            model.addAttribute("courseData", courseInstancesdata);
//            return "registe_course";
//        }
//        return "registe_course" ;
//    }
}