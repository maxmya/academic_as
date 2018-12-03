package com.academic.as.demo.config;

import com.academic.as.demo.api.requests.CourseInstanceRequest;
import com.academic.as.demo.controllers.web.models.Group;
import com.academic.as.demo.firebase.FirebaseConstants;
import com.academic.as.demo.firebase.FirebaseHelper;
import com.academic.as.demo.models.Course;
import com.academic.as.demo.models.CourseInstance;
import com.academic.as.demo.models.Semester;
import com.academic.as.demo.repositories.CourseInstanceRepository;
import com.academic.as.demo.repositories.CourseRepository;
import com.academic.as.demo.repositories.HallRepository;
import com.academic.as.demo.repositories.SemesterRepository;
import com.academic.as.demo.services.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Component
@Transactional
public class Automatic {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseInstanceRepository courseInstanceRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    HallRepository hallRepository;


    @Autowired
    CoursesService coursesService;

    @Autowired
    FirebaseHelper firebaseHelper;

    /**
     * this method runs every year in the beginning of month September to create Fall semester record
     * and creates its attributes of courses
     */
    //@Scheduled(cron = "0 0 12 1 9 ? *")
//    @Scheduled(fixedRate = 1000000000)
    public void generateFallSemester() {

        System.out.println("generating instances fall-" + Calendar.getInstance().get(Calendar.YEAR));
        // generate semester record
        Semester currentFallSemester = new Semester();
        currentFallSemester.setSemesterCode("fall-" + Calendar.getInstance().get(Calendar.YEAR));
        currentFallSemester.setStartDate(new Date());

       //    semesterRepository.save(currentFallSemester);

        createCourseInstanceForSemester(semesterRepository.findBySemesterCode(currentFallSemester.getSemesterCode()));
    }

    /**
     * @param semester generic for all semesters
     * @return list of course instances
     */
    private void createCourseInstanceForSemester(Semester semester) {

        List<Course> courses = courseRepository.findAll();
        for (Course c : courses) {

            System.out.println(" course instance of " + c.getName() + " is generated");

            CourseInstanceRequest courseInstanceRequest = new CourseInstanceRequest();
//            courseInstanceRequest.setStartTime(0l);
//            courseInstanceRequest.setEndTime(Instant.now().toEpochMilli());
            courseInstanceRequest.setCourseId(c.getId());
            courseInstanceRequest.setSemesterId(semester.getId());
            courseInstanceRequest.setInstructorsIds(new ArrayList<>());
            courseInstanceRequest.setStudentsIds(new ArrayList<>());
           firebaseHelper.createFirebaseGroupForCourseInstance(createdInstance.getId() + "", new Group(new ArrayList<>(), new Group.Metadata(createdInstance.getCourse().getName() + " " + semester.getSemesterCode())));
        }

    }



}
