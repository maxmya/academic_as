package com.academic.as.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "registration_info")
public class Degree implements Serializable {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuizDegree() {
        return quizDegree;
    }

    public void setQuizDegree(Integer quizDegree) {
        this.quizDegree = quizDegree;
    }

    public Integer getLabDegree() {
        return labDegree;
    }

    public void setLabDegree(Integer labDegree) {
        this.labDegree = labDegree;
    }

    public Integer getFinalDegree() {
        return finalDegree;
    }

    public void setFinalDegree(Integer finalDegree) {
        this.finalDegree = finalDegree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }

    public Set<CourseStudentDegreeTernaryRelation> getCourseStudentDegreeTernaryRelations() {
        return courseStudentDegreeTernaryRelations;
    }

    public void setCourseStudentDegreeTernaryRelations(Set<CourseStudentDegreeTernaryRelation> courseStudentDegreeTernaryRelations) {
        this.courseStudentDegreeTernaryRelations = courseStudentDegreeTernaryRelations;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @NotNull
    @Column(name = "10%")
    private Integer quizDegree;


    @NotEmpty
    @NotNull
    @Column(name = "30%")
    private Integer labDegree;

    @NotEmpty
    @NotNull
    @Column(name = "60%")
    private Integer finalDegree;


    @NotEmpty
    @NotNull
    @Column(name = "status")
    private String status;


    @NotEmpty
    @NotNull
    @Column(name = "repetition")
    private Integer repetition;

    @OneToMany(mappedBy = "degree", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<CourseStudentDegreeTernaryRelation> courseStudentDegreeTernaryRelations;

}
