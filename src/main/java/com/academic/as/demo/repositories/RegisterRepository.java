package com.academic.as.demo.repositories;

import com.academic.as.demo.models.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends CrudRepository<UserEntity, Integer> {

}
