package br.com.osa.stander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StanderApplication {

	public static void main(String[] args) {

		SpringApplication.run(StanderApplication.class, args);
	}

}
