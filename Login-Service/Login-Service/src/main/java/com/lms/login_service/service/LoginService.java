package com.lms.login_service.service;

import com.lms.login_service.dto.LoginDto;
import com.lms.login_service.exceptions.UserException;
import com.lms.login_service.external_service.UserService;
import com.lms.login_service.jwt_config.JwtUtility;
import com.lms.login_service.responses.Response;
import com.lms.login_service.responses.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    JwtUtility jwtUtility;
    @Autowired
    UserService userService;
    @Autowired
    ModelMapper mapper;
    @Autowired
    Response response;

    public Response userLogin (LoginDto loginDto){

        UserResponse userResponseFromUserService = userService.getUserDetails(loginDto);
        System.out.println(userResponseFromUserService);

        if(userResponseFromUserService == null){
            throw new UserException("User Not Available" , HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
        else {
            response.setMessage("User Logged In Successfully");
            response.setObject(jwtUtility.generateToken(loginDto));
            return response;
        }


    }




}
