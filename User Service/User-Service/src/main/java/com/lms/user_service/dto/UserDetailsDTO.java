package com.lms.user_service.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDetailsDTO {

    private String id;
    private String firstname;
    private String lastname;
    private String candidateStatus;
    private String email;

}
