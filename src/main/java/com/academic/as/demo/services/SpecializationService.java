package com.academic.as.demo.services;

import com.academic.as.demo.api.requests.SpecializationRequest;
import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.models.Specialization;
import com.academic.as.demo.repositories.DepartmentRepository;
import com.academic.as.demo.repositories.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    SpecializationRepository specializationRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public BaseResponse addDepartment(Department department) {
        BaseResponse response = new BaseResponse();
        try {
            departmentRepository.save(department);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public BaseResponse addSpecialization(SpecializationRequest specialization) {
        BaseResponse response = new BaseResponse();
        try {
            Specialization newSp = specialization.getSpecialization();
            specialization.setDepartments(specialization.getDepartments());
            for (Department dps : specialization.getDepartments()) {
                Department current = departmentRepository.findDepartmentByDepartmentName(dps.getDepartmentName());
                if (current == null) {
                    response.setCode("400");
                    response.setMessage("ERROR , department " + dps.getDepartmentName() + " is not created");
                    return response;
                }
                List<Specialization> sp = current.getSpecSializations();
                sp.add(specialization.getSpecialization());
                current.setSpecializations(sp);
            }
            specializationRepository.save(newSp);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
