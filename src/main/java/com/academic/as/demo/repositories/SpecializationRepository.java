package com.academic.as.demo.repositories;

import com.academic.as.demo.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    Specialization findSpecializationBySpecialityName(String specialityName);
}
