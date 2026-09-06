package com.lasalle.contacto.config;

import com.lasalle.contacto.model.Contacto;
import com.lasalle.contacto.repository.ContactoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Inserta un par de registros de ejemplo, CLARAMENTE IDENTIFICABLES
 * como datos de desarrollo (prefijo "[DEV]" y dominio de correo
 * "ejemplo-lasalle.local"), unicamente cuando el perfil activo es
 * "development" y la tabla esta vacia. Nunca se ejecuta en pruebas
 * ni en produccion gracias a @Profile("development").
 */
@Component
@Profile("development")
public class DatosDesarrolloSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatosDesarrolloSeeder.class);

    private final ContactoRepository repository;

    public DatosDesarrolloSeeder(ContactoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            log.debug("Ya existen registros en la base de datos de desarrollo; no se insertan datos de ejemplo.");
            return;
        }

        Contacto uno = new Contacto();
        uno.setNombre("[DEV] Usuario de Prueba Uno");
        uno.setCorreo("dev.usuario1@ejemplo-lasalle.local");
        uno.setAsunto("Registro de ejemplo #1 (dato de desarrollo)");
        uno.setMensaje("Registro generado automáticamente para el ambiente de desarrollo.");
        uno.setAmbiente("development");

        Contacto dos = new Contacto();
        dos.setNombre("[DEV] Usuario de Prueba Dos");
        dos.setCorreo("dev.usuario2@ejemplo-lasalle.local");
        dos.setAsunto("Registro de ejemplo #2 (dato de desarrollo)");
        dos.setMensaje("Segundo registro de ejemplo, solo con fines de desarrollo local.");
        dos.setAmbiente("development");

        repository.save(uno);
        repository.save(dos);

        log.info("Datos de desarrollo de ejemplo insertados en formulario_contacto_dev.");
    }
}
