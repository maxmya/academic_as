package com.academic.as.demo.controllers.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
public class Group implements Serializable {
    List<String> members;
    Metadata metadata;
}

@AllArgsConstructor
@Data
class Metadata {
    String name;
}
