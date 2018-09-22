package com.academic.as.demo.api.responses;


import com.academic.as.demo.models.CourseInstance;

import java.util.List;

public class CourseInstanceResponse extends BaseResponse {

    private List<CourseInstance> data;

    public List<CourseInstance> getData() {
        return data;
    }

    public void setData(List<CourseInstance> data) {
        this.data = data;
    }
}
