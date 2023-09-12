package com.wellsfargo.training.ezloans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@EnableAutoConfiguration
@ComponentScan
@Configuration
@SpringBootApplication
public class EzloansApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzloansApplication.class, args);
	}

}
