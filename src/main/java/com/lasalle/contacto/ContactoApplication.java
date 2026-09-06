package com.lasalle.contacto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicacion "Formulario de contacto La Salle".
 * El mismo artefacto (JAR) se ejecuta sin cambios en los tres ambientes;
 * lo que cambia es el perfil activo (development | testing | production),
 * definido por la variable de entorno APP_ENV (ver application.properties).
 */
@SpringBootApplication
public class ContactoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactoApplication.class, args);
    }
}
