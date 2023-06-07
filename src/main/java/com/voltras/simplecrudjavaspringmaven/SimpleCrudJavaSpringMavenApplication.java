package com.voltras.simplecrudjavaspringmaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SimpleCrudJavaSpringMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCrudJavaSpringMavenApplication.class, args);
	}

}