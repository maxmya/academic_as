package com.academic.as.demo.controllers.web;


import com.academic.as.demo.services.FirebaseGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/moderators")
public class ModeratorsViewController {

    @Autowired
    FirebaseGroupsService firebaseGroupsService;

    @GetMapping("/groups")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String viewGroups(Model model) throws InterruptedException {

        model.addAttribute("h", firebaseGroupsService.doSomething());

        return "groups_list";
    }

    @GetMapping("/groups/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getGroup(@Param("id") String id) {
        return "single_group";
    }

}
