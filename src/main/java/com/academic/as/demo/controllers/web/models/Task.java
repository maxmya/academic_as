package com.academic.as.demo.controllers.web.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class Task {


    String name;
    Timestamp dueDate;
    String imageUrl;
    String fileUrl;

}
