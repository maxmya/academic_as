package com.academic.as.demo.controllers.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    String name;
    String body;
    long dueDate;
    String imageUrl;
    String fileUrl;

}
