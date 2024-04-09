package dh.project11.packs.com;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Random random() {
        return new Random();
    }
    
    @Bean
    public String myStringBean() {
        return "Hello, World!";
    }
}