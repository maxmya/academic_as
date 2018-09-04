package com.academic.as.demo.controllers;


import com.academic.as.demo.api.requests.SpecializationRequest;
import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("specialization")
@RestController
public class SpecializationController {

    @Autowired
    SpecializationService specializationService;

    @PostMapping("/department/add")
    public BaseResponse addDepartment(@RequestBody Department department) {
        return specializationService.addDepartment(department);
    }

    @PostMapping("/speciality/add")
    public BaseResponse addSpeciality(@RequestBody SpecializationRequest specialization) {
        return specializationService.addSpecialization(specialization);
    }
}
