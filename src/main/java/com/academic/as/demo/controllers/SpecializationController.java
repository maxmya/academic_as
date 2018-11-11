package com.academic.as.demo.controllers;


import com.academic.as.demo.api.requests.SpecializationRequest;
import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.DepartmentResponse;
import com.academic.as.demo.api.responses.SpecializationResponse;
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

    @GetMapping("/departments")
    public DepartmentResponse getAllDepartments() {
        return specializationService.getAllDepartments();
    }

    @GetMapping("/specializations")
    public SpecializationResponse getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }

    @PostMapping("/department/add")
    public BaseResponse addDepartment(@RequestBody Department department) {
        return specializationService.addDepartment(department);
    }

    /**
     * @param specialization "specialization":
     *                       {"specialityName":"math_special"},
     *                       "departments":[
     *                       {"departmentName":"mathematics"}
     *                       ]
     * @return base response
     */
    @PostMapping("/speciality/add")
    public BaseResponse addSpeciality(@RequestBody SpecializationRequest specialization) {
        return specializationService.addSpecialization(specialization);
    }
}
