package com.lms.admin_service.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CandidateResponse {

    private String firstname;
    private String lastname;
    private String candidateStatus;

}
