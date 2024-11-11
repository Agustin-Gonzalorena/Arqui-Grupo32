package com.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroserviceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEurekaApplication.class, args);
	}

	//CAMBIAR TODOS LOS URL DE DB PARA QUE SE CREEN SOLOS LOS SCHEMAS TODO
	//preguntar por Coneccion a la base de datos para la correccion TODO
}
