package com.lms.admin_service.service;

import com.lms.admin_service.dto.CandidateStatusDTO;
import com.lms.admin_service.exceptions.UserException;
import com.lms.admin_service.external_services.UserService;
import com.lms.admin_service.response.CandidateResponse;
import com.lms.admin_service.response.Response;
import com.lms.admin_service.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    Response response;
    @Autowired
    UserService userService;
    @Autowired
    RestTemplate restTemplate;

    public Response getAllUserDetails () {

        response.setMessage("All User Details Are : ");
        response.setObject(userService.getAllUserDetails());
        return response;

    }

    public Response setCandidateStatus (CandidateStatusDTO candidateStatusDTO){

        CandidateResponse responseFromUserService =
                restTemplate.getForObject("http://USER-SERVICE/candidateStatus{email_id}",
                        CandidateResponse.class,candidateStatusDTO.getEmail());
        if(responseFromUserService == null){
            throw new UserException("User Not Available", HttpStatus.BAD_REQUEST,null);
        }
        else {
            return restTemplate.postForObject
                    ("http://USER-SERVICE/setCandidateStatus",candidateStatusDTO, Response.class);
        }

    }
}
