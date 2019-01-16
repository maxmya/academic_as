package com.academic.as.demo.config;

import com.academic.as.demo.controllers.web.models.Group;
import com.academic.as.demo.firebase.FirebaseHelper;
import com.academic.as.demo.models.Course;
import com.academic.as.demo.models.CourseInstance;
import com.academic.as.demo.models.Hall;
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


import java.util.*;

@Component
@Transactional
public class AutomationScheduler {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseInstanceRepository courseInstanceRepository;

    @Autowired
    SemesterRepository semesterRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    FirebaseHelper firebaseHelper;


    /**
     * this method runs every year in the beginning of month September to create Fall semester record
     * and creates its attributes of courses
     */
    @Scheduled(fixedRate = 1000000000)
    public void generateAll() {
        System.out.println("generating instances test-" + Calendar.getInstance().get(Calendar.YEAR));
        // generate semester record
        Semester currentFallSemester = new Semester();
        currentFallSemester.setSemesterCode("test-" + UUID.randomUUID() + "-" + Calendar.getInstance().get(Calendar.YEAR));
        currentFallSemester.setStartDate(new Date());
        semesterRepository.save(currentFallSemester);
       // generateAutomatedTimeTable(courseRepository.findAll(), hallRepository.findAll(), currentFallSemester);
    }

    @Scheduled(cron = "0 12 1 9 12 ?")
    public void generateFallSemester() {
        System.out.println("generating instances fall-" + Calendar.getInstance().get(Calendar.YEAR));
        // generate semester record
        Semester currentFallSemester = new Semester();
        currentFallSemester.setSemesterCode("fall-" + Calendar.getInstance().get(Calendar.YEAR));
        currentFallSemester.setStartDate(new Date());
        semesterRepository.save(currentFallSemester);
        generateAutomatedTimeTable(courseRepository.findAllBySemester("fall"), hallRepository.findAll(), currentFallSemester);
    }

    @Scheduled(cron = "0 12 1 3 12 ?")
    public void generateSpringSemester() {
        System.out.println("generating instances spring-" + Calendar.getInstance().get(Calendar.YEAR));
        // generate semester record
        Semester currentFallSemester = new Semester();
        currentFallSemester.setSemesterCode("spring-" + Calendar.getInstance().get(Calendar.YEAR));
        currentFallSemester.setStartDate(new Date());
        semesterRepository.save(currentFallSemester);
        generateAutomatedTimeTable(courseRepository.findAllBySemester("spring"), hallRepository.findAll(), currentFallSemester);
    }

    @Scheduled(cron = "0 12 1 7 12 ?")
    public void generateSummerSemester() {
        System.out.println("generating instances summer-" + Calendar.getInstance().get(Calendar.YEAR));
        // generate semester record
        Semester currentFallSemester = new Semester();
        currentFallSemester.setSemesterCode("summer-" + Calendar.getInstance().get(Calendar.YEAR));
        currentFallSemester.setStartDate(new Date());
        semesterRepository.save(currentFallSemester);
        generateAutomatedTimeTable(courseRepository.findAllBySemester("summer"), hallRepository.findAll(), currentFallSemester);
    }

    private List<CourseInstance> generateAutomatedTimeTable(List<Course> c, List<Hall> h, Semester semester) {
        Collections.sort(c);
        List<CourseInstance> courseInstances = InstantiateCourses(c, semester);
        PriorityQueue<Timing> queue = new PriorityQueue<Timing>();
        Timing[] timings = new Timing[h.size() * 6];
        for (int i = 0; i < timings.length; i++) {
            timings[i] = new Timing();
        }
        int idx = 0;
        for (int i = 0; i < h.size(); i++) {
            for (int j = 0; j < 6; j++) {
                timings[idx].setDay(j);
                timings[idx].setHallIdx(i);
                timings[idx].setRemainTime(12);
                queue.add(timings[idx]);
                idx++;
            }
        }
        Timing timing = new Timing();
        for (int i = 0; i < courseInstances.size(); i++) {
            if (queue.isEmpty()) return new ArrayList<CourseInstance>();
            timing = queue.poll();
            courseInstances.get(i).setDay(getDayByIndex(timing.getDay()));
            courseInstances.get(i).setStartTime(8 + (12 - timing.getRemainTime()));
            courseInstances.get(i).setEndTime(courseInstances.get(i).getStartTime() + 2);
            timing.setRemainTime(timing.getRemainTime() - 2);
            courseInstances.get(i).setHall(h.get(timing.getHallIdx()));
            if (timing.getRemainTime() >= 2) {
                queue.add(timing);
            }
            courseInstanceRepository.save(courseInstances.get(i));
            firebaseHelper.createFirebaseGroupForCourseInstance(
                    courseInstances.get(i).getId() + "",
                    new Group(new ArrayList<>(), new Group.Metadata(courseInstances.get(i).getCourse().getName() + " " + semester.getSemesterCode()))
            );
        }

        return courseInstances;
    }

    private String getDayByIndex(int idx) {
        if (idx == 0) return "saturday";
        else if (idx == 1) return "sunday";
        else if (idx == 2) return "monday";
        else if (idx == 3) return "tuesday";
        else if (idx == 4) return "wednesday";
        else if (idx == 5) return "thursday";
        else return "";
    }

    private List<CourseInstance> InstantiateCourses(List<Course> courses, Semester semester) {
        List<CourseInstance> courseInstances = new ArrayList<>();

        for (Course c : courses) {
            System.out.println(" create empty instance of " + c.getName());
            CourseInstance lecture = new CourseInstance();
            CourseInstance train = new CourseInstance();
            lecture.setSemester(semester);
            train.setSemester(semester);
            lecture.setType("lecture");
            train.setType("training");
            lecture.setCourse(c);
            train.setCourse(c);
            courseInstances.add(lecture);
            courseInstances.add(train);

        }

        return courseInstances;
    }

}
