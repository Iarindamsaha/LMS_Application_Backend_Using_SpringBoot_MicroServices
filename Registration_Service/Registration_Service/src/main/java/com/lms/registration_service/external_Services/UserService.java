package com.lms.registration_service.external_Services;

import com.lms.registration_service.dto.UserDto;
import com.lms.registration_service.responses.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @PostMapping("/register")
    public Response createNewUser (UserDto userDto);
    

}
