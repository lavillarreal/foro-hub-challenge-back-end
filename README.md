# ForoHub

# foro-hub-challenge-back-end
Alura

ForoHub es una aplicación backend desarrollada con Spring Boot para gestionar foros de discusión sobre tópicos relacionados con diversos cursos, como arte, programacion y oficios. Los usuarios pueden crear, visualizar, y responder a tópicos en diferentes categorías.

---

## Estructura del Proyecto

El proyecto tiene la siguiente estructura:

```
FOROHUB/
├── Documentation/  # Documentación en Markdown para controladores y entidades
├── src/main/
│   ├── java/com/alura/forohub/
│   │   ├── controllers/  # Controladores REST
│   │   ├── dto/          # Clases DTO (Data Transfer Objects)
│   │   ├── entities/     # Entidades JPA
│   │   ├── repositories/ # Repositorios JPA
│   │   ├── services/     # Servicios de lógica y contexto de base de datos
│   │   └── ForohubApplication.java  # Clase principal
│   ├── resources/
│       ├── application.properties  # Configuración del proyecto
│       └── static/templates/       # Recursos estáticos (si aplica)
├── test/  # Pruebas unitarias
└── pom.xml  # Archivo de configuración de Maven
```

---

## Configuración de Base de Datos

ForoHub utiliza PostgreSQL como base de datos. El archivo `application.properties` contiene la configuración para conectar la aplicación a la base de datos.

### Configuración por defecto:

```properties
spring.application.name=forohub
spring.datasource.url=jdbc:postgresql://localhost:5432/forohub
spring.datasource.username=forohub
spring.datasource.password=forohub
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
server.port=8080
jwt.secret.key=misecretkeysuperseguraquenovaaquedaraca
```

### Creación del esquema de base de datos

Ejecuta el siguiente script SQL para crear el esquema necesario en PostgreSQL:

```sql
CREATE DATABASE forohub;

-- Tabla Curso
CREATE TABLE Curso (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Tabla perfil
CREATE TABLE perfil (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Tabla usuario
CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correoElectronico VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    perfiles BIGINT,
    isadmin BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_perfiles FOREIGN KEY (perfiles) REFERENCES perfil(id)
);

-- Tabla topico
CREATE TABLE topico (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    autor BIGINT,
    curso BIGINT,
    CONSTRAINT fk_autor FOREIGN KEY (autor) REFERENCES usuario(id),
    CONSTRAINT fk_curso FOREIGN KEY (curso) REFERENCES Curso(id)
);

-- Tabla respuesta
CREATE TABLE respuesta (
    id BIGSERIAL PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico BIGINT,
    fechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    autor BIGINT,
    solucion BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_topico FOREIGN KEY (topico) REFERENCES topico(id),
    CONSTRAINT fk_autor FOREIGN KEY (autor) REFERENCES usuario(id)
);
```

---

## Ejecución del Proyecto

### Requisitos previos

- **Java 17** o superior
- **Maven** instalado
- **PostgreSQL** configurado y en ejecución

### Pasos para ejecutar

1. Clona el repositorio del proyecto.
2. Configura PostgreSQL y asegúrate de que los parámetros de `application.properties` coincidan con tu configuración local.
3. Compila el proyecto con Maven:
   ```bash
   mvn clean install
   ```
4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```
5. La API estará disponible en `http://localhost:8080`.

---

## Endpoints Disponibles

### UsuarioController

- **GET /api/usuarios**: Obtiene todos los usuarios (Requiere rol de administrador).
- **GET /api/usuarios/{id}**: Obtiene un usuario por su ID (Requiere rol de administrador).
- **POST /api/usuarios**: Crea un nuevo usuario (Requiere rol de administrador).
- **PUT /api/usuarios/{id}**: Actualiza un usuario existente (Requiere rol de administrador).
- **DELETE /api/usuarios/{id}**: Elimina un usuario por su ID (Requiere rol de administrador).

### TopicoController

- **GET /api/topicos**: Lista todos los tópicos.
- **GET /api/topicos/{id}**: Obtiene un tópico por su ID.
- **POST /api/topicos**: Crea un nuevo tópico.
- **PUT /api/topicos/{id}**: Actualiza un tópico existente.
- **DELETE /api/topicos/{id}**: Elimina un tópico por su ID.

### RespuestaController

- **POST /api/respuestas**: Crea una nueva respuesta para un tópico.
- **PUT /api/respuestas/{id}**: Marca una respuesta como solución.

---

## Seguridad

ForoHub utiliza autenticación basada en JWT. Asegúrate de incluir un encabezado `Authorization: Bearer <token>` en las solicitudes protegidas.

---

## Motivo

¡ Curso Oracle NEXT Education !
