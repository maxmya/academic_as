package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Specialization;

import java.util.List;

public class SpecializationResponse extends BaseResponse {

    private List<Specialization> data ;

    public void setData(List<Specialization> data) {
        this.data = data;
    }

    public List<Specialization> getData() {
        return data;
    }
}
