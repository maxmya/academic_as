package com.academic.as.demo.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course implements Serializable, Comparable<Course> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @NotNull
    @Column(name = "name")
    private String name;


    @NotEmpty
    @NotNull
    @Column(name = "code")
    private String code;


    @NotNull
    @Positive
    @Column(name = "required_points")
    private Integer requiredPoints;

    @NotNull
    @Positive
    @Column(name = "awarded_points")
    private Integer awardedPoints;


    @Column(name = "semester")
    private String semester;


    @JsonBackReference(value = "department")
    @ManyToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    @JoinColumn(name = "department_id")
    Department department;


    @JsonBackReference(value = "courseInstances")
    @OneToMany(mappedBy = "course",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.DETACH})
    private List<CourseInstance> courseInstances = new ArrayList<>();

    public void addCourse(CourseInstance courseInstance) {
        courseInstances.add(courseInstance);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(Integer requiredPoints) {
        this.requiredPoints = requiredPoints;
    }

    public Integer getAwardedPoints() {
        return awardedPoints;
    }

    public void setAwardedPoints(Integer awardedPoints) {
        this.awardedPoints = awardedPoints;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<CourseInstance> getCourseInstances() {
        return courseInstances;
    }

    public void setCourseInstances(List<CourseInstance> courseInstances) {
        this.courseInstances = courseInstances;
    }

    public int getLevel() {
        if (requiredPoints >= 100) return 4;
        else if (requiredPoints >= 80) return 3;
        else if (requiredPoints >= 60) return 2;
        else return 1;
    }

    @Override
    public int compareTo(Course o) {
        if (o.getDepartment().getId().equals(getDepartment().getId())) {
            if (o.getLevel() == getLevel()) {
                return 0;
            } else if (o.getLevel() < getLevel()) {
                return 1;
            } else return -1;
        } else if (o.getDepartment().getId() < getDepartment().getId()) {
            return 1;
        } else return -1;
    }
}
