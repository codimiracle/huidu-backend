package com.codimiracle.application.platform.huidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
public class HuiduWebBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuiduWebBackendApplication.class, args);
    }

}
