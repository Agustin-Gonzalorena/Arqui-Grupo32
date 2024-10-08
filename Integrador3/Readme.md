# Integrador 3 

## _API Endopints_

**Url para importar en Postman:**

https://api.postman.com/collections/38850954-52690157-55fe-428a-8c87-7eaddedb3bbe?access_key=PMAT-01J9PJFFMC5S47PCVA9WW40DTT

## Estudiantes:

### `GET /api/estudiante`
- **Descripcion**: Recupera todos los estudiantes.

### `POST /api/estudiante`
- **Descripcion**: Dar de alta un nuevo estudiante.
- **Body**: 
    ```json
    {   
        "nombre":"Franco",
        "apellido":"Colapinto",
        "genero":"masculino",
        "dni":434343,
        "ciudadResidencia":"Pilar",
        "nroLibretaUniversitaria":43 
    }

### `GET /api/estudiante?sort=nombre,asc`
- **Descripcion**: Recupera todos los estudiantes y especificar criterio de ordenamiento.
- **Parametros**: 
  - sort= 'nombre','apellido', 'ciudad', 'dni', 'nroLibretaUniversitaria'

### `GET /api/estudiante/:nroLibretaUniversitaria`
- **Descripcion**: Recupera un estudiante,en base a su numero de libreta universitaria.
- **Parametros**:
  - Numero de libreta universitaria
- **Observaciones**: Los estudiantes pre-cargados van desde 50800-50829

### `GET /api/estudiante?genero=masculino`
- **Descripcion**: Recupera todos los estudiantes, en base a su género.
- **Parametros**:
  - genero= 'masculino', 'femenino' o 'otro'

### `GET /api/estudiante?carrera_id=1&ciudad=tandil`
- **Descripcion**: Recupera todos los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
- **Parametros**:
  - carrera= 'tudai=1', 'psicologia=2', 'derecho=3', 'veterinaria=4', 'medicina=5','martillero publico=6'
  - ciudad= 'tandil', 'buenos_aires','mar_del_plata'
- **Observaciones**: 
  - En vez de utilizar espacios usar ( _ ) Ejemplo: 'Mar del plata' = 'Mar_del_plata'

## Inscripcion:

### `POST /api/inscripcion`
- **Descripcion**: Matricula un estudiante a una carrera.
- **Body**:
    ```json
    {   
        "estudiante_dni":434343,
        "carrera_id":2
    }
- **Observaciones**: Los id de carreras ya cargadas van del 1-6.

## Carrera:

### `GET /api/carrera`
- **Descripcion**: Recupera todos las carreras.

### `GET /api/carrera/inscriptos`
- **Descripcion**: Recupera las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.


### `GET /api/carrera/reporte`
- **Descripcion**: Genera un reporte de las carreras con informacion por año de inscriptos y graduados, ordenadas las carreras
       alfabéticamente, y los años de manera cronológica.
