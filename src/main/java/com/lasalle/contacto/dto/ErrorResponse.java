package com.lasalle.contacto.dto;

import java.util.List;

/**
 * Respuesta uniforme para errores de validacion o errores generales
 * del backend. El frontend (app.js) recorre la lista "errores" y la
 * muestra dentro de la alerta roja del formulario.
 */
public class ErrorResponse {

    private boolean exito = false;
    private List<String> errores;

    public ErrorResponse() {
    }

    public ErrorResponse(List<String> errores) {
        this.errores = errores;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
}
