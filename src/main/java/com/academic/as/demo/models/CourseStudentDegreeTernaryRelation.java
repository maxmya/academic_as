package com.academic.as.demo.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course_student_degrees")
public class CourseStudentDegreeTernaryRelation implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseInstance getCourseInstance() {
        return courseInstance;
    }

    public void setCourseInstance(CourseInstance courseInstance) {
        this.courseInstance = courseInstance;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "course_instance_id")
    private CourseInstance courseInstance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "degree_id")
    private Degree degree;

}
