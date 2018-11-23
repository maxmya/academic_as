package com.academic.as.demo.api.requests;

import lombok.Data;

import java.sql.Time;
import java.time.Instant;
import java.util.List;

@Data
public class CourseInstanceRequest {

    private Instant startTime;
    private Instant endTime;
    private String type;
    private Integer courseId;
    private Integer hallId;
    private Integer specializationId;
    private Integer semesterId;
    private List<Integer> instructorsIds;
    private List<Integer> studentsIds;

}


