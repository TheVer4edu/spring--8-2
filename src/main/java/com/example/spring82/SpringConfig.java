package com.example.spring82;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.spring82")
public class SpringConfig {

    @Bean(name = "engine")
    public ModuleEngine provideModuleEngine() {
        return new ModuleEngine();
    }

}
