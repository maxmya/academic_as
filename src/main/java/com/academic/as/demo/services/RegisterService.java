package com.academic.as.demo.services;

import com.academic.as.demo.api.responses.RegisterResponse;
import com.academic.as.demo.enums.UserRoles;
import com.academic.as.demo.firebase.FirebaseHelper;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.*;
import com.google.firebase.auth.FirebaseAuthException;
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

    @Autowired
    private AuthGroupRepository authGroupRepository;

    private BCryptPasswordEncoder encoder;

    @Autowired
    FirebaseHelper firebaseHelper;

    public RegisterService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public RegisterResponse addAdmin(Admin admin) {
        admin.getUser().setCreateDate(new Date());
        String plainPassword = admin.getUser().getPassword();
        admin.getUser().setPassword(encoder.encode(admin.getUser().getPassword()));
        return add(adminRepository, admin, admin.getUser().getUsername(),
                plainPassword, UserRoles.ADMIN);
    }


    public RegisterResponse addProfessor(Professor professor) {
        professor.getUser().setCreateDate(new Date());
        String plainPassword = professor.getUser().getPassword();
        professor.getUser().setPassword(encoder.encode(professor.getUser().getPassword()));
        return add(professorRepository, professor, professor.getUser().getUsername(),
                plainPassword, UserRoles.PROFESSOR);
    }


    public RegisterResponse addAssistant(Assistant assistant) {
        assistant.getUser().setCreateDate(new Date());
        String plainPassword = assistant.getUser().getPassword();
        assistant.getUser().setPassword(encoder.encode(assistant.getUser().getPassword()));
        return add(assistantRepository, assistant, assistant.getUser().getUsername(),
                plainPassword, UserRoles.ASSISTANT);
    }


    public RegisterResponse addSupervisor(Supervisor supervisor) {
        supervisor.getUser().setCreateDate(new Date());
        String plainPassword = supervisor.getUser().getPassword();
        supervisor.getUser().setPassword(encoder.encode(supervisor.getUser().getPassword()));
        return add(supervisorRepository, supervisor, supervisor.getUser().getUsername(),
                plainPassword, UserRoles.SUPERVISOR);
    }

    public RegisterResponse addStudent(Student student) {
        student.getUser().setCreateDate(new Date());
        String plainPassword = student.getUser().getPassword();
        student.getUser().setPassword(encoder.encode(student.getUser().getPassword()));
        return add(studentRepository, student, student.getUser().getUsername(),
                plainPassword, UserRoles.STUDENT);
    }

    // add only root user we can remove it
    public RegisterResponse addUser(User user) {
        user.setCreateDate(new Date());
        String plainPassword = user.getPassword();
        user.setPassword(encoder.encode(user.getPassword()));
        return add(userRepository, user, user.getUsername(), plainPassword, UserRoles.SYSTEM);
    }

    private RegisterResponse add(JpaRepository repository, Object data, String username, String plainPassword, UserRoles role) {
        RegisterResponse response = new RegisterResponse();
        try {
            firebaseHelper.createUser(username, plainPassword);
            repository.save(data);
            authGroupRepository.save(new AuthGroup(role.name(), username));
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
