package com.lms.account_verification_service.controller;

import com.lms.account_verification_service.responses.Response;
import com.lms.account_verification_service.service.VerificationService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verification")
public class VerificationController {
    @Autowired
    VerificationService verificationService;

    @GetMapping
    public ResponseEntity<Response> verifyUser(@RequestHeader String token){

        return new ResponseEntity<>(verificationService.verify(token), HttpStatus.OK);

    }

}
