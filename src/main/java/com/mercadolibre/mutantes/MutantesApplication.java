package com.mercadolibre.mutantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mercadolibre.mutantes.controllers,com.mercadolibre.mutantes.services,com.mercadolibre.mutantes.repositories,com.mercadolibre.mutantes.config")
public class MutantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantesApplication.class, args);
	}

}
