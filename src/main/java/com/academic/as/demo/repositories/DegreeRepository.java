package com.academic.as.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Course, Integer> {

}
