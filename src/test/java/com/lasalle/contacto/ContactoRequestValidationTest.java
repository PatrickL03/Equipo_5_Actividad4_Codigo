
package com.lasalle.contacto;

import com.lasalle.contacto.dto.ContactoRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContactoRequestValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void configurarValidador() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void cerrarValidador() {
        validatorFactory.close();
    }

    private ContactoRequest crearSolicitudValida() {
        ContactoRequest solicitud = new ContactoRequest();
        solicitud.setNombre("Juan Perez");
        solicitud.setCorreo("juan@correo.com");
        solicitud.setTelefono("3001234567");
        solicitud.setAsunto("Consulta academica");
        solicitud.setMensaje("Solicito informacion sobre inscripciones.");
        return solicitud;
    }

    @Test
    void U01_telefonoConFormatoValido() {
        ContactoRequest solicitud = crearSolicitudValida();

        var errores = validator.validate(solicitud);

        assertThat(errores).isEmpty();
    }

    @Test
    void U02_telefonoConFormatoInvalido() {
        ContactoRequest solicitud = crearSolicitudValida();
        solicitud.setTelefono("abc123");

        var errores = validator.validate(solicitud);

        assertThat(errores)
            .anySatisfy(error -> {
                assertThat(error.getPropertyPath().toString())
                    .isEqualTo("telefono");
                assertThat(error.getMessage())
                    .contains("formato");
            });
    }
}
  