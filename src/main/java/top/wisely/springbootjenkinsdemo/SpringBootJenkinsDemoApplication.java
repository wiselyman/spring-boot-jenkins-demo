package top.wisely.springbootjenkinsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootJenkinsDemoApplication {

	@GetMapping("/")
	public String index(){
		return "Hello Spring Boot With Jenkins";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJenkinsDemoApplication.class, args);
	}

}
