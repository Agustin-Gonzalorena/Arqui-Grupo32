package com.microservice.parada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceParadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceParadaApplication.class, args);
	}

}
