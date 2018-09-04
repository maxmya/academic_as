package com.academic.as.demo.services;


import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.enums.UserRole;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Service
public class UpdateUserService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssistantRepository assistantRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;

    public BaseResponse updateAdmin(Admin admin, Integer id) {
        BaseResponse response = new BaseResponse();
        try {
            Admin selectedAdmin = adminRepository.getOne(id);
            selectedAdmin.getUser().update(admin.getUser());
            adminRepository.save(selectedAdmin);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    public BaseResponse updateStudent(Student student, Integer id) {
        BaseResponse response = new BaseResponse();
        try {
            Student selectedStudent = studentRepository.getOne(id);
            selectedStudent.getUser().update(student.getUser());
            studentRepository.save(selectedStudent);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    public BaseResponse updateSupervisor(Supervisor supervisor, Integer id) {
        BaseResponse response = new BaseResponse();
        try {
            Supervisor selectedSupervisor = supervisorRepository.getOne(id);
            selectedSupervisor.getUser().update(supervisor.getUser());
            supervisorRepository.save(selectedSupervisor);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    public BaseResponse updateAssistant(Assistant assistant, Integer id) {
        BaseResponse response = new BaseResponse();
        try {
            Assistant selectedAssistant = assistantRepository.getOne(id);
            selectedAssistant.getUser().update(assistant.getUser());
            assistantRepository.save(selectedAssistant);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    public BaseResponse updateProfessor(Professor professor, Integer id) {
        BaseResponse response = new BaseResponse();
        try {
            Professor selectedProfessor = professorRepository.getOne(id);
            selectedProfessor.getUser().update(professor.getUser());
            professorRepository.save(selectedProfessor);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    public BaseResponse updateUser(User user, Integer id) {
        BaseResponse response = new BaseResponse();
        try {
            User selectedUser = userRepository.getOne(id);
            selectedUser.update(user);
            userRepository.save(selectedUser);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }


}


