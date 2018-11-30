package com.academic.as.demo.services;

import com.academic.as.demo.api.responses.UsersResponse;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUsersService {


    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AssistantRepository assistantRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SupervisorRepository supervisorRepository;

    public UsersResponse getAdmins() {
        return getAll(adminRepository);
    }

    public UsersResponse getAdmin(Integer id) {
        return getOne(adminRepository, id);
    }

    public UsersResponse getProfessors() {
        return getAll(professorRepository);
    }

    public UsersResponse getProfessor(Integer id) {
        return getOne(professorRepository, id);
    }

    public UsersResponse getAssistants() {
        return getAll(assistantRepository);
    }

    public UsersResponse getAssistant(Integer id) {
        return getOne(assistantRepository, id);
    }

    public UsersResponse getStudents() {
        return getAll(studentRepository);
    }

    public UsersResponse getStudent(Integer id) {
        return getOne(studentRepository, id);
    }

    public UsersResponse getSupervisors() {
        return getAll(supervisorRepository);
    }

    public UsersResponse getSupervisor(Integer id) {
        return getOne(supervisorRepository, id);
    }

    // generic method to get all users of any type
    private UsersResponse getAll(JpaRepository repository) {
        UsersResponse response = new UsersResponse();
        try {
            List usersList = repository.findAll();
            response.setCode("200");
            response.setMessage("SUCCESS");
            response.setData(usersList);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    /*
    generic method to get single user with id if it's existed
    passed repo should be in the generic form <Object , Integer>
    */
    private UsersResponse getOne(JpaRepository repository, Integer id) {
        UsersResponse response = new UsersResponse();
        try {
            if (repository.existsById(id)) {
                response.setCode("200");
                response.setMessage("SUCCESS");
                response.setData(repository.getOne(id));
            } else {
                response.setCode("400");
                response.setMessage("User with id : " + id + " not found");
            }
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
