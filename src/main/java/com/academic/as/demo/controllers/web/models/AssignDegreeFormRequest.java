package com.academic.as.demo.controllers.web.models;

import lombok.Data;

    @Data
public class AssignDegreeFormRequest {
    private Integer degree;
    private String degreeType;
    private Integer studentId;
    private Integer courseInstanceId;
}
