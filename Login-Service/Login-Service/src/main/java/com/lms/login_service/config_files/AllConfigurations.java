package com.lms.login_service.config_files;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllConfigurations {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
