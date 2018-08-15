package com.academic.as.demo.services;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.models.UserEntity;
import com.academic.as.demo.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;

    public RegisterResponse addUser(UserEntity user) {
        RegisterResponse response = new RegisterResponse();
        if (registerRepository.existsById(user.getId())) {
            response.setCode("400");
            response.setMessage("something went wrong , this id is related to another record");
        } else {
            registerRepository.save(user);
            response.setCode("200");
            response.setMessage("Success");
        }
        return response;

    }

}
