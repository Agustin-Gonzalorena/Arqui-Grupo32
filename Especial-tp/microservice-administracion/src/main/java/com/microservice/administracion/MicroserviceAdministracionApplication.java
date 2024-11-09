package com.microservice.administracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceAdministracionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAdministracionApplication.class, args);
	}

}