package com.lms.account_verification_service.service;

import com.lms.account_verification_service.dto.LoginDto;
import com.lms.account_verification_service.exceptions.UserException;
import com.lms.account_verification_service.external_service.UserService;
import com.lms.account_verification_service.responses.Response;
import com.lms.account_verification_service.responses.UserResponse;
import com.lms.account_verification_service.utility.JwtUtility;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    UserService userService;
    @Autowired
    Response response;

    public Response verify(String token){

        LoginDto loginDto = jwtUtility.decodeToken(token);
        if(loginDto == null){
            throw new UserException("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
        else {
            UserResponse userCheck = userService.getUserDetails(loginDto);
            response.setMessage("User Verified Successfully");
            response.setObject("User Name : " + userCheck.getFirstname() + " " + userCheck.getLastname());
            return response;
        }

    }
}
