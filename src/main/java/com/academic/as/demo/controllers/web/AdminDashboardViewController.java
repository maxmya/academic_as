package com.academic.as.demo.controllers.web;

import com.academic.as.demo.api.responses.*;
import com.academic.as.demo.models.*;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.GetUsersService;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminDashboardViewController {


    @Autowired
    GetUsersService usersService;

    @Autowired
    CoursesService coursesService;

    @Autowired
    SpecializationService specializationService;


    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllUsers(Model model) {
        return "dashboard";
    }

    @GetMapping("/all/admins")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllAdmins(Model model) {
        List<Admin> admins =  (List<Admin>) usersService.getAdmins().getData();
        model.addAttribute("admins" , admins);
        return "all_admins";
    }

    @GetMapping("/all/supervisors")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllSupervisors(Model model) {

        model.addAttribute("supervisors" , (List<Supervisor>) usersService.getSupervisors().getData());
        return "all_supervisors";
    }

    @GetMapping("/all/students")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllStudents(Model model) {

        model.addAttribute("students" , (List<Student>) usersService.getStudents().getData());
        return "all_students";
    }

    @GetMapping("/all/professors")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllProfessors(Model model) {
        model.addAttribute("professors" , (List<Professor>) usersService.getProfessors().getData());
        return "all_professors";
    }

    @GetMapping("/all/assistants")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllAssistants(Model model) {

        model.addAttribute("assistants" , (List<Assistant>) usersService.getAssistants().getData());
        return "all_assistants";
    }

    @GetMapping("/user/{ID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getUser(@PathVariable(value="ID") int id, Model model) {
       UsersResponse response =  usersService.getOne(id);
       User userData = (User) response.getData();
       if(response.getCode() == "200"){
          model.addAttribute("userData" , userData);
       }
       model.addAttribute("response" , response);
       return "view_user";
    }

    @GetMapping("/all/courses")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AllCourses(Model model) {

        model.addAttribute("courses" , coursesService.getAllCourses());
        System.out.println(coursesService.getAllCourses());
        return "all_courses";
    }

    @GetMapping("/all/departments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AllDepartments(Model model) {

        model.addAttribute("departments" , specializationService.getAllDepartments());
        return "all_departments";
    }

    @GetMapping("/department/{ID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getDepartment(@PathVariable(value="ID") int id, Model model) {
        DepartmentResponse response =  specializationService.getDepartment(id);
        Department departmentData = (Department) response.getData();
        if(response.getCode() == "200"){
            model.addAttribute("departmentData" , departmentData);
        }
        model.addAttribute("response" , response);
        return "courses_specialization_dash";
    }
    @GetMapping("/department/{ID}/specialization")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getDepartmentSpecialization(@PathVariable(value="ID") int id, Model model) {
        DepartmentResponse response =  specializationService.getDepartment(id);
        Department departmentData = (Department) response.getData();
        if(response.getCode() == "200"){
            model.addAttribute("departmentData" , departmentData);
        }
        model.addAttribute("response" , response);
        return "view_department_specializatiom";
    }
    @GetMapping("/department/{ID}/course")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getDepartmentCourses(@PathVariable(value="ID") int id, Model model) {
        DepartmentResponse response =  specializationService.getDepartment(id);
        Department departmentData = (Department) response.getData();
        if(response.getCode() == "200"){
            model.addAttribute("departmentData" , departmentData);
        }
        model.addAttribute("response" , response);
        return "view_depatment_courses";

    }

    @GetMapping("/specialization/{ID}/departments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getSpecializationDepartments(@PathVariable(value="ID") int id, Model model) {
        SpecializationResponse response =  specializationService.getSpecialization(id);
        Specialization specializationData = (Specialization) response.getData();
        if(response.getCode() == "200"){
            model.addAttribute("specializationData" , specializationData);
        }
        model.addAttribute("response" , response);
        return "view_specialization_department";
    }

    @GetMapping("/course/{ID}/instances")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCourseInstances(@PathVariable(value="ID") int id, Model model) {
        CoursesResponse response =  coursesService.getCourse(id);
        Course  courseData = (Course) response.getData();
        if(response.getCode() == "200"){
            model.addAttribute("courseInstances" , courseData.getCourseInstances());
        }
        model.addAttribute("response" , response);
        return "view_course_intance_admin";
    }

    @GetMapping("/instance/{ID}/students")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCourseInstanceStudents(@PathVariable(value="ID") int id, Model model) {
        CourseInstanceResponse response =  coursesService.getCourseInstance(id);
        CourseInstance  courseInstanceData = (CourseInstance) response.getData();
        if(response.getCode() == "200"){
            model.addAttribute("courseInstanceData" , courseInstanceData);
        }
        model.addAttribute("response" , response);
        return "view_course_intance_students";
    }

    @GetMapping("/instance/{ID}/instructors")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCourseInstanceInstructor(@PathVariable(value="ID") int id, Model model) {
        CourseInstanceResponse response =  coursesService.getCourseInstance(id);
        CourseInstance  courseInstanceData = (CourseInstance) response.getData();
        if(response.getCode() == "200"){
                model.addAttribute("courseInstanceData" , courseInstanceData);
        }
        model.addAttribute("response" , response);
        return "view_course_intance_intructors";
    }
}
