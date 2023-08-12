package com.lms.forget_password.responses;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserResponse {

    private String firstname;
    private String lastname;

}
