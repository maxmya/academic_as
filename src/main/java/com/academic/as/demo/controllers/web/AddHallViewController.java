package com.academic.as.demo.controllers.web;
import com.academic.as.demo.api.requests.CourseInstanceRequest;
import com.academic.as.demo.models.CourseInstance;
import com.academic.as.demo.models.Hall;
import com.academic.as.demo.services.CoursesService;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AddHallViewController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/add/hall")
    public String addHallView(Model model) {
        model.addAttribute(new Hall());
        return "add_hall";
    }

    @PostMapping("/add/hall")
    public String addHall(@ModelAttribute("hall")Hall hall,CourseInstanceRequest courseInstanceRequest,Model model) {
        model.addAttribute("response",coursesService.addHall(hall));
        return "add_hall";
    }

}
