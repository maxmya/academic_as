package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT ri.* ,csd.* FROM registration_info ri" +
            " LEFT JOIN course_student_degrees csd ON ri.id = csd.degree_id" +
            " WHERE csd.course_instance_id = :#{#instanceId }")
    List<Degree> getAllDegreeOfCourseInstance(@Param("instanceId") Integer instanceId);
}
