# TP ESPECIAL

### Bases de datos:
1. Se necesita una bd mySql en el puerto 3306
2. Se necesita una bd mongo en el puerto 27017 para el msvc-parada 

### Levantar los microservicios en orden:
1. msvc-config
2. msvc-eureka
3. msvc-administracion, msvc-monopatin, msvc-parada, msvc-usuario, msvc-viaje
4. msvc-gateway

### El archivo para importar en Postam y probar los endpoints se llama _POSTMAN.JSON_

## Endpoints:

## Monopatin:

### `POST /api/monopatin`
- **Descripcion**: crear un monopatin.
- **Body**:
    ```json
    {
        "paradaId": "4c8c2d0d-e9f2-44f8-9975-cb921c3a3e84"
    }
    ```

### `GET /api/monopatin`
- **Descripcion**: Recupera todos los monopatines.

### `GET /api/monopatin/{id}`
- **Descripcion**: Recupera un monopatin por id.
- **Parametros**: Remplazar {id} por un id valido.

### `PUT /api/monopatin/{id}`
- **Descripcion**: Modifica un monopatin.
- **Parametros**: Remplazar {id} por el id del monopatin a modificar.
- **Body**:
    ```json
    {
      "kilometros":2.3,
      "tiempoSinPausa":10,
      "tiempoConPausa":2,
      "ultimaParadaId":1,
      "enMantenimiento":true
    }
    ```

### `DELETE /api/monopatin/{id}`
- **Descripcion**: Elimina un monopatin.
- **Parametros**: Remplazar {id} por el id del monopatin a eliminar.

### `GET /api/monopatin/mantenimiento/{id}`
- **Descripcion**: Cambia el estado de mantenimiento del monopatin, es decir, se puede poner y sacar de mantenimiento.
- **Parametros**: Remplazar {id} por el id del monopatin a modificar.

### `GET /api/monopatin/viajes?cantidad=2&anio=2024`
- **Descripcion**: Recupera los monopatines filtadros por cantidad de viajes en un determinado año.
- **Parametros**: 

FALTA TERMINAR DE DOCUMENTAR LOS ENDPOINTS