package com.indianeagle.internal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class IEInternalApplication {

	public static void main(String[] args) {
		SpringApplication.run(IEInternalApplication.class, args);
	}

}
