package com.academic.as.demo.controllers.web.models;

import lombok.Data;

import java.util.List;

@Data
public class Group {

    String groupName;
    String groupImageUrl;
    List<Member> memberList;
    List<Task> tasks;

}
