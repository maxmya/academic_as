package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Course;

import java.util.List;

public class CoursesResponse extends BaseResponse {

    private List<Course> data;

    public List<Course> getData() {
        return data;
    }

    public void setData(List<Course> data) {
        this.data = data;
    }
}
