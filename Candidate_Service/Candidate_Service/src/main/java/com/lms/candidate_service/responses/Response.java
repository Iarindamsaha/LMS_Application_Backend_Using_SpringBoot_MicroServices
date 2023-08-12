package com.lms.candidate_service.responses;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Response {

    private String message;
    private Object object;

}
