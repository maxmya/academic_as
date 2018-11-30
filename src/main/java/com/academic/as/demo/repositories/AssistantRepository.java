package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Integer> {
    Assistant findById(int id);
}
