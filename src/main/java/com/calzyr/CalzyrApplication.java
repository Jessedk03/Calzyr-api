package com.calzyr;

import com.calzyr.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CalzyrApplication {

	public static void main(String[] args) {

		EnvConfig.loadenv();

		SpringApplication.run(CalzyrApplication.class, args);
	}

}
