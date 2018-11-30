package com.academic.as.demo.api.requests;


import java.util.List;

public class CourseRegistrationRequest {

     private Integer studentId;
     private List<Integer> courseInstancesIds;

    public List<Integer> getCourseInstancesIds() {
        return courseInstancesIds;
    }

    public void setCourseInstancesIds(List<Integer> courseInstancesIds) {
        this.courseInstancesIds = courseInstancesIds;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }
}
