package com.academic.as.demo.api.responses;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@ResponseBody
public class BaseResponse {

    private String code;
    private String message;

}
