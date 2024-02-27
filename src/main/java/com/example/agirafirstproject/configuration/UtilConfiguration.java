package com.example.agirafirstproject.configuration;

import com.example.agirafirstproject.utility.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfiguration {
    @Bean
    public UserMapper userMapper(){
        return new UserMapper();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
