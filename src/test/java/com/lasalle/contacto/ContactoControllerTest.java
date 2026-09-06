package com.lasalle.contacto;

import com.lasalle.contacto.dto.ContactoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pruebas de integración automatizadas para el AMBIENTE DE PRUEBAS.
 * Ejecutan y verifican los tres casos obligatorios de la actividad:
 *   1) Envío correcto del formulario.
 *   2) Intento de envío con un campo obligatorio vacío.
 *   3) Intento de envío con un correo electrónico inválido.
 *
 * Requieren que la base de datos MySQL "formulario_contacto_test"
 * exista y sea alcanzable (por ejemplo, vía XAMPP en localhost:3306),
 * ya que corren con el perfil "testing" (ver application-testing.properties).
 *
 * Ejecutar con:  mvn test -Dspring.profiles.active=testing
 * (o simplemente "mvn test" si APP_ENV=testing ya está definido en el entorno)
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testing")
class ContactoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> enviarContacto(ContactoRequest cuerpo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.postForEntity("/api/contacto", new HttpEntity<>(cuerpo, headers), String.class);
    }

    private ContactoRequest construirSolicitudValida() {
        ContactoRequest r = new ContactoRequest();
        r.setNombre("Juan Pérez");
        r.setCorreo("juan.perez@correo.com");
        r.setAsunto("Consulta sobre proceso de admisión");
        r.setMensaje("Buenas tardes, quisiera información sobre fechas de inscripción.");
        return r;
    }

    @Test
    void caso1_envioCorrectoDelFormulario() {
        ResponseEntity<String> respuesta = enviarContacto(construirSolicitudValida());

        assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respuesta.getBody()).contains("\"exito\":true");
        assertThat(respuesta.getBody()).contains("se envió correctamente");
    }

    @Test
    void caso2_campoObligatorioVacio() {
        ContactoRequest solicitud = construirSolicitudValida();
        solicitud.setNombre(""); // Nombre vacío

        ResponseEntity<String> respuesta = enviarContacto(solicitud);

        assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(respuesta.getBody()).contains("obligatorio");
    }

    @Test
    void caso3_correoConFormatoInvalido() {
        ContactoRequest solicitud = construirSolicitudValida();
        solicitud.setCorreo("ana.gomez-correo.com"); // Sin arroba: formato inválido

        ResponseEntity<String> respuesta = enviarContacto(solicitud);

        assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(respuesta.getBody()).contains("correo electrónico no es válido");
    }
}
