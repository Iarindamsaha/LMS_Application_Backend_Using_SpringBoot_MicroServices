package com.lms.admin_service.controller;

import com.lms.admin_service.dto.CandidateStatusDTO;
import com.lms.admin_service.response.Response;
import com.lms.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public ResponseEntity<Response>  getAllUserDetails (){

        return new ResponseEntity<>(adminService.getAllUserDetails(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response> setCandidateStatus(@RequestBody CandidateStatusDTO candidateStatusDTO){

        return new ResponseEntity<>(adminService.setCandidateStatus(candidateStatusDTO),HttpStatus.OK);

    }
}
