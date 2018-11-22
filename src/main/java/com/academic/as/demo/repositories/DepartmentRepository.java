package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findDepartmentByDepartmentName(String departmentName);
    Department findById(int id);
}
