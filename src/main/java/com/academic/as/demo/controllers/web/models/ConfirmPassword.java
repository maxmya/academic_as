package com.academic.as.demo.controllers.web.models;

import lombok.Data;

@Data
public class ConfirmPassword {
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
