package com.microservice.viaje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceViajeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceViajeApplication.class, args);
	}

}
