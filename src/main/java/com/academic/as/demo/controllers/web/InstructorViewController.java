package com.academic.as.demo.controllers.web;


import com.academic.as.demo.api.responses.*;
import com.academic.as.demo.controllers.web.models.Task;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.InstructorRepository;
import com.academic.as.demo.repositories.ProfessorRepository;
import com.academic.as.demo.repositories.UserRepository;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.FirebaseGroupsService;
import com.academic.as.demo.services.GetUsersService;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class InstructorViewController {

    @Autowired
    GetUsersService usersService;

    @Autowired
    CoursesService coursesService;

    @Autowired
    SpecializationService specializationService;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FirebaseGroupsService firebaseGroupsService;


    @GetMapping("/get/{userName}/courses")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String getAllCoursesAssignedToHim(@PathVariable(value = "userName") String userName, Model model) {
        CoursesResponse response = new CoursesResponse();
        User user = userRepository.findByUsername(userName);
        if (instructorRepository.existsById(instructorRepository.findByUserId(user.getId()).getId())) {
            Instructor instructor = instructorRepository.getOne(instructorRepository.findByUserId(user.getId()).getId());
            List<CourseInstance> coursesInstance = instructor.getCourseInstances();
            if (coursesInstance.size() == 0) {
                response.setCode("400");
                response.setMessage("You do not have any assigned courses yet");
            } else {
                response.setCode("200");
                model.addAttribute("courseInstances", coursesInstance);
            }

        } else {
            response.setMessage("User with id : " + user.getId() + " not found");
        }
        model.addAttribute("response", response);
        return "view_course_instance_professor";
    }


    @GetMapping("/professor/instance/{ID}/students")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String getCourseInstanceStudents(@PathVariable(value = "ID") String id, Model model) {
        CourseInstanceResponse response = coursesService.getCourseInstance(Integer.parseInt(id));
        CourseInstance courseInstanceData = (CourseInstance) response.getData();
        if (response.getCode().equalsIgnoreCase("200")) {
            model.addAttribute("courseInstanceData", courseInstanceData);
        }
        model.addAttribute("response", response);
        return "view_course_instance_students_for_professor";
    }

    @GetMapping("/professor/instance/{ID}/instructors")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String getCourseInstanceInstructor(@PathVariable(value = "ID") String id, Model model) {
        CourseInstanceResponse response = coursesService.getCourseInstance(Integer.parseInt(id));
        CourseInstance courseInstanceData = (CourseInstance) response.getData();
        if (response.getCode().equalsIgnoreCase("200")) {
            model.addAttribute("courseInstanceData", courseInstanceData);
        }
        model.addAttribute("response", response);
        return "view_course_instance_intructors_for_professor";
    }

    @GetMapping("/student/{ID}/add/degree")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String assignDegree(@PathVariable(value = "ID") String id, Model model) {

        /*
         * degree id , course_instance_id ,
         * student id
         * id , student_id , quiz no
         *reg agrriagate
         *id 10% 30% 60% status repeation
         *
         * course_id , student_id , degree_id
         */
        return "assign_degree";
    }

    @GetMapping("/tasks/{id}")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String viewTasks(Model model, @PathVariable("id") String groupId) throws ExecutionException, InterruptedException {
        model.addAttribute("tasks", firebaseGroupsService.getAllTasks(groupId));
        model.addAttribute("group", groupId);
        return "task_list";
    }

    @GetMapping("/tasks/{group_id}/{task_id}")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String getSingleTask(@PathVariable("group_id") String groupId, @PathVariable("task_id") String taskId) {
        return "single_task";
    }


    @GetMapping("/tasks/{id}/add")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String addTaskView(Model model, @PathVariable("id") String groupId) {
        model.addAttribute("group", groupId);
        model.addAttribute("task", new Task());
        return "add_task";
    }

    @PostMapping("/tasks/{id}/add")
    @PreAuthorize("hasRole('ROLE_PROFESSOR') or hasRole('ROLE_ASSISTENT')")
    public String addTask(@ModelAttribute("task") Task task, @PathVariable("id") String groupId, Model model) {
        model.addAttribute("group", groupId);
        firebaseGroupsService.postTask(task, groupId);
        return "add_task";
    }
}
