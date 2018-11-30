package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findById(int id);
    Student findByUserId(Integer userId);

}
