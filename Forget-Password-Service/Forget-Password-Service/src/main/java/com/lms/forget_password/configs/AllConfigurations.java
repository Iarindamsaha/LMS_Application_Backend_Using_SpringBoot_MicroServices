package com.lms.forget_password.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AllConfigurations {

    @Bean
    SimpleMailMessage simpleMailMessage(){
        return new SimpleMailMessage();
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
