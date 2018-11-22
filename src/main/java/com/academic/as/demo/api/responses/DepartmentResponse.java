package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Department;

import java.util.List;

public class DepartmentResponse extends BaseResponse {

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
