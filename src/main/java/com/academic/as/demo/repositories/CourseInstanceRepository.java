package com.academic.as.demo.repositories;

import com.academic.as.demo.models.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Integer> {
}
