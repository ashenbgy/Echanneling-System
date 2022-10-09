package com.echanneling.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BeanConfiguration{
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
