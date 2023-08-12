package com.lms.forget_password.external_service;

import com.lms.forget_password.dto.OtpDto;
import com.lms.forget_password.responses.Response;
import com.lms.forget_password.responses.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @RequestMapping("/getUserByEmail")
    public UserResponse getUserByEmail (String email);


}
