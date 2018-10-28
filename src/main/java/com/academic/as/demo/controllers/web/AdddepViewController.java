package com.academic.as.demo.controllers.web;

import com.academic.as.demo.models.Department;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdddepViewController {

    @Autowired
    SpecializationService specializationService ;

    @GetMapping("/adddep")
    public String addDepView(Model model) {
        model.addAttribute(new Department());
        return "add_dep";
    }

    @PostMapping("/adddep")
    public String addDep(@ModelAttribute("department") Department department, Model model) {
        model.addAttribute("response", specializationService.addDepartment(department));
        return "home";
    }




}
