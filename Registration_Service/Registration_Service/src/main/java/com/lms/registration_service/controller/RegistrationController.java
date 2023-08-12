package com.lms.registration_service.controller;

import com.lms.registration_service.dto.UserDto;
import com.lms.registration_service.responses.Response;
import com.lms.registration_service.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Response> registerNewUser(@RequestBody UserDto userDto){

        return new ResponseEntity<>(registrationService.registerUser(userDto), HttpStatus.ACCEPTED);

    }

    




}
