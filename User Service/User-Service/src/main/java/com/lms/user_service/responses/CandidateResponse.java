package com.lms.user_service.responses;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CandidateResponse {

    private String firstname;
    private String lastname;
    private String candidateStatus;

}
