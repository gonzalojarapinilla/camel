package com.ejemplo.camel.bitcoin1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ejemplo.camel")
public class CamelBitcoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelBitcoinApplication.class, args);
	}

}
