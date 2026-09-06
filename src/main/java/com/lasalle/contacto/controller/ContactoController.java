package com.lasalle.contacto.controller;

import com.lasalle.contacto.config.AmbienteInfo;
import com.lasalle.contacto.dto.AmbienteResponse;
import com.lasalle.contacto.dto.ContactoRequest;
import com.lasalle.contacto.dto.ContactoResponse;
import com.lasalle.contacto.model.Contacto;
import com.lasalle.contacto.service.ContactoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expone la API REST que consume el frontend (public/ static/ index.html + app.js):
 *  - POST /api/contacto  -> procesa el envio del formulario
 *  - GET  /api/ambiente  -> informa el ambiente activo (para la insignia y el panel de depuracion)
 *
 * La validacion de los campos (obligatorios + formato de correo) se
 * dispara automaticamente por la anotacion @Valid sobre ContactoRequest;
 * los errores los captura ManejadorErroresGlobal.
 */
@RestController
@RequestMapping("/api")
public class ContactoController {

    private static final Logger log = LoggerFactory.getLogger(ContactoController.class);

    private final ContactoService contactoService;
    private final AmbienteInfo ambienteInfo;

    public ContactoController(ContactoService contactoService, AmbienteInfo ambienteInfo) {
        this.contactoService = contactoService;
        this.ambienteInfo = ambienteInfo;
    }

    @PostMapping("/contacto")
    public ResponseEntity<ContactoResponse> crearContacto(@Valid @RequestBody ContactoRequest request,
                                                            HttpServletRequest httpRequest) {
        log.debug("Solicitud de contacto recibida desde {}", httpRequest.getRemoteAddr());

        Contacto guardado = contactoService.guardar(request);
        log.info("Contacto almacenado correctamente. id={}, ambiente={}", guardado.getId(), guardado.getAmbiente());

        ContactoResponse respuesta = new ContactoResponse(
                true,
                "¡Tu mensaje se envió correctamente! Gracias por escribirnos, pronto nos pondremos en contacto contigo.",
                guardado.getId()
        );
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/ambiente")
    public ResponseEntity<AmbienteResponse> informacionAmbiente() {
        AmbienteResponse respuesta = new AmbienteResponse();
        respuesta.setAmbiente(ambienteInfo.getAmbiente());
        respuesta.setEtiqueta(ambienteInfo.getEtiqueta());
        respuesta.setDebug(ambienteInfo.isDebug());
        respuesta.setTotalContactos(ambienteInfo.isDebug() ? contactoService.contar() : null);
        return ResponseEntity.ok(respuesta);
    }
}
