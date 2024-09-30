package org.example.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.ConexionEntityManager;
import org.example.entity.Carrera;
import org.example.entity.Estudiante;
import org.example.entity.Inscripcion;
import org.example.repository_impl.Carrera_Repository_impl;
import org.example.repository_impl.Estudiante_Repository_impl;
import org.example.repository_impl.Inscripcion_Repository_impl;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.time.LocalDate;

public class InsertCSV {
    public static final String FILE_NAME_CARRERA="./src/main/resources/csvDate/carreras.csv";
    public static final String FILE_NAME_ESTUDIANTE="./src/main/resources/csvDate/estudiantes.csv";
    public static final String FILE_NAME_INSCRIPCION="./src/main/resources/csvDate/inscripciones.csv";

    private CSVParser parserCarrera;
    private CSVParser parserEstudiante;
    private CSVParser parserInscripcion;

    private Carrera_Repository_impl carreraRepository;
    private Estudiante_Repository_impl estudianteRepository;
    private Inscripcion_Repository_impl inscripcionRepository;

    private EntityManager em;

    public InsertCSV(EntityManager em) {
        this.em = em;
        parserCarrera = lector(FILE_NAME_CARRERA);
        parserEstudiante = lector(FILE_NAME_ESTUDIANTE);
        parserInscripcion = lector(FILE_NAME_INSCRIPCION);

        carreraRepository = new Carrera_Repository_impl(em);
        estudianteRepository = new Estudiante_Repository_impl(em);
        inscripcionRepository = new Inscripcion_Repository_impl(em);

    }
    public void insert(){

        try {
            //agrega las carreras
            for(CSVRecord c : parserCarrera){
                Carrera c1 = new Carrera(c.get("nombre"));
                carreraRepository.agregar(c1);
            }
            //agrega los estudiantes
            for(CSVRecord e : parserEstudiante){
                Estudiante e1 = new Estudiante(e.get("nombre"),e.get("apellido"),
                        e.get("genero"),Integer.parseInt(e.get("DNI")),e.get("Ciudad"),
                        Integer.parseInt(e.get("NroLibretaUniversitaria")));
                estudianteRepository.agregar(e1);
            }
            //busca el estudiante ya ingresado y busca la carrera donde el estudiante se quiere anotar y lo inscribe
            for(CSVRecord i : parserInscripcion){
                Estudiante es = estudianteRepository.buscarPorDNI(Integer.parseInt(i.get("estudiante_DNI")));
                Carrera ca = carreraRepository.buscarPorId(Integer.parseInt(i.get("carrera_id")));
                Inscripcion i1 = new Inscripcion(es,ca, LocalDate.parse(i.get("antiguedad")),Boolean.parseBoolean(i.get("graduado")));
                inscripcionRepository.agregar(i1);
            }

        } catch (Exception e) {
            System.out.println("No se pudo insertar");
            e.printStackTrace();
        }
    }
    private CSVParser lector(String archivo){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader(archivo));
            return parser;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
