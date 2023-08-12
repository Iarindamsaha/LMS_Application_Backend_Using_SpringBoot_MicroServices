package com.lms.user_service.controller;

import com.lms.user_service.dto.*;
import com.lms.user_service.responses.CandidateResponse;
import com.lms.user_service.responses.Response;
import com.lms.user_service.responses.UserResponse;
import com.lms.user_service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> createNewUser(@RequestBody UserDto userDto){

        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.ACCEPTED);

    }

    @RequestMapping("/userDetail")
    public ResponseEntity<UserResponse> getUserDetails (@RequestBody LoginDto loginDto){

        return new ResponseEntity<>(userService.getUserByEmailAndPassword(loginDto),HttpStatus.ACCEPTED);
    }

    @RequestMapping("/getUserByEmail")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestBody String email){

        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);

    }

    @PostMapping("/setOtp")
    public ResponseEntity<Response> setUserOtp(@RequestBody OtpDto otp){

        return new ResponseEntity<>(userService.setOtp(otp),HttpStatus.OK);

    }

    @PostMapping ("/getByOtp")
    public ResponseEntity<UserResponse> getByOtp (@RequestBody OtpDto otpDto){

        return new ResponseEntity<>(userService.getByOtp(otpDto),HttpStatus.OK);

    }

    @PostMapping("/createNewPassword")
    public ResponseEntity<Response> createNewPassword(@RequestBody NewPasswordDto newPasswordDto){

        return new ResponseEntity<>(userService.createNewPassword(newPasswordDto),HttpStatus.OK);

    }

    @GetMapping("/candidateStatus{email_id}")
    public ResponseEntity<CandidateResponse> getAllCandidateStatusByEmail (@PathVariable String email_id){

        return new ResponseEntity<>(userService.getCandidateDetailsByEmail(email_id),HttpStatus.OK);

    }

    @RequestMapping("/getAllUserDetails")
    public ResponseEntity<List<UserDetailsDTO>> getAllUserDetails () {

        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);

    }

    @PostMapping("/setCandidateStatus")
    public ResponseEntity<Response> setCandidateStatus(@RequestBody CandidateStatusDTO candidateStatusDTO){

        return new ResponseEntity<>(userService.setCandidateStatus(candidateStatusDTO),HttpStatus.OK);

    }
}
