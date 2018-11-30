package com.academic.as.demo.api.requests;

import com.academic.as.demo.models.Department;
import com.academic.as.demo.models.Specialization;

import java.util.List;

public class SpecializationRequest {

    Specialization specialization;

    List<Department> departments;


    public SpecializationRequest() {
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
