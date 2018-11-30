package com.academic.as.demo.controllers;

import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.models.*;
import com.academic.as.demo.services.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/update/")
@RestController
public class UpdateUserController {

    @Autowired
    UpdateUserService updateUserService;

    @PutMapping(value = "/admin/{id}")
    public BaseResponse updateAdminUser(@RequestBody Admin admin, @PathVariable("id") Integer id) {
        return updateUserService.updateAdmin(admin, id);
    }

    @PutMapping(value = "/student/{id}")
    public BaseResponse updateStudentUser(@RequestBody Student student, @PathVariable("id") Integer id) {
        return updateUserService.updateStudent(student, id);
    }

    @PutMapping(value = "/supervisor/{id}")
    public BaseResponse updateSupervisorUser(@RequestBody Supervisor supervisor, @PathVariable("id") Integer id) {
        return updateUserService.updateSupervisor(supervisor, id);
    }

    @PutMapping(value = "/assistant/{id}")
    public BaseResponse updateAssistantUser(@RequestBody Assistant assistant, @PathVariable("id") Integer id) {
        return updateUserService.updateAssistant(assistant, id);
    }

    @PutMapping(value = "/professor/{id}")
    public BaseResponse updateProfessorUser(@RequestBody Professor professor, @PathVariable("id") Integer id) {
        return updateUserService.updateProfessor(professor, id);
    }

    @PutMapping(value = "/user/{id}")
    public BaseResponse updateUserUser(@RequestBody User user, @PathVariable("id") Integer id) {
        return updateUserService.updateUser(user, id);
    }


}
