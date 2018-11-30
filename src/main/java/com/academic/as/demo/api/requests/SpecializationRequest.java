package com.academic.as.demo.api.requests;

import com.academic.as.demo.models.Department;
import com.academic.as.demo.models.Specialization;
import lombok.Data;

import java.util.List;

@Data
public class SpecializationRequest {

    Specialization specialization;

    List<Department> departments;

}
