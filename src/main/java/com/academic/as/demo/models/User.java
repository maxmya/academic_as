package com.academic.as.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "fname")
    private String firstName;

    @NotNull
    @Column(name = "lname")
    private String lastName;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "username")
    private String username;

    @Column(name = "create_date")
    private Date createDate;

    public void update(User user) {
        if (user.getFirstName() != null)
            this.firstName = user.getFirstName();
        if (user.getLastName() != null)
            this.lastName = user.getLastName();
        if (user.getUsername() != null)
            this.username = user.getUsername();
        if (user.getEmail() != null)
            this.email = user.getEmail();
    }
}
