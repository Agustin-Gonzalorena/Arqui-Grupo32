package org.example.integrador3;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.integrador3.entity.Carrera;
import org.example.integrador3.entity.Estudiante;
import org.example.integrador3.entity.Inscripcion;
import org.example.integrador3.entity.KeyInscripcion;
import org.example.integrador3.repository.CarreraRepo;
import org.example.integrador3.repository.EstudianteRepo;
import org.example.integrador3.repository.InscripcionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.time.LocalDate;

@Component
public class CargaDeDatos {
    private static final String FILE_PATH_ESTUDIANTES = "./src/main/resources/csvDate/estudiantes.csv";
    private static final String FILE_PATH_CARRERAS = "./src/main/resources/csvDate/carreras.csv";
    private static final String FILE_PATH_INSCRIPCIONES = "./src/main/resources/csvDate/inscripciones.csv";

    private CSVParser parserEstudiantes;
    private CSVParser parserCarreras;
    private CSVParser parserInscripciones;

    private EstudianteRepo estudianteRepo;
    private CarreraRepo carreraRepo;
    private InscripcionRepo inscripcionRepo;

    @Autowired
    public CargaDeDatos(EstudianteRepo estudianteRepo,CarreraRepo carreraRepo,InscripcionRepo inscripcionRepo) {
        this.estudianteRepo = estudianteRepo;
        this.carreraRepo =carreraRepo;
        this.inscripcionRepo =inscripcionRepo;

        parserEstudiantes= lector(FILE_PATH_ESTUDIANTES);
        parserCarreras= lector(FILE_PATH_CARRERAS);
        parserInscripciones= lector(FILE_PATH_INSCRIPCIONES);
    }

    public void cargarEstudiantesCSV() {
        try {
            for (CSVRecord record : parserEstudiantes.getRecords()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setNombre(record.get("nombre"));
                estudiante.setApellido(record.get("apellido"));
                estudiante.setGenero(record.get("genero"));
                estudiante.setDNI(Integer.parseInt(record.get("DNI")));
                estudiante.setCiudadResidencia(record.get("Ciudad"));
                estudiante.setNroLibretaUniversitaria(Integer.parseInt(record.get("NroLibretaUniversitaria")));
                estudianteRepo.save(estudiante);
            }
            for(CSVRecord record : parserCarreras.getRecords()) {
                Carrera carrera = new Carrera();
                carrera.setNombre(record.get("nombre"));
                carreraRepo.save(carrera);
            }
            for (CSVRecord record : parserInscripciones.getRecords()) {
                Estudiante e = estudianteRepo.findByDni(Integer.parseInt(record.get("estudiante_DNI")));
                Carrera c = carreraRepo.getById(Long.parseLong(record.get("carrera_id")));
                if(e == null || c == null) continue;
                KeyInscripcion keyInscripcion = new KeyInscripcion(e,c);
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setKeyInscripcion(keyInscripcion);
                inscripcion.setFechaInscripcion(LocalDate.parse(record.get("fechaInscripcion")));
                if(!record.get("fechaGraduacion").equals("null")) {
                    inscripcion.setFechaGraduacion(LocalDate.parse(record.get("fechaGraduacion")));
                }
                System.out.println(inscripcion.getFechaInscripcion());
                inscripcionRepo.save(inscripcion);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public CSVParser lector(String archivo){
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
