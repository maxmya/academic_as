package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Specialization;

import java.util.List;

public class SpecializationResponse extends BaseResponse {

    private Object data ;

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
