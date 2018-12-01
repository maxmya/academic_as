package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Course;
import com.academic.as.demo.models.CourseStudentDegreeTernaryRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentDegreeTernaryRelationRepository extends JpaRepository<CourseStudentDegreeTernaryRelation, Integer> {
 }
