package com.itcen.censquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CenSquareApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenSquareApplication.class, args);
    }

}
