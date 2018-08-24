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
public class UpdateProfileService {


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
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }

        return response;
    }

    public BaseResponse updateStudent(Student student, int id) {
        BaseResponse response = new BaseResponse();
        try {
            Student selectedStudent = studentRepository.findById(id);
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

    public BaseResponse updateSupervisor(Supervisor supervisor, int id) {
        BaseResponse response = new BaseResponse();
        try {
            Supervisor selectedSupervisor = supervisorRepository.findById(id);
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

    public BaseResponse updateAssistant(Assistant assistant, int id) {
        BaseResponse response = new BaseResponse();
        try {
            Assistant selectedAssistant = assistantRepository.findById(id);
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

    public BaseResponse updateProfessor(Professor professor, int id) {
        BaseResponse response = new BaseResponse();
        try {
            Professor selectedprofessor = professorRepository.findById(id);
            selectedprofessor.getUser().update(professor.getUser());
            professorRepository.save(selectedprofessor);
            response.setCode("200");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }

        return response;
    }

//    public BaseResponse updateUser(User user, int id) {
//        BaseResponse response = new BaseResponse();
//        try {
//            User selectedUser = userRepository.findById(id);
//            selectedUser.get
//            userRepository.save(selectedUser);
//            response.setCode("200");
//            response.setMessage("Success");
//        } catch (Exception e) {
//            response.setCode("400");
//            response.setMessage(e.getMessage());
//        }
//
//        return response;
//    }
}


