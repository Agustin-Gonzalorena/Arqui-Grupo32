package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entity.Inscripcion;
import org.example.repository_impl.Carrera_Repository_impl;
import org.example.repository_impl.Estudiante_Repository_impl;
import org.example.entity.Carrera;
import org.example.entity.Estudiante;
import org.example.repository_impl.Inscripcion_Repository_impl;
import org.example.service.InsertCSV;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = ConexionEntityManager.getInstancia().getConexion();
        em.getTransaction().begin();
        Estudiante_Repository_impl eRepo = new Estudiante_Repository_impl(em);
        Carrera_Repository_impl cRepo = new Carrera_Repository_impl(em);
        Inscripcion_Repository_impl iRepo = new Inscripcion_Repository_impl(em);

        InsertCSV insertCSV = new InsertCSV(em);
        insertCSV.insert();
        System.out.println("Datos cargados");
        System.out.println("-----------------------");

        //Ej_2_a) dar de alta un estudiante
        Estudiante e1 = new Estudiante("Lionel","Messi","Masculino",35787021,"Rosario",10);
        eRepo.agregar(e1);

        //Ej_2_b) matricular un estudiante en una carrera
        Carrera carrera = cRepo.buscarPorId(1);
        Inscripcion inscripcion = new Inscripcion(e1,carrera,LocalDate.now(),false);
        iRepo.agregar(inscripcion);

        //Ej_2_c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        List<Estudiante> es1 = eRepo.getEstudiantesByOrden("nombre");
        if(es1!=null){
            System.out.println("Estudiantes ordenados:");
            for (Estudiante e : es1) {
                System.out.println(e);
            }
        }
        System.out.println("-----------------------");

        //Ej_2_d) recuperar un estudiante, en base a su número de libreta universitaria.
        System.out.println("Estudiane por libreta: "+ eRepo.getEstudiantePorLibreta(10));
        System.out.println("-----------------------");

        //Ej_2_e) recuperar todos los estudiantes, en base a su género.
        List<Estudiante> es2 = eRepo.getEstudiantesPorGenero("Masculino");
        if(es2!=null){
            System.out.println("Estudiantes Por genero:");
            for (Estudiante e : es2) {
                System.out.println(e);
            }
        }
        System.out.println("-----------------------");

        //Ej_2_f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
        List<Carrera> ca1 = cRepo.getCarrerasIncriptosOrdenada();
        if(ca1!=null){
            System.out.println("Carreras encontradas:");
            for (Carrera e : ca1) {
                System.out.println(e);
            }
        }
        System.out.println("-----------------------");

        //Ej_2_g):recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        recuperarEstudiantesPorCarreraYCidudad(em,cRepo,eRepo);

        em.getTransaction().commit();
        em.close();
    }

    private static void recuperarEstudiantesPorCarreraYCidudad(EntityManager em, Carrera_Repository_impl cRepo,Estudiante_Repository_impl eRepo) {
        //Recupera una carrera
        Carrera carrera = cRepo.buscarPorId(2);
        //Recupera los estudiantes de esa carrera y de la ciudad que se le pasa por parametro
        List<Estudiante> estudiantes = eRepo.getEstudiantesPorCarreraCiudad(carrera,"Tandil");

        System.out.println("Carrera: "+carrera.getNombre());
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }
}