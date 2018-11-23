package com.academic.as.demo.controllers.web;

import com.academic.as.demo.api.responses.BaseResponse;
import com.academic.as.demo.models.Department;
import com.academic.as.demo.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DepartmentViewController {

    @Autowired
    SpecializationService specializationService ;

    @GetMapping("/add/department")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDepView(Model model) {
        model.addAttribute(new Department());
        return "add_dep";
    }

    @PostMapping("/add/department")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addDep(@ModelAttribute("department")  @Valid Department department, BindingResult bindingResult , Model model) {

        if (bindingResult.hasErrors()) {
            return "add_dep";
        }
        model.addAttribute("response", specializationService.addDepartment(department));
        return "add_dep";
    }
     //Todo prevent user to put anyother type of id as integer
    @GetMapping("/department/{ID}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String EditDepartmentView(@PathVariable(value="ID") int id, Model model) {
        model.addAttribute("department",specializationService.getDepartment(id).getData());
        return "edit_department";
    }

    @PostMapping("/department/{ID}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String EditDepartment(@ModelAttribute("course") @Valid Department department, BindingResult bindingResult,Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "edit_department";
        }
        BaseResponse response = specializationService.SaveDepartment(department,department.getId());
        if (response.getCode().equalsIgnoreCase("200"))
            model.addAttribute(department);
        model.addAttribute("response", response);
        return "edit_department";
    }


}
