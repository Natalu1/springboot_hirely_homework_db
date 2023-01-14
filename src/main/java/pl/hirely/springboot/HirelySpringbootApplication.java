package pl.hirely.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class HirelySpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HirelySpringbootApplication.class, args);
	}

}
