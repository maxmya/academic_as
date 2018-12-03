package com.academic.as.demo.api.responses;

import com.academic.as.demo.models.CourseInstance;
import lombok.Data;

@Data
public class CourseInstanceCreationResponse extends BaseResponse {

    CourseInstance courseInstance;
}
