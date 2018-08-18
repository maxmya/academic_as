package com.academic.as.demo.services;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.enums.UserRole;
import com.academic.as.demo.models.Admin;
import com.academic.as.demo.models.User;
import com.academic.as.demo.repositories.AdminRepository;
import com.academic.as.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RegisterService {


    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;


    public RegisterResponse addAdmin(Admin admin) {
        RegisterResponse response = new RegisterResponse();
        response.setUserRole(UserRole.ADMIN);
        try {
            adminRepository.save(admin);
            response.setCode("200");
            response.setMessage("SUCCESS");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }


    public RegisterResponse addUser(User user) {
        RegisterResponse response = new RegisterResponse();
        try {
            user.setCreateDate(new Date());
            userRepository.save(user);
            response.setCode("200");
            response.setMessage("SUCCESS");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
