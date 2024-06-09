package com.carlaospa.arquiteturahexagonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence")
public class ArquiteturahexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArquiteturahexagonalApplication.class, args);
	}

}
