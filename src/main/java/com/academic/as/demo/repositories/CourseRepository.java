package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findById(int id);
    List<Course> findAllBySemester(String semester);
}
