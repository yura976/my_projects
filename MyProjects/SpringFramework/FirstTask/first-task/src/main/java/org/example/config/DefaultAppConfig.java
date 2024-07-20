package org.example.config;

import org.example.manager.ApplicationManager;
import org.example.manager.DefaultApplicationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Profile("default")
public class DefaultAppConfig {
    @Bean
    public ApplicationManager setContactList() {
        return new DefaultApplicationManager();
    }
}
