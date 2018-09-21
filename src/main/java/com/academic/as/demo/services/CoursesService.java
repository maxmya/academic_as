package com.academic.as.demo.services;

import com.academic.as.demo.api.requests.CourseInstanceRequest;
import com.academic.as.demo.api.requests.CourseRegistrationRequest;
import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.api.responses.CoursesResponse;
import com.academic.as.demo.models.*;
import com.academic.as.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursesService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    public BaseResponse addCourseByDepName(Course course) {
        BaseResponse response = new BaseResponse();
        try {
            Department dp = departmentRepository.
                    findDepartmentByDepartmentName(course.getDepartment().getDepartmentName());
            List<Course> currentCourses = dp.getCourses();
            currentCourses.add(course);
            dp.setCourses(currentCourses);
            course.setDepartment(dp);
            courseRepository.save(course);
            response.setCode("200");
            response.setMessage("SUCCESS");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public CoursesResponse getAllCourses() {
        CoursesResponse response = new CoursesResponse();
        try {
            List<Course> coursesList = courseRepository.findAll();
            response.setCode("200");
            response.setMessage("SUCCESS");
            response.setData(coursesList);
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public BaseResponse addCourseInstance(CourseInstanceRequest requestBody) {
        BaseResponse response = new BaseResponse();
        try {
            CourseInstance newCourseInstance = new CourseInstance();
            newCourseInstance.setStartTime(requestBody.getStartTime());
            newCourseInstance.setEndTime(requestBody.getEndTime());
            newCourseInstance.setType(requestBody.getType());
            Hall courseHall = hallRepository.getOne(requestBody.getHallId());
            Course instanceImage = courseRepository.getOne(requestBody.getCourseId());
            Specialization specialization = specializationRepository.getOne(requestBody.getSpecializationId());
            Semester courseSemester = semesterRepository.getOne(requestBody.getSemesterId());
            List<Instructor> instructors = new ArrayList<>();
            for (int instructorId : requestBody.getInstructorsIds()) {
                instructors.add(instructorRepository.getOne(instructorId));
            }
            List<Student> students = new ArrayList<>();
            for (int studentId : requestBody.getStudentsIds()) {
                students.add(studentRepository.getOne(studentId));
            }
            newCourseInstance.setHall(courseHall);
            newCourseInstance.setCourse(instanceImage);
            newCourseInstance.setSpecialization(specialization);
            newCourseInstance.setInstructors(instructors);
            newCourseInstance.setSemester(courseSemester);
            newCourseInstance.setStudents(students);
            courseHall.addCourse(newCourseInstance);
            instanceImage.addCourse(newCourseInstance);
            specialization.addCourse(newCourseInstance);
            courseSemester.addCourse(newCourseInstance);
            courseInstanceRepository.save(newCourseInstance);
            response.setCode("200");
            response.setMessage("SUCCESSES");
        } catch (Exception e) {
            response.setCode("400");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public BaseResponse registerCoursesToStudent(CourseRegistrationRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            Student selectedStudent = studentRepository.getOne(request.getStudentId());
            for (int instanceId : request.getCourseInstancesIds()) {
                CourseInstance instance = courseInstanceRepository.getOne(instanceId);
                selectedStudent.registerCourse(instance);
            }
            studentRepository.save(selectedStudent);
            response.setCode("200");
            response.setMessage("SUCCESS");
        } catch (Exception e) {
            response.setCode("400");
            e.printStackTrace();
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
