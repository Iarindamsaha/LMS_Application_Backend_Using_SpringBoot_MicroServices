package com.lms.user_service.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class NewPasswordDto {

    private String password;
    private String email;
}
