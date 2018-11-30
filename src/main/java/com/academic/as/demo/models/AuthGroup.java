package com.academic.as.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auth_group")
public class AuthGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "auth_group")
    String authGroup;

    @Column(name = "username")
    String username;

}
