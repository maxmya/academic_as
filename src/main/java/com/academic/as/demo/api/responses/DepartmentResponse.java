package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Department;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse extends BaseResponse {

    private List<Department> data;

}
