package com.academic.as.demo.api.responses;


import com.academic.as.demo.models.CourseInstance;

import java.util.List;

public class CourseInstanceResponse extends BaseResponse {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
