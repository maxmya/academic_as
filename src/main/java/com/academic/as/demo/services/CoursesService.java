package com.academic.as.demo.services;

import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.CoursesResponse;
import com.academic.as.demo.models.Course;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.repositories.CourseRepository;
import com.academic.as.demo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public BaseResponse addCourseByDepName(Course course) {
        BaseResponse response = new BaseResponse();
        try {
            Department dp = departmentRepository.
                    findDepartmentByDepartmentName(course.getDepartment().getDepartmentName());
            List<Course> currentCourses = dp.getCourses();
            currentCourses.add(course);
            dp.setCourses(currentCourses);
            course.setDepartment(dp);
            courseRepository.save(course);
            response.setCode("200");
            response.setMessage("SUCCESS");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public CoursesResponse getAllCourses() {
        CoursesResponse response = new CoursesResponse();
        try {
            List<Course> coursesList = courseRepository.findAll();
            response.setCode("200");
            response.setMessage("SUCCESS");
            response.setData(coursesList);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }


}
