package com.lasalle.contacto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Centraliza el acceso al ambiente (perfil de Spring) activo y a la
 * bandera app.debug, para que el resto de la aplicacion (servicio,
 * controlador, seeder de datos de desarrollo) no tengan que leerlos
 * cada uno por su cuenta.
 */
@Component
public class AmbienteInfo {

    private final Environment environment;

    @Value("${app.debug:false}")
    private boolean debug;

    public AmbienteInfo(Environment environment) {
        this.environment = environment;
    }

    /** Devuelve el perfil activo: development | testing | production */
    public String getAmbiente() {
        String[] perfiles = environment.getActiveProfiles();
        return perfiles.length > 0 ? perfiles[0] : "development";
    }

    public boolean isDebug() {
        return debug;
    }

    public String getEtiqueta() {
        return switch (getAmbiente()) {
            case "production" -> "PRODUCCIÓN";
            case "testing" -> "PRUEBAS";
            default -> "DESARROLLO";
        };
    }
}
