package com.lms.registration_service.service;

import com.lms.registration_service.dto.UserDto;
import com.lms.registration_service.external_Services.UserService;
import com.lms.registration_service.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserService userService;

    public Response registerUser(UserDto userDto){

        return userService.createNewUser(userDto);

    }

}
