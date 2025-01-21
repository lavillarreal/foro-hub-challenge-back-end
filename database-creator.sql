-- Crear usuario en la DB
CREATE ROLE forohub WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT 500
	PASSWORD 'xxxxxx';
COMMENT ON ROLE forohub IS 'ForohubAdmin for alura course';

-- Crear esquema de base de datos para ForoHub
CREATE DATABASE forohub
    WITH
    OWNER = forohub
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE forohub
    IS 'forohub database for alura project';

-- Crear usuario en la DB
CREATE ROLE forohub WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT 500
	PASSWORD 'xxxxxx';
COMMENT ON ROLE forohub IS 'ForohubAdmin for alura course';

-- Eliminar tablas viejas

drop table if exists curso, perfil, usuario, topico, respuesta CASCADE;

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

-- Asignar owner y roles de las tablas al usuario
GRANT ALL PRIVILEGES ON DATABASE forohub TO forohub;

GRANT ALL PRIVILEGES ON TABLE curso TO forohub;

GRANT ALL PRIVILEGES ON TABLE perfil TO forohub;

GRANT ALL PRIVILEGES ON TABLE usuario TO forohub;

GRANT ALL PRIVILEGES ON TABLE topico TO forohub;

GRANT ALL PRIVILEGES ON TABLE respuesta TO forohub;

ALTER TABLE curso OWNER TO forohub;

ALTER TABLE perfil OWNER TO forohub;

ALTER TABLE usuario OWNER TO forohub;

ALTER TABLE topico OWNER TO forohub;

ALTER TABLE respuesta OWNER TO forohub;