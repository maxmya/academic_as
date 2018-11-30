package com.academic.as.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;

    @JsonBackReference(value = "courseInstances")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    })
    @JoinTable(name = "course_registration",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_instance_id"))
    private List<CourseInstance> courseInstances = new ArrayList<>();

    public Student() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void registerCourse(CourseInstance courseInstance) {
        courseInstances.add(courseInstance);
    }

    public List<CourseInstance> getCourseInstances() {
        return courseInstances;
    }

    public void setCourseInstances(List<CourseInstance> courseInstances) {
        this.courseInstances = courseInstances;
    }
}
