package com.calzyr;

import com.calzyr.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.calzyr.repositories"
})
@EnableScheduling
public class CalzyrApplication {

	public static void main(String[] args) {

		EnvConfig.loadEnv();

		SpringApplication.run(CalzyrApplication.class, args);
	}

}
