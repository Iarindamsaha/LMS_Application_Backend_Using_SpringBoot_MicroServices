package com.lms.user_service.external_services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "CANDIDATE-SERVICE")
public class CandidateService {


}
