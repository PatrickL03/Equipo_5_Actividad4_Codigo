package com.lasalle.contacto.service;

import com.lasalle.contacto.config.AmbienteInfo;
import com.lasalle.contacto.dto.ContactoRequest;
import com.lasalle.contacto.model.Contacto;
import com.lasalle.contacto.repository.ContactoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Contiene la logica de negocio para el formulario de contacto:
 * transforma el DTO validado en una entidad, la etiqueta con el
 * ambiente activo y la guarda a traves del repositorio.
 */
@Service
public class ContactoService {

    private static final Logger log = LoggerFactory.getLogger(ContactoService.class);

    private final ContactoRepository repository;
    private final AmbienteInfo ambienteInfo;

    public ContactoService(ContactoRepository repository, AmbienteInfo ambienteInfo) {
        this.repository = repository;
        this.ambienteInfo = ambienteInfo;
    }

    public Contacto guardar(ContactoRequest request) {
        Contacto contacto = new Contacto();
        contacto.setNombre(request.getNombre().trim());
        contacto.setCorreo(request.getCorreo().trim());
        contacto.setAsunto(request.getAsunto().trim());
        contacto.setMensaje(request.getMensaje().trim());
        contacto.setAmbiente(ambienteInfo.getAmbiente());

        Contacto guardado = repository.save(contacto);
        log.debug("Contacto guardado: id={}, ambiente={}", guardado.getId(), guardado.getAmbiente());
        return guardado;
    }

    public long contar() {
        return repository.count();
    }
}
