package com.lms.login_service.controller;

import com.lms.login_service.dto.LoginDto;
import com.lms.login_service.responses.Response;
import com.lms.login_service.service.LoginService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping
    public ResponseEntity<Response> userLogin (@RequestBody LoginDto loginDto){

        return new ResponseEntity<>(loginService.userLogin(loginDto), HttpStatus.ACCEPTED);
    }
}
