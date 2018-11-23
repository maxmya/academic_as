package com.academic.as.demo.config;

import com.academic.as.demo.api.requests.CourseInstanceRequest;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
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

    /**
     * this method runs every year in the beginning of month September to create Fall semester record
     * and creates its attributes of courses
     */
    //@Scheduled(cron = "0 0 12 1 9 ? *")
    @Scheduled(fixedRate = 1000000000)
    public void generateFallSemester() {

        System.out.println("generating instances fall-" + Calendar.getInstance().get(Calendar.YEAR));
        // generate semester record
        Semester currentFallSemester = new Semester();
        currentFallSemester.setSemesterCode("fall-" + Calendar.getInstance().get(Calendar.YEAR));
        currentFallSemester.setStartDate(new Date());

        //   semesterRepository.save(currentFallSemester);

        createCourseInstanceForSemester(semesterRepository.findBySemesterCode(currentFallSemester.getSemesterCode()));
    }

    /**
     * @param semester generic for all semesters
     * @return list of course instances
     */
    private List<CourseInstance> createCourseInstanceForSemester(Semester semester) {
        List<CourseInstance> courseInstances = new ArrayList<>();

        List<Course> courses = courseRepository.findAll();
        for (Course c : courses) {

            System.out.println(" course instance of " + c.getName() + " is generated");

            CourseInstanceRequest courseInstanceRequest = new CourseInstanceRequest();
            courseInstanceRequest.setCourseId(c.getId());
            courseInstanceRequest.setSemesterId(semester.getId());
            courseInstanceRequest.setInstructorsIds(new ArrayList<>());
            courseInstanceRequest.setStudentsIds(new ArrayList<>());
            courseInstanceRequest.setHallId(1);
            courseInstanceRequest.setSpecializationId(1);
            coursesService.addCourseInstance(courseInstanceRequest);

        }

        return courseInstances;
    }

}
