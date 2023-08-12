package com.lms.candidate_service.controller;

import com.lms.candidate_service.responses.Response;
import com.lms.candidate_service.service.CandidateService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @GetMapping("/checkStatus")
    public ResponseEntity<Response> getCandidateStatus(@RequestHeader String token){

        return new ResponseEntity<>(candidateService.getCandidateStatus(token), HttpStatus.OK);

    }
}
