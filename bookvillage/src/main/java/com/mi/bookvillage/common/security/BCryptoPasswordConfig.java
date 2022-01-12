package com.mi.bookvillage.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * BCryptoPassword Bean 주입
 */
@Configuration
public class BCryptoPasswordConfig {

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}

