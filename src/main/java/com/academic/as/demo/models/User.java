package com.academic.as.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

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

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

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
