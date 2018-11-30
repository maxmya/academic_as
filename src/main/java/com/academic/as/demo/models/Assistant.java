package com.academic.as.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "assistant")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Assistant extends Instructor implements Serializable {

}
