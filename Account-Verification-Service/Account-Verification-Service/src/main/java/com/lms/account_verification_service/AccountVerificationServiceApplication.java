package com.lms.account_verification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AccountVerificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountVerificationServiceApplication.class, args);
	}

}
