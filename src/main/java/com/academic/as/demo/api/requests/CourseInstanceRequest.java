package com.academic.as.demo.api.requests;

import com.academic.as.demo.enums.CourseInstanceType;

import java.sql.Time;
import java.util.List;

public class CourseInstanceRequest {

    private Time startTime;
    private Time endTime;
    private String type;
    private Integer courseId;
    private Integer hallId;
    private Integer specializationId;
    private Integer semesterId;
    private List<Integer> instructorsIds;

    CourseInstanceRequest() {
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public Integer getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Integer specializationId) {
        this.specializationId = specializationId;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public List<Integer> getInstructorsIds() {
        return instructorsIds;
    }

    public void setInstructorsIds(List<Integer> instructorsIds) {
        this.instructorsIds = instructorsIds;
    }
}

