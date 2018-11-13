package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Course;
import lombok.Data;

import java.util.List;

@Data
public class CoursesResponse extends BaseResponse {

    private List<Course> data;

}
