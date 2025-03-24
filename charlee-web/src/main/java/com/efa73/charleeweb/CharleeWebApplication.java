package com.efa73.charleeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CharleeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CharleeWebApplication.class, args);
    }

}
