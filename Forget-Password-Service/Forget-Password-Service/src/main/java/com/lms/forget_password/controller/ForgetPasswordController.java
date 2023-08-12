package com.lms.forget_password.controller;

import com.lms.forget_password.dto.EmailDto;
import com.lms.forget_password.dto.NewPasswordDto;
import com.lms.forget_password.dto.OtpDto;
import com.lms.forget_password.responses.Response;
import com.lms.forget_password.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

    @Autowired
    ForgetPasswordService forgetPasswordService;

    @PostMapping("/getOtp")
    public ResponseEntity<Response> getOtp (@RequestBody EmailDto emailDto){

        return new ResponseEntity<>(forgetPasswordService.forgetPassword(emailDto), HttpStatus.ACCEPTED);

    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<Response> verifyOtp (@RequestBody OtpDto otpDto){

        return new ResponseEntity<>(forgetPasswordService.verifyOtp(otpDto),HttpStatus.OK);

    }

    @PostMapping("/createNewPassword")
    public ResponseEntity<Response> createNewPassword(@RequestBody NewPasswordDto newPasswordDto, @RequestHeader String token){

        return new ResponseEntity<>(forgetPasswordService.createNewPassword(newPasswordDto,token),HttpStatus.OK);

    }



}
