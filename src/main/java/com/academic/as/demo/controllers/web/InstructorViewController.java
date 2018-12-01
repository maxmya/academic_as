package com.academic.as.demo.controllers.web;


import com.academic.as.demo.api.responses.*;
import com.academic.as.demo.controllers.web.models.AssignDegreeFormRequest;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.*;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.GetUsersService;
import com.academic.as.demo.services.RegisterService;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InstructorViewController {

    @Autowired
    private GetUsersService usersService;

    @Autowired
    private CoursesService coursesService;


    @Autowired
    private CourseInstanceRepository courseInstanceRepository;


    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterService registerService;

    @GetMapping("/professor/get/{userName}/courses")
    public String getAllCoursesAssignedToHim(@PathVariable(value="userName") String userName,Model model) {
        CoursesResponse response = new CoursesResponse();
        User user =  userRepository.findByUsername(userName);
        if(instructorRepository.existsById(instructorRepository.findByUserId(user.getId()).getId())){
            Instructor instructor = instructorRepository.getOne(instructorRepository.findByUserId(user.getId()).getId());
            List<CourseInstance> coursesInstance = instructor.getCourseInstances();
            if(coursesInstance.size() == 0){
                response.setCode("400");
                response.setMessage("You do not have any assigned courses yet");
            }else {
                response.setCode("200");
                model.addAttribute("courseInstances" , coursesInstance);
            }

        }else{
            response.setMessage("User with id : " + user.getId() + " not found");
        }
        model.addAttribute("response" , response);
        return "view_course_instance_professor";
    }


    @GetMapping("/professor/instance/{ID}/students")
    public String getCourseInstanceStudents(@PathVariable(value="ID") String id, Model model) {
        CourseInstanceResponse response =  coursesService.getCourseInstance(Integer.parseInt(id));
        CourseInstance  courseInstanceData = (CourseInstance) response.getData();
        if(response.getCode().equalsIgnoreCase("200")){
            model.addAttribute("courseInstanceData" , courseInstanceData);
        }
        model.addAttribute("response" , response);
        return "view_course_instance_students_for_professor";
    }

    @GetMapping("/professor/instance/{ID}/instructors")
    public String getCourseInstanceInstructor(@PathVariable(value="ID") String id, Model model) {
        CourseInstanceResponse response =  coursesService.getCourseInstance(Integer.parseInt(id));
        CourseInstance  courseInstanceData = (CourseInstance) response.getData();
        if(response.getCode().equalsIgnoreCase("200")){
            model.addAttribute("courseInstanceData" , courseInstanceData);
        }
        model.addAttribute("response" , response);
        return "view_course_instance_intructors_for_professor";
    }

    @GetMapping("/instance/{InstanceId}/student/{ID}/add/degree")
    public String assignDegreeView(@PathVariable(value="InstanceId") String instanceId,@PathVariable(value="ID") String id, Model model) {

        Student student = studentRepository.getOne(Integer.parseInt(id));
        CourseInstance courseInstance =  courseInstanceRepository.getOne(Integer.parseInt(instanceId));
        model.addAttribute("student", student);
        model.addAttribute("assignDegree", new AssignDegreeFormRequest());
        model.addAttribute("courseInstance", courseInstance);
        return "assign_degree";
    }

    @PostMapping("/instance/{InstanceId}/student/{ID}/add/degree")
    public String assignDegree(@PathVariable(value="InstanceId") String instanceId,@PathVariable(value="ID") String id, @ModelAttribute("assignDegree") AssignDegreeFormRequest assignDegreeFormRequest, Model model) {

        assignDegreeFormRequest.setStudentId(Integer.parseInt(id));
        assignDegreeFormRequest.setCourseInstanceId(Integer.parseInt(instanceId));
        System.out.println(assignDegreeFormRequest);
        CoursesResponse coursesResponse = coursesService.SaveDegree(assignDegreeFormRequest);
        if(coursesResponse.getCode().equalsIgnoreCase("200")){
            return "404";
        }
        model.addAttribute("response", coursesResponse);
        System.out.println(studentRepository.existsById(assignDegreeFormRequest.getStudentId()));
        System.out.println(courseInstanceRepository.existsById(assignDegreeFormRequest.getCourseInstanceId()));
        model.addAttribute("student", studentRepository.getOne(assignDegreeFormRequest.getStudentId()));
        model.addAttribute("courseInstance", courseInstanceRepository.getOne(assignDegreeFormRequest.getCourseInstanceId()));

        return "assign_degree";
    }
}
