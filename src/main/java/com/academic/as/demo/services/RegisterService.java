package com.academic.as.demo.services;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.enums.UserRoles;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RegisterService {


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

    private BCryptPasswordEncoder encoder;

    public RegisterService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public RegisterResponse addAdmin(Admin admin) {
        admin.getUser().setCreateDate(new Date());
        admin.getUser().setPassword(encoder.encode(admin.getUser().getPassword()));
        return add(adminRepository, admin, UserRoles.ADMIN);
    }


    public RegisterResponse addProfessor(Professor professor) {
        professor.getUser().setCreateDate(new Date());
        professor.getUser().setPassword(encoder.encode(professor.getUser().getPassword()));
        return add(professorRepository, professor, UserRoles.PROFESSOR);
    }


    public RegisterResponse addAssistant(Assistant assistant) {
        assistant.getUser().setCreateDate(new Date());
        assistant.getUser().setPassword(encoder.encode(assistant.getUser().getPassword()));
        return add(assistantRepository, assistant, UserRoles.ASSISTANT);
    }


    public RegisterResponse addSupervisor(Supervisor supervisor) {
        supervisor.getUser().setCreateDate(new Date());
        supervisor.getUser().setPassword(encoder.encode(supervisor.getUser().getPassword()));
        return add(supervisorRepository, supervisor, UserRoles.SUPERVISOR);
    }

    public RegisterResponse addStudent(Student student) {
        student.getUser().setCreateDate(new Date());
        student.getUser().setPassword(encoder.encode(student.getUser().getPassword()));
        return add(studentRepository, student, UserRoles.STUDENT);
    }

    // add only root user we can remove it
    public RegisterResponse addUser(User user) {
        user.setCreateDate(new Date());
        user.setPassword(encoder.encode(user.getPassword()));
        return add(userRepository, user, UserRoles.SYSTEM);
    }

    private RegisterResponse add(JpaRepository repository, Object data, UserRoles role) {
        RegisterResponse response = new RegisterResponse();
        try {
            repository.save(data);
            response.setCode("200");
            response.setMessage("SUCCESS");
            response.setUserRoles(role);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
