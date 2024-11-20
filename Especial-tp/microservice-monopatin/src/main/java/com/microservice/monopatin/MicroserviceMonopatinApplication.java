package com.microservice.monopatin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceMonopatinApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMonopatinApplication.class, args);
	}

}
