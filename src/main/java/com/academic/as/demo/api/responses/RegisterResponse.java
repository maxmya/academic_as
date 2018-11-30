package com.academic.as.demo.api.responses;

import com.academic.as.demo.enums.UserRoles;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class RegisterResponse extends BaseResponse {

    private UserRoles userRoles;

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}

