package com.academic.as.demo.controllers;


import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.CoursesResponse;
import com.academic.as.demo.models.Course;
import com.academic.as.demo.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/courses")
@RestController
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @PostMapping(value = "/add")
    public BaseResponse addCourse(@RequestBody Course course) {
        return coursesService.addCourseByDepName(course);
    }

    @GetMapping(value = "/all")
    public CoursesResponse getAllCourses() {
        return coursesService.getAllCourses();
    }

}