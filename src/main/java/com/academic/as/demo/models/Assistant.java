package com.academic.as.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assistant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Assistant extends Instructor implements Serializable {

}
