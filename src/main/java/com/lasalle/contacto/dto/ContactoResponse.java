package com.lasalle.contacto.dto;

/**
 * Respuesta enviada al frontend cuando el formulario se procesa
 * correctamente (mensaje de confirmacion + id del registro creado).
 */
public class ContactoResponse {

    private boolean exito;
    private String mensaje;
    private Long id;

    public ContactoResponse() {
    }

    public ContactoResponse(boolean exito, String mensaje, Long id) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.id = id;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
