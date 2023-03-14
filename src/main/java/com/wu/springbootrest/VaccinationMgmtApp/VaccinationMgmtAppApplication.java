package com.wu.springbootrest.VaccinationMgmtApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class VaccinationMgmtAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccinationMgmtAppApplication.class, args);
	}

}
