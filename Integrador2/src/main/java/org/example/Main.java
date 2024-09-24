package org.example;

import org.example.DAO_impl.Estudiante_Repository_impl;
import org.example.entidades.Estudiante;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Estudiante pedro = new Estudiante("Pedro","RDD","masculino",43555432,
                "Tandil",123);

        Estudiante_Repository_impl repo_estudiante = Estudiante_Repository_impl.getInstancia();
        repo_estudiante.darDeAlta(pedro);


    }
}