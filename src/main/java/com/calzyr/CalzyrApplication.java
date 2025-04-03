package com.calzyr;

import com.calzyr.config.EnvConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.calzyr.repositories")
public class CalzyrApplication {

	public static void main(String[] args) {

		EnvConfig.loadEnv();

		SpringApplication.run(CalzyrApplication.class, args);
	}

}
