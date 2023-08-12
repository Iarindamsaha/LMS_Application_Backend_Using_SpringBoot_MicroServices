package com.lms.admin_service.external_services;

import com.lms.admin_service.dto.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "USER-SERVICE")
public interface UserService {

    @RequestMapping("/getAllUserDetails")
    public List<UserDetailsDTO> getAllUserDetails ();
}
