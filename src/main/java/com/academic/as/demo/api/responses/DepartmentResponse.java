package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Department;

import java.util.List;

public class DepartmentResponse extends BaseResponse {

    private List<Department> data;

    public void setData(List<Department> data) {
        this.data = data;
    }

    public List<Department> getData() {
        return data;
    }
}
