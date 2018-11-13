package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.Specialization;
import lombok.Data;

import java.util.List;

@Data
public class SpecializationResponse extends BaseResponse {

    private List<Specialization> data;

}
