package fr.ing.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BankAccountKataApplication {
	 private static final Logger logger = LoggerFactory.getLogger(BankAccountKataApplication.class);
	
	 public static void main(String[] args) {
		SpringApplication.run(BankAccountKataApplication.class, args);
	}

}
