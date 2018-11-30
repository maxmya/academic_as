package com.academic.as.demo.api.responses;

import com.academic.as.demo.enums.UserRoles;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@ResponseBody
public class RegisterResponse extends BaseResponse {

    private UserRoles userRoles;

}

