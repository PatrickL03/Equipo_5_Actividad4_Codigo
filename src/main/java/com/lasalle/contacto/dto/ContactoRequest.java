package com.lasalle.contacto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Size(max = 30, message = "El telefono no puede superar 30 caracteres.")
    private String telefono;

    @NotBlank(message = "El campo \"Asunto\" es obligatorio.")
    @Size(max = 150, message = "El asunto no puede superar 150 caracteres.")
    private String asunto;

    @NotBlank(message = "El campo \"Mensaje\" es obligatorio.")
    private String mensaje;

    /**
     * Campo OPCIONAL: puede llegar vacío. Si el usuario escribe algo, debe
     * tener un formato razonable de teléfono (dígitos, espacios, +, -, paréntesis,
     * entre 7 y 20 caracteres). La expresión acepta también la cadena vacía,
     * porque @Pattern por sí sola no distingue "opcional pero con formato".
     */
    @Pattern(
        regexp = "^$|^[0-9+\\-\\s()]{7,20}$",
        message = "El formato del teléfono no es válido."
    )
    private String Telefono;

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
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
