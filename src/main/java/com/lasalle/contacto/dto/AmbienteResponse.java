package com.lasalle.contacto.dto;

/**
 * Informacion del ambiente activo que consume el frontend para
 * mostrar la insignia ("badge") de ambiente y, solo si la
 * depuracion esta habilitada, el panel de depuracion.
 * En produccion, "debug" siempre viene en false y "totalContactos"
 * viene en null, para no exponer informacion interna.
 */
public class AmbienteResponse {

    private String ambiente;
    private String etiqueta;
    private boolean debug;
    private Long totalContactos;

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public Long getTotalContactos() {
        return totalContactos;
    }

    public void setTotalContactos(Long totalContactos) {
        this.totalContactos = totalContactos;
    }
}
