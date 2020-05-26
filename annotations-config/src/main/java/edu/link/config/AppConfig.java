package edu.link.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class})
@ComponentScan("edu.link.jpa")
public class AppConfig {

}
