package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {

    Semester findBySemesterCode(String semesterCode);
}
