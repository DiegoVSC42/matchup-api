package dev.diegovsc42.MatchUp_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MatchUpApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(MatchUpApiApplication.class, args);
	}

}
