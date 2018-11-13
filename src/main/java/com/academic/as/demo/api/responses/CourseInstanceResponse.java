package com.academic.as.demo.api.responses;


import com.academic.as.demo.models.CourseInstance;
import lombok.Data;

import java.util.List;

@Data
public class CourseInstanceResponse extends BaseResponse {

    private List<CourseInstance> data;

}
