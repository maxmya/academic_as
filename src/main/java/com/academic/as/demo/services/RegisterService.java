package com.academic.as.demo.services;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.enums.UserRole;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;


@Service
public class RegisterService {


    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AssistantRepository assistantRepository;
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SupervisorRepository supervisorRepository;


    public RegisterResponse addAdmin(Admin admin) {
        admin.getUser().setCreateDate(new Date());
        return add(supervisorRepository, admin, UserRole.ADMIN);
    }


    public RegisterResponse addProfessor(Professor professor) {
        professor.getUser().setCreateDate(new Date());
        return add(supervisorRepository, professor, UserRole.PROFESSOR);
    }


    public RegisterResponse addAssistant(Assistant assistant) {
        assistant.getUser().setCreateDate(new Date());
        return add(supervisorRepository, assistant, UserRole.ASSISTANT);
    }


    public RegisterResponse addSupervisor(Supervisor supervisor) {
        supervisor.getUser().setCreateDate(new Date());
        return add(supervisorRepository, supervisor, UserRole.SUPERVISOR);
    }

    public RegisterResponse addStudent(Student student) {
        student.getUser().setCreateDate(new Date());
        return add(studentRepository, student, UserRole.STUDENT);
    }

    public RegisterResponse addUser(User user) {
        user.setCreateDate(new Date());
        return add(userRepository, user, UserRole.SYSTEM);
    }

    private RegisterResponse add(JpaRepository repository, Object data, UserRole role) {
        RegisterResponse response = new RegisterResponse();
        try {
            repository.save(data);
            response.setCode("200");
            response.setMessage("SUCCESS");
            response.setUserRole(role);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
