package com.academic.as.demo.models;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
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

}