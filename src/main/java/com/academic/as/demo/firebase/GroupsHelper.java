package com.academic.as.demo.firebase;

import com.academic.as.demo.controllers.web.models.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupsHelper {


    public List<Group> getGroupsListFromFirebase() {
        List<Group> groups = new ArrayList<>();

        return groups;
    }


}
