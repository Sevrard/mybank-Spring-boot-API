package com.bank.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("test")
    public String TestProfileConfig() {
        System.out.println("=== TEST PROFILE ACTIVE ===");
        return "Test profile";
    }
    @Bean
    @Profile("dev")
    public String DevProfileConfig(){
        System.out.println("=== DEV PROFILE ACTIVE ===");
        return "Dev profile";
    }
    @Bean
    @Profile("prod")
    public String ProdProfileConfig(){
        System.out.println("=== PROD PROFILE ACTIVE ===");
        return "prod profile";
    }
}

