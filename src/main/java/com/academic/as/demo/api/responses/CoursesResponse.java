package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Course;
import lombok.Data;

import java.util.List;

@Data
public class CoursesResponse extends BaseResponse {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
