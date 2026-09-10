# Hotel System

API REST para la gestión integral de un sistema hotelero: hoteles, habitaciones, huéspedes, reservas y servicios adicionales, protegida con autenticación y autorización basada en JWT.

Desarrollada con **Java 17**, **Spring Boot**, **Spring Web MVC**, **Spring Data JPA**, **Spring Security**, **Bean Validation**, **Lombok**, **JJWT** y **PostgreSQL**.

## Tabla de contenido

- [Características](#características)
- [Stack tecnológico](#stack-tecnológico)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Modelo de dominio](#modelo-de-dominio)
- [Autenticación y autorización](#autenticación-y-autorización)
- [Endpoints de la API](#endpoints-de-la-api)
- [Configuración](#configuración)
- [Puesta en marcha](#puesta-en-marcha)
- [Documentación Swagger](#documentación-swagger)
- [Pruebas](#pruebas)
- [Manejo de errores](#manejo-de-errores)

## Características

- CRUD completo de hoteles, habitaciones, huéspedes, reservas y servicios.
- Autenticación con **JWT** (access token + refresh token) y sesiones *stateless*.
- Refresh tokens persistidos en base de datos, revocables y rotados en cada uso.
- Autorización basada en roles (`ADMIN`, `RECEPCIONISTA`, `HUESPED`) mediante `hasAnyRole` y `@EnableMethodSecurity`.
- Capa de DTOs con *mappers* manuales para no exponer las entidades JPA directamente.
- Manejo centralizado de errores (`GlobalExceptionHandler`) y excepciones de dominio (`ResourceNotFoundException`, `ConflictException`).
- Documentación interactiva con Swagger / OpenAPI, incluyendo autenticación Bearer.
- Configuración externalizada por variables de entorno mediante `spring.config.import` (`.env`).

## Stack tecnológico

| Categoría        | Tecnología                                           |
|-------------------|-------------------------------------------------------|
| Lenguaje          | Java 17                                                |
| Framework         | Spring Boot (Spring Web MVC, Spring Data JPA, Spring Security) |
| Seguridad         | JWT (`jjwt-api`, `jjwt-impl`, `jjwt-gson`), BCrypt     |
| Base de datos     | PostgreSQL                                             |
| Validación        | Jakarta Bean Validation                                |
| Documentación     | springdoc-openapi (Swagger UI)                         |
| Utilidades        | Lombok                                                 |
| Build tool        | Maven (`mvnw` / `mvnw.cmd`)                            |

## Estructura del proyecto

```text
hotelsystem/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/com/vdrt/hotelsystem/
│   │   │   ├── HotelsystemApplication.java
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── HabitacionController.java
│   │   │   │   ├── HotelController.java
│   │   │   │   ├── HuespedController.java
│   │   │   │   ├── ReservaController.java
│   │   │   │   └── ServicioController.java
│   │   │   ├── dto/
│   │   │   │   ├── auth/
│   │   │   │   ├── habitacion/
│   │   │   │   ├── hotel/
│   │   │   │   ├── huesped/
│   │   │   │   ├── perfilcontacto/
│   │   │   │   ├── reserva/
│   │   │   │   └── servicio/
│   │   │   ├── exception/
│   │   │   │   ├── ConflictException.java
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── model/
│   │   │   │   ├── Habitacion.java
│   │   │   │   ├── Hotel.java
│   │   │   │   ├── Huesped.java
│   │   │   │   ├── PerfilContacto.java
│   │   │   │   ├── RefreshToken.java
│   │   │   │   ├── Reserva.java
│   │   │   │   ├── Servicio.java
│   │   │   │   ├── Usuario.java
│   │   │   │   └── enums/
│   │   │   │       ├── Estado.java
│   │   │   │       ├── Rol.java
│   │   │   │       └── Tipo.java
│   │   │   ├── repository/
│   │   │   │   ├── HabitacionRepository.java
│   │   │   │   ├── HotelRepository.java
│   │   │   │   ├── HuespedRepository.java
│   │   │   │   ├── RefreshTokenRepository.java
│   │   │   │   ├── ReservaRepository.java
│   │   │   │   ├── ServicioRepository.java
│   │   │   │   └── UsuarioRepository.java
│   │   │   ├── security/
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── JwtService.java
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── UserDetailsServiceImpl.java
│   │   │   │   ├── UsuarioDetailsImpl.java
│   │   │   │   └── handler/
│   │   │   │       ├── JwtAccessDeniedHandler.java
│   │   │   │       └── JwtAuthenticationEntryPoint.java
│   │   │   └── service/
│   │   │       ├── AuthService.java
│   │   │       ├── HabitacionService.java
│   │   │       ├── HotelService.java
│   │   │       ├── HuespedService.java
│   │   │       ├── ReservaService.java
│   │   │       ├── ServicioService.java
│   │   │       └── impl/
│   │   │           ├── AuthServiceImpl.java
│   │   │           ├── HabitacionServiceImpl.java
│   │   │           ├── HotelServiceImpl.java
│   │   │           ├── HuespedServiceImpl.java
│   │   │           ├── ReservaServiceImpl.java
│   │   │           └── ServicioServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/vdrt/hotelsystem/
│           ├── HotelsystemApplicationTests.java
│           └── security/
│               └── JwtServiceTest.java
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

## Capas principales

- **`controller`**: expone los endpoints REST de la API.
- **`service` / `service.impl`**: define e implementa la lógica de negocio, coordinando repositorios.
- **`repository`**: interfaces de acceso a datos con Spring Data JPA.
- **`model`**: entidades JPA que representan las tablas de la base de datos.
- **`dto`**: objetos de entrada/salida y *mappers* por recurso, para no exponer las entidades directamente.
- **`security`**: filtro JWT, servicio de tokens, configuración de Spring Security y manejadores de errores de autenticación/autorización.
- **`exception`**: excepciones de dominio y manejo global de errores HTTP.
- **`config`**: configuración transversal (OpenAPI/Swagger).

## Modelo de dominio

- **`Hotel`**: nombre, ciudad, dirección, categoría, teléfono y habitaciones asociadas.
- **`Habitacion`**: número, tipo, precio por noche y disponibilidad, asociada a un hotel.
- **`Huesped`**: datos personales, documento, email y perfil de contacto.
- **`PerfilContacto`**: teléfono, teléfono de emergencia, dirección, país y nacionalidad del huésped.
- **`Reserva`**: fechas, estado, total calculado, huésped, habitación y servicios asociados.
- **`Servicio`**: servicios adicionales disponibles para una reserva.
- **`Usuario`**: cuenta de acceso (email, password cifrada, rol, estado habilitado), opcionalmente vinculada a un `Huesped`.
- **`RefreshToken`**: token de refresco persistido, asociado a un `Usuario`, con fecha de expiración y estado de revocación.

### Relaciones principales

- Un `Hotel` tiene muchas `Habitacion`; una `Habitacion` pertenece a un `Hotel`.
- Un `Huesped` tiene un `PerfilContacto` y puede tener muchas `Reserva`.
- Una `Reserva` pertenece a un `Huesped` y a una `Habitacion`, y puede tener muchos `Servicio`.
- Un `Usuario` puede estar vinculado a un `Huesped` y tiene muchos `RefreshToken`.

### Enumeraciones

- **`Tipo`**: `SIMPLE`, `DOBLE`, `SUITE`.
- **`Estado`**: `PENDIENTE`, `CONFIRMADA`, `CANCELADA`, `COMPLETADA`.
- **`Rol`**: `ADMIN`, `RECEPCIONISTA`, `HUESPED`.

## Autenticación y autorización

El proyecto implementa una capa de seguridad completa basada en **JWT** sobre **Spring Security**:

- **Sesiones *stateless*** (`SessionCreationPolicy.STATELESS`) y CSRF deshabilitado, propios de una API REST consumida por clientes externos.
- **`JwtService`**: genera y valida access tokens y refresh tokens firmados, incluyendo el rol del usuario como *claim*.
- **`JwtAuthenticationFilter`**: intercepta cada petición, valida el token del header `Authorization: Bearer <token>` y construye el contexto de seguridad.
- **`RefreshToken` persistido en base de datos**: permite revocar sesiones activas y se **rota** (se invalida el anterior y se emite uno nuevo) en cada llamada a `/refresh`.
- **Verificación de estado por request**: el campo `habilitado` del `Usuario` se valida contra la base de datos en cada petición autenticada.
- **`UserDetailsServiceImpl` / `UsuarioDetailsImpl`**: adaptan `Usuario` al modelo de `UserDetails` de Spring Security.
- **Manejadores personalizados**: `JwtAuthenticationEntryPoint` (401 - no autenticado) y `JwtAccessDeniedHandler` (403 - sin permisos).
- **Autorización por rol**: rutas de administración restringidas con `hasAnyRole(...)`, resto de rutas protegidas con `authenticated()`; los endpoints de `/api/v1/auth/**` y de documentación (`/swagger-ui/**`, `/v3/api-docs/**`) son públicos.

### Flujo de uso

1. `POST /api/v1/auth/login` con `email` y `password` → devuelve `accessToken`, `refreshToken` y `rol`.
2. Se envía el `accessToken` en el header `Authorization: Bearer <accessToken>` en cada petición a los endpoints protegidos.
3. Cuando el `accessToken` expira, se llama a `POST /api/v1/auth/refresh` con el `refreshToken` vigente para obtener un nuevo par de tokens.
4. `POST /api/v1/auth/logout` revoca el `refreshToken` indicado, cerrando la sesión.

## Endpoints de la API

### Autenticación (`/api/v1/auth`)

| Método | Endpoint   | Descripción                                  | Acceso  |
|--------|------------|-----------------------------------------------|---------|
| POST   | `/login`   | Autentica un usuario y devuelve los tokens    | Público |
| POST   | `/refresh` | Rota el refresh token y genera nuevos tokens  | Público |
| POST   | `/logout`  | Revoca el refresh token indicado              | Público |

### Hoteles (`/api/hotels`)

| Método | Endpoint          | Descripción                                    |
|--------|-------------------|--------------------------------------------------|
| GET    | `/`                | Listar todos los hoteles                        |
| GET    | `/{id}`            | Buscar hotel por ID                              |
| GET    | `/disponibles`     | Listar hoteles con habitaciones disponibles      |
| POST   | `/`                | Crear hotel                                      |
| PUT    | `/{id}`            | Actualizar hotel                                 |
| DELETE | `/{id}`            | Eliminar hotel                                   |

### Habitaciones (`/api`)

| Método | Endpoint                                              | Descripción                                  |
|--------|----------------------------------------------------------|-----------------------------------------------|
| GET    | `/`                                                       | Listar todas las habitaciones                |
| GET    | `/habitaciones/{id}`                                      | Buscar habitación por ID                     |
| GET    | `/hotels/{hotelId}/habitaciones/disponibles`              | Listar habitaciones disponibles por hotel    |
| GET    | `/habitaciones/precio-maximo?precio={valor}`              | Buscar habitaciones por precio máximo        |
| POST   | `/hotels/{hotelId}/habitaciones`                          | Crear habitación para un hotel               |
| PUT    | `/habitaciones/{id}`                                      | Actualizar habitación                        |
| DELETE | `/habitaciones/{id}`                                      | Eliminar habitación                          |

### Huéspedes (`/api/huespedes`)

| Método | Endpoint          | Descripción                                 |
|--------|-------------------|-----------------------------------------------|
| GET    | `/`                | Listar todos los huéspedes                   |
| GET    | `/{id}`            | Buscar huésped por ID                        |
| GET    | `/buscar?texto=`   | Buscar huésped por nombre o apellido         |
| POST   | `/`                | Crear huésped                                |
| PUT    | `/{id}`            | Actualizar huésped                           |
| DELETE | `/{id}`            | Eliminar huésped                             |

### Reservas (`/api/reservas`)

| Método | Endpoint                              | Descripción                             |
|--------|------------------------------------------|--------------------------------------------|
| GET    | `/`                                       | Listar todas las reservas                |
| GET    | `/{id}`                                   | Buscar reserva por ID                    |
| GET    | `/huesped/{huespedId}`                    | Listar reservas por huésped              |
| GET    | `/estado?estado={estado}`                 | Listar reservas por estado               |
| POST   | `/`                                       | Crear reserva                            |
| PUT    | `/{id}`                                   | Actualizar reserva                       |
| PATCH  | `/{id}/cancelar`                          | Cancelar reserva                         |
| POST   | `/{id}/servicios/{servicioId}`            | Agregar servicio a una reserva           |

### Servicios (`/api/servicios`)

| Método | Endpoint          | Descripción                                 |
|--------|-------------------|-----------------------------------------------|
| GET    | `/`                | Listar todos los servicios                   |
| GET    | `/disponibles`     | Listar servicios disponibles                 |
| GET    | `/{id}`            | Buscar servicio por ID                       |
| POST   | `/`                | Crear servicio                               |
| PUT    | `/{id}`            | Actualizar servicio                          |
| DELETE | `/{id}`            | Eliminar servicio                            |

> Todos los endpoints salvo `/api/v1/auth/**` y la documentación Swagger requieren un `accessToken` JWT válido en el header `Authorization`.

## Configuración

La configuración se encuentra en `src/main/resources/application.properties` y se carga de forma externalizada desde un archivo `.env` en la raíz del proyecto (no versionado, ver `.gitignore`):

```properties
spring.application.name=hotelsystem

spring.config.import=optional:file:.env[.properties]
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8090

jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION}
jwt.refresh-expiration=${JWT_REFRESH_EXPIRATION}
```

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```dotenv
DB_URL=jdbc:postgresql://localhost:5432/hotel_system_db
DB_USER=postgres
DB_PASSWORD=tu_password

JWT_SECRET=una_clave_secreta_larga_y_segura
JWT_EXPIRATION=3600000
JWT_REFRESH_EXPIRATION=604800000
```

> `JWT_EXPIRATION` y `JWT_REFRESH_EXPIRATION` se expresan en milisegundos (ejemplo: 1 hora y 7 días respectivamente).

## Puesta en marcha

### Requisitos previos

- Java 17+
- PostgreSQL en ejecución con una base de datos creada (por ejemplo `hotel_system_db`)
- Archivo `.env` configurado como se describe arriba

### Ejecución

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

## Documentación Swagger

El proyecto incluye `springdoc-openapi-starter-webmvc-ui` con soporte para autenticación Bearer. Con la aplicación en ejecución, la documentación puede consultarse en:

```text
http://localhost:8090/swagger-ui.html
```

Desde ahí es posible autenticarse con el `accessToken` obtenido en `/api/v1/auth/login` y probar los endpoints protegidos directamente.

## Pruebas

El proyecto incluye pruebas unitarias, entre ellas `JwtServiceTest` para la generación y validación de tokens. Para ejecutarlas:

```bash
./mvnw test
```

## Manejo de errores

Los errores se centralizan en `GlobalExceptionHandler`, que traduce las excepciones de dominio a respuestas HTTP consistentes:

- `ResourceNotFoundException` → `404 Not Found`
- `ConflictException` → `409 Conflict`
- Errores de autenticación → `401 Unauthorized` (vía `JwtAuthenticationEntryPoint`)
- Errores de autorización → `403 Forbidden` (vía `JwtAccessDeniedHandler`)
