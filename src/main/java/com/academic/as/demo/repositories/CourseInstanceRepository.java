package com.academic.as.demo.repositories;

import com.academic.as.demo.models.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Integer> {

    @Query("select i from CourseInstance i where i.specialization.id = :dep_id")
    List<CourseInstance> getCourseInstanceBySpecializationId(@Param("dep_id") Integer id);
}
