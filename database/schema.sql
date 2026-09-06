-- schema.sql
-- Esquema de referencia de la tabla "contactos" en MySQL.
-- Hibernate (spring.jpa.hibernate.ddl-auto=update) la crea y actualiza
-- automáticamente a partir de la entidad Contacto.java, por lo que NO
-- es necesario ejecutar este script manualmente. Se incluye únicamente
-- como documentación del modelo de datos.

CREATE DATABASE IF NOT EXISTS formulario_contacto_dev;
CREATE DATABASE IF NOT EXISTS formulario_contacto_test;

USE formulario_contacto_dev;

CREATE TABLE IF NOT EXISTS contactos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    correo VARCHAR(150) NOT NULL,
    asunto VARCHAR(150) NOT NULL,
    mensaje TEXT NOT NULL,
    ambiente VARCHAR(30) NOT NULL,           -- development | testing | production
    fecha_registro DATETIME NOT NULL
);
