package com.lms.user_service.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CandidateStatusDTO {

    private String email;
    private String candidateStatus;

}
