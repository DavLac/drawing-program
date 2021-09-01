package io.davlac.drawingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Configuration
@ComponentScan(basePackages = {"io.davlac.drawingapp"})
public class AppConfig {
    @Bean
    public ValidatorFactory factory() {
        return Validation.buildDefaultValidatorFactory();
    }
}
