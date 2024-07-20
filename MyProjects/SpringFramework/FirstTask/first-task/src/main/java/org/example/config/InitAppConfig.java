package org.example.config;

import org.example.manager.ApplicationManager;
import org.example.manager.InitApplicationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class InitAppConfig {
    @Bean
    public ApplicationManager setContactList() {
        return new InitApplicationManager();
    }
}
