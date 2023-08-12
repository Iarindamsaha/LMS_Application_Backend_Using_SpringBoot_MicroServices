package com.lms.login_service.responses;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserResponse {

    private String firstname;
    private String lastname;

}
