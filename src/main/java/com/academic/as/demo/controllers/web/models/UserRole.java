package com.academic.as.demo.controllers.web.models;


import lombok.Data;

@Data
public class UserRole {
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
