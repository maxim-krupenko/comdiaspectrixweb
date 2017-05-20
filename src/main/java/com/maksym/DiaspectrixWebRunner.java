package com.maksym;

import com.maksym.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.maksym"}, exclude = {SecurityAutoConfiguration.class })
public class DiaspectrixWebRunner extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DiaspectrixWebRunner.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(DiaspectrixWebRunner.class);
    }
}
