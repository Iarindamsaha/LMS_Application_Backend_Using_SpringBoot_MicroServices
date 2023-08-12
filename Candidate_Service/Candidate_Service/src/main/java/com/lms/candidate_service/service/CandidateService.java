package com.lms.candidate_service.service;

import com.lms.candidate_service.dto.LoginDto;
import com.lms.candidate_service.responses.CandidateResponse;
import com.lms.candidate_service.responses.Response;
import com.lms.candidate_service.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CandidateService {

    @Autowired
    Response response;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    JwtUtility jwtUtility;

    public Response getCandidateStatus(String token){

        LoginDto loginDto  = jwtUtility.decodeToken(token);

        CandidateResponse candidateResponse =
                restTemplate.getForObject
                        ("http://USER-SERVICE//candidateStatus{email_id}", CandidateResponse.class,loginDto.getEmail());
        response.setMessage("Details Found");
        response.setObject(candidateResponse);
        return response;

    }
}
