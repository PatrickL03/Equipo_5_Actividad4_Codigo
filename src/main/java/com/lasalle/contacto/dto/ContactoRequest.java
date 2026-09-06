package com.lasalle.contacto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Datos que llegan desde el formulario (JSON) al endpoint
 * POST /api/contacto. Las anotaciones de Jakarta Bean Validation
 * se encargan de la validacion de campos obligatorios y del
 * formato del correo electronico en el SERVIDOR (la validacion
 * en el navegador, en app.js, es solo una ayuda de experiencia
 * de usuario y nunca reemplaza esta validacion).
 */
public class ContactoRequest {

    @NotBlank(message = "El campo \"Nombre\" es obligatorio.")
    @Size(max = 120, message = "El nombre no puede superar 120 caracteres.")
    private String nombre;

    @NotBlank(message = "El campo \"Correo electrónico\" es obligatorio.")
    @Email(message = "El formato del correo electrónico no es válido.")
    @Size(max = 150, message = "El correo no puede superar 150 caracteres.")
    private String correo;

    @NotBlank(message = "El campo \"Asunto\" es obligatorio.")
    @Size(max = 150, message = "El asunto no puede superar 150 caracteres.")
    private String asunto;

    @NotBlank(message = "El campo \"Mensaje\" es obligatorio.")
    private String mensaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
