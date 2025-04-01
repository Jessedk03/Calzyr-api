package com.calzyr;

import com.calzyr.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.calzyr.repositories")
public class CalzyrApplication {

	public static void main(String[] args) {

		EnvConfig.loadenv();

		SpringApplication.run(CalzyrApplication.class, args);
	}

}
