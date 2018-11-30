package com.academic.as.demo.api.requests;


import lombok.Data;

import java.util.List;

@Data
public class CourseRegistrationRequest {

    private Integer studentId;
    private List<Integer> courseInstancesIds;

}
