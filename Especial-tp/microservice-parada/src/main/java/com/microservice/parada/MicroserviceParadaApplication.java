package com.microservice.parada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableDiscoveryClient
@SpringBootApplication
@EnableWebMvc

public class MicroserviceParadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceParadaApplication.class, args);
	}

}
