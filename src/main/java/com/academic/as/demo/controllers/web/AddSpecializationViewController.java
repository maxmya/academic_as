package com.academic.as.demo.controllers.web;


import com.academic.as.demo.api.requests.SpecializationRequest;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.models.Specialization;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AddSpecializationViewController implements WebMvcConfigurer {

    @Autowired
    SpecializationService specializationService;

    @GetMapping("/add/specialization")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addSpecializationView(Model model) {
        model.addAttribute(new Specialization());
        model.addAttribute("department", specializationService.getAllDepartments().getData());

        return "add_specialization";
    }

    @PostMapping("/add/specialization")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addSpecialization(@ModelAttribute("specialization")@Valid Specialization specialization, BindingResult bindingResult ,SpecializationRequest specializationRequest, Model model) {
        specializationRequest.setSpecialization(specialization);
        if (bindingResult.hasErrors()) {
            return "add_specialization";
        }
        model.addAttribute("response", specializationService.addSpecialization(specializationRequest));
        return "add_specialization";
    }
}
