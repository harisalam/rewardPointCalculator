package dev.haris.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CharterAssignmentApplication {

	public static void main(String[] args) {

		SpringApplication.run(CharterAssignmentApplication.class, args);
	}

	@GetMapping("/")
	public String getHello(){
		return "API started successfully!!! Please navigate to postman to run the api on demo data";
	}

}
