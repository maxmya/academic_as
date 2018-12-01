

import com.academic.as.demo.api.requests.CourseInstanceRequest;
import com.academic.as.demo.config.models.Timing;
import com.academic.as.demo.models.Course;
import com.academic.as.demo.models.CourseInstance;
import com.academic.as.demo.models.Hall;
import com.academic.as.demo.models.Semester;
import com.academic.as.demo.repositories.CourseInstanceRepository;
import com.academic.as.demo.repositories.CourseRepository;
import com.academic.as.demo.repositories.HallRepository;
import com.academic.as.demo.repositories.SemesterRepository;
import com.academic.as.demo.services.CoursesService;

import io.grpc.netty.shaded.io.netty.util.internal.PriorityQueue;

import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

//           semesterRepository.save(currentFallSemester);

       
    }
    

    private List<CourseInstance> AutomatedTimeTable(List<Course> c, List<Hall> h, Semester semester){
    	Collections.sort(c);
    	ArrayList<CourseInstance> courseInstances = (ArrayList<CourseInstance>) InstantiateCourses(c, semester);
    	
    	PriorityQueue<Timing> queue = (PriorityQueue<Timing>) new LinkedList();
    	Timing timing = new Timing();
    	for(int i = 0; i < h.size(); i++) {
    		for(int j = 0; j < 6; j++) {
    			timing.setDay(j);
    			timing.setHallIdx(i);
    			timing.setRemainTime(12);
    			queue.add(timing);
    		} 
    	}
    	for(int i = 0; i < courseInstances.size(); i++) {
    		if(queue.isEmpty())return new ArrayList<CourseInstance>();
    		timing = queue.poll();
    		courseInstances.get(i).setDay(getTheDayByIndex(timing.getDay()));
    		courseInstances.get(i).setStartTime(8 + (12  - timing.getRemainTime()));
    		courseInstances.get(i).setEndTime(courseInstances.get(i).getStartTime() + 2);
    		timing.setRemainTime(timing.getRemainTime() - 2);
    		courseInstances.get(i).setHall(h.get(timing.getHallIdx()));
    		if(timing.getRemainTime() >= 2) {
    			queue.add(timing);
    		}
    	}
    	return courseInstances;
    }
    private String getTheDayByIndex(int idx) {
    	if(idx == 0)return "saturday";
    	else if(idx == 1) return "sunday";
    	else if(idx == 2) return "monday";
    	else if(idx == 3) return "tuesday";
    	else if(idx == 4) return "wednesday";
    	else if(idx == 5) return "thursday";
    	return "";
    }
    
    private List<CourseInstance> InstantiateCourses(List<Course> courses, Semester semester) {
        List<CourseInstance> courseInstances = new ArrayList<>();

        for (Course c : courses) {
            CourseInstance lecture = new CourseInstance();
            CourseInstance train = new CourseInstance();
            lecture.setSemester(semester);
            train.setSemester(semester);
            lecture.setCourse(c);
            train.setCourse(c);
            courseInstances.add(lecture);
            courseInstances.add(train);
            
        }

        return courseInstances;
    }

}
