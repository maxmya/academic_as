package com.academic.as.demo.models;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hall")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hall {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "latitude")
    private Float latitude;

    @NotNull
    @Column(name = "longitude")
    private Float longitude;

    @NotNull
    @Column(name = "capacity")
    private Integer capacity;

    @JsonBackReference(value = "courseInstances")
    @OneToMany(mappedBy = "hall",
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

    public Hall() {
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
