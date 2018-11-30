package com.academic.as.demo.models;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "semester")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Semester {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "semester_code")
    private String semesterCode;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public Semester() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonBackReference(value = "courseInstances")
    @OneToMany(mappedBy = "semester",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    private List<CourseInstance> courseInstances = new ArrayList<>();

    public void addCourse(CourseInstance courseInstance) {
        courseInstances.add(courseInstance);
    }

    public List<CourseInstance> getCourseInstances() {
        return courseInstances;
    }

    public void setCourseInstances(List<CourseInstance> courseInstances) {
        this.courseInstances = courseInstances;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
