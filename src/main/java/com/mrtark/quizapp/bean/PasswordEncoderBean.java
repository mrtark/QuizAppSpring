package com.mrtark.quizapp.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class PasswordEncoderBean {
    @Bean
    public PasswordEncoder passwordEncoderBeanMethod(){
        return new BCryptPasswordEncoder();
    }
}
