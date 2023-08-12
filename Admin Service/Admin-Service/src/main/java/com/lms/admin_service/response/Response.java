package com.lms.admin_service.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Response {

    private String message;
    private Object object;


}
