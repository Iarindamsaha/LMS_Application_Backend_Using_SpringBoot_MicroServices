package com.lms.login_service.external_service;

import com.lms.login_service.dto.LoginDto;
import com.lms.login_service.responses.Response;
import com.lms.login_service.responses.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @RequestMapping("/userDetail")
    public UserResponse getUserDetails(LoginDto loginDto);

}