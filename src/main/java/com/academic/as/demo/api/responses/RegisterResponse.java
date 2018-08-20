package com.academic.as.demo.api.responses;

import com.academic.as.demo.enums.UserRole;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class RegisterResponse extends BaseResponse {

    private UserRole userRole;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}

