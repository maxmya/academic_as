package com.academic.as.demo.controllers;

import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.models.*;
import com.academic.as.demo.services.UpdateProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/update/profile")
@RestController
public class UpdateProfileController {

    @Autowired
    UpdateProfileService updateprofileService;

    @PutMapping(value = "/admin/{id}")
    public BaseResponse UpdateAdminProfile(@RequestBody Admin admin,int id) {
        return updateprofileService.updateAdmin(admin,id);
    }

    @PutMapping(value = "/student/{id}")
    public BaseResponse UpdateStudentProfile(@RequestBody Student student , int id) {
        return updateprofileService.updateStudent(student,id);
    }

    @PutMapping(value = "/supervisor/{id}")
    public BaseResponse UpdateSupervisorProfile(@RequestBody Supervisor supervisor , int id) {
        return updateprofileService.updateSupervisor(supervisor,id);
    }

    @PutMapping(value = "/assistant/{id}")
    public BaseResponse UpdateAssistantProfile(@RequestBody Assistant assistant , int id) {
        return updateprofileService.updateAssistant(assistant,id);
    }

    @PutMapping(value = "/professor/{id}")
    public BaseResponse UpdateProfessorProfile(@RequestBody Professor professor , int id) {
        return updateprofileService.updateProfessor(professor,id);
    }

    @PutMapping(value = "/user/{id}")
    public BaseResponse UpdateUserProfile(@RequestBody User user , int id) {

        return updateprofileService.updateUser(user,id);
    }


}
