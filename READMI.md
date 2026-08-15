# Hotel System

API backend desarrollada con Java 17, Spring Boot, Spring Web MVC, Spring Data JPA, Bean Validation, Lombok y PostgreSQL. El proyecto permite gestionar hoteles, habitaciones, huespedes, reservas y servicios adicionales.

## Estructura del proyecto

```text
hotelsystem/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── vdrt/
│   │   │           └── hotelsystem/
│   │   │               ├── HotelsystemApplication.java
│   │   │               ├── controller/
│   │   │               │   ├── HabitacionController.java
│   │   │               │   ├── HotelController.java
│   │   │               │   ├── HuespedController.java
│   │   │               │   ├── ReservaController.java
│   │   │               │   └── ServicioController.java
│   │   │               ├── dto/
│   │   │               │   ├── habitacion/
│   │   │               │   ├── hotel/
│   │   │               │   ├── huesped/
│   │   │               │   ├── perfilcontacto/
│   │   │               │   ├── reserva/
│   │   │               │   └── servicio/
│   │   │               ├── exception/
│   │   │               │   ├── ConflictException.java
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── ResourceNotFoundException.java
│   │   │               ├── model/
│   │   │               │   ├── Habitacion.java
│   │   │               │   ├── Hotel.java
│   │   │               │   ├── Huesped.java
│   │   │               │   ├── PerfilContacto.java
│   │   │               │   ├── Reserva.java
│   │   │               │   ├── Servicio.java
│   │   │               │   └── enums/
│   │   │               │       ├── Estado.java
│   │   │               │       └── Tipo.java
│   │   │               ├── repository/
│   │   │               │   ├── HabitacionRepository.java
│   │   │               │   ├── HotelRepository.java
│   │   │               │   ├── HuespedRepository.java
│   │   │               │   ├── ReservaRepository.java
│   │   │               │   └── ServicioRepository.java
│   │   │               └── service/
│   │   │                   ├── HabitacionService.java
│   │   │                   ├── HotelService.java
│   │   │                   ├── HuespedService.java
│   │   │                   ├── ReservaService.java
│   │   │                   ├── ServicioService.java
│   │   │                   └── impl/
│   │   │                       ├── HabitacionServiceImpl.java
│   │   │                       ├── HotelServiceImpl.java
│   │   │                       ├── HuespedServiceImpl.java
│   │   │                       ├── ReservaServiceImpl.java
│   │   │                       └── ServicioServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── vdrt/
│                   └── hotelsystem/
│                       └── HotelsystemApplicationTests.java
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── READMI.md
```

## Capas principales

- `controller`: expone los endpoints REST de la API.
- `service`: define la logica de negocio mediante interfaces.
- `service.impl`: implementa la logica de negocio y coordina repositorios.
- `repository`: contiene interfaces de acceso a datos con Spring Data JPA.
- `model`: contiene las entidades JPA que representan las tablas de la base de datos.
- `dto`: contiene objetos de entrada, salida y mappers por cada recurso.
- `exception`: centraliza excepciones y manejo global de errores.

## Entidades del dominio

- `Hotel`: representa un hotel con nombre, ciudad, direccion, categoria, telefono y habitaciones.
- `Habitacion`: representa una habitacion asociada a un hotel, con numero, tipo, precio por noche y disponibilidad.
- `Huesped`: representa un cliente del hotel, con datos personales, documento, email y perfil de contacto.
- `PerfilContacto`: contiene telefono, telefono de emergencia, direccion, pais y nacionalidad del huesped.
- `Reserva`: representa una reserva con fechas, estado, total calculado, huesped, habitacion y servicios asociados.
- `Servicio`: representa servicios adicionales disponibles para una reserva.

## Relaciones principales

- Un `Hotel` tiene muchas `Habitacion`.
- Una `Habitacion` pertenece a un `Hotel`.
- Un `Huesped` tiene un `PerfilContacto`.
- Un `Huesped` puede tener muchas `Reserva`.
- Una `Reserva` pertenece a un `Huesped`.
- Una `Reserva` pertenece a una `Habitacion`.
- Una `Reserva` puede tener muchos `Servicio`.
- Un `Servicio` puede estar asociado a muchas reservas.

## Enumeraciones

- `Tipo`: `SIMPLE`, `DOBLE`, `SUITE`.
- `Estado`: `PENDIENTE`, `CONFIRMADA`, `CANCELADA`, `COMPLETADA`.

## Endpoints principales

### Hoteles

- `GET /api/hotels`: listar todos los hoteles.
- `GET /api/hotels/{id}`: buscar hotel por ID.
- `GET /api/hotels/disponibles`: listar hoteles con habitaciones disponibles.
- `POST /api/hotels`: crear hotel.
- `PUT /api/hotels/{id}`: actualizar hotel.
- `DELETE /api/hotels/{id}`: eliminar hotel.

### Habitaciones

- `GET /api`: listar todas las habitaciones.
- `GET /api/habitaciones/{id}`: buscar habitacion por ID.
- `GET /api/hotels/{hotelId}/habitaciones/disponibles`: listar habitaciones disponibles por hotel.
- `GET /api/habitaciones/precio-maximo?precio={valor}`: buscar habitaciones por precio maximo.
- `POST /api/hotels/{hotelId}/habitaciones`: crear habitacion para un hotel.
- `PUT /api/habitaciones/{id}`: actualizar habitacion.
- `DELETE /api/habitaciones/{id}`: eliminar habitacion.

### Huespedes

- `GET /api/huespedes`: listar todos los huespedes.
- `GET /api/huespedes/{id}`: buscar huesped por ID.
- `GET /api/huespedes/buscar?texto={valor}`: buscar huesped por nombre o apellido.
- `POST /api/huespedes`: crear huesped.
- `PUT /api/huespedes/{id}`: actualizar huesped.
- `DELETE /api/huespedes/{id}`: eliminar huesped.

### Reservas

- `GET /api/reservas`: listar todas las reservas.
- `GET /api/reservas/{id}`: buscar reserva por ID.
- `GET /api/reservas/huesped/{huespedId}`: listar reservas por huesped.
- `GET /api/reservas/estado?estado={estado}`: listar reservas por estado.
- `POST /api/reservas`: crear reserva.
- `PUT /api/reservas/{id}`: actualizar reserva.
- `PATCH /api/reservas/{id}/cancelar`: cancelar reserva.
- `POST /api/reservas/{id}/servicios/{servicioId}`: agregar servicio a una reserva.

### Servicios

- `GET /api/servicios`: listar todos los servicios.
- `GET /api/servicios/disponibles`: listar servicios disponibles.
- `GET /api/servicios/{id}`: buscar servicio por ID.
- `POST /api/servicios`: crear servicio.
- `PUT /api/servicios/{id}`: actualizar servicio.
- `DELETE /api/servicios/{id}`: eliminar servicio.

## Configuracion

La configuracion principal se encuentra en:

```text
src/main/resources/application.properties
```

Valores actuales:

```properties
spring.application.name=hotelsystem
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_system_db
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8090
```

## Ejecucion

En Windows:

```bash
./mvnw.cmd spring-boot:run
```

En Linux o macOS:

```bash
./mvnw spring-boot:run
```

La API queda disponible en:

```text
http://localhost:8090
```

## Documentacion Swagger

El proyecto incluye `springdoc-openapi-starter-webmvc-ui`. Con la aplicacion en ejecucion, la documentacion puede consultarse normalmente en:

```text
http://localhost:8090/swagger-ui.html
```
