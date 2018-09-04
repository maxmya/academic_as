package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {
    Supervisor findById(int id);

}
