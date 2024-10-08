package org.example.integrador3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Integrador3Application {

    @Autowired
    private CargaDeDatos cargaDeDatos;

    public static void main(String[] args) {
        SpringApplication.run(Integrador3Application.class, args);
    }

    @PostConstruct
    public void init() {
        cargaDeDatos.cargarEstudiantesCSV();
    }


    //https://api.postman.com/collections/38850954-52690157-55fe-428a-8c87-7eaddedb3bbe?access_key=PMAT-01J9PJFFMC5S47PCVA9WW40DTT
}
