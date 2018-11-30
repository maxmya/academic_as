package com.academic.as.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "professor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Professor extends Instructor implements Serializable {


}
