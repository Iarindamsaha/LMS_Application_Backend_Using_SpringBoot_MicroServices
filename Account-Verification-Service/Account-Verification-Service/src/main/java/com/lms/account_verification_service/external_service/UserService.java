package com.lms.account_verification_service.external_service;

import com.lms.account_verification_service.dto.LoginDto;
import com.lms.account_verification_service.responses.UserResponse;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @RequestMapping("/userDetail")
    public UserResponse getUserDetails(LoginDto loginDto);


}
