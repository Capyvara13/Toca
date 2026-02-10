package com.atdo.toca_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.atdo.toca_cms.adapter.persistence.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.atdo.toca_cms.adapter.persistence.jpa"
})
public class TocaCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TocaCmsApplication.class, args);
    }
}