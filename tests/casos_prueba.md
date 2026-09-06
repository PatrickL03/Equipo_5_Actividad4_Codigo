# Casos de prueba — Ambiente de pruebas

**Ambiente:** `testing` (perfil de Spring `application-testing.properties`)
**Base de datos usada:** MySQL `formulario_contacto_test` (independiente de desarrollo y producción)
**Log usado:** `logs/app-testing.log`

Los tres casos se documentaron de dos formas:
1. **Automatizada:** clase `src/test/java/com/lasalle/contacto/ContactoControllerTest.java`, ejecutable con `mvn test`.
2. **Manual:** desde el navegador, contra la aplicación corriendo con `APP_ENV=testing`.

Para la verificación manual se arrancó la aplicación así:

```bash
export APP_ENV=testing
mvn spring-boot:run
```

Y se abrió `http://localhost:8080` en el navegador.

---

## Caso 1 — Envío correcto del formulario

**Datos usados:**

| Campo   | Valor                              |
|---------|-------------------------------------|
| Nombre  | Juan Pérez                          |
| Correo  | juan.perez@correo.com               |
| Asunto  | Consulta sobre proceso de admisión  |
| Mensaje | Buenas tardes, quisiera información sobre fechas de inscripción. |

**Solicitud HTTP equivalente (para pruebas con curl/Postman):**
```bash
curl -X POST http://localhost:8080/api/contacto \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan Pérez","correo":"juan.perez@correo.com","asunto":"Consulta sobre proceso de admisión","mensaje":"Buenas tardes, quisiera información sobre fechas de inscripción."}'
```

**Resultado esperado:** `HTTP 200`, cuerpo con `"exito": true` y el mensaje de confirmación; un nuevo registro insertado en `formulario_contacto_test.contactos`.

**Resultado obtenido:** ✅ Coincide con lo esperado, tanto en la prueba manual (mensaje de confirmación en verde, formulario vacío) como en la prueba automatizada (`caso1_envioCorrectoDelFormulario`, que verifica `HTTP 200` y `"exito":true`).

---

## Caso 2 — Intento de envío con un campo obligatorio vacío

**Datos usados:**

| Campo   | Valor                  |
|---------|------------------------|
| Nombre  | *(vacío)*              |
| Correo  | juan.perez@correo.com  |
| Asunto  | Consulta               |
| Mensaje | Mensaje de prueba      |

**Solicitud HTTP equivalente:**
```bash
curl -i -X POST http://localhost:8080/api/contacto \
  -H "Content-Type: application/json" \
  -d '{"nombre":"","correo":"juan.perez@correo.com","asunto":"Consulta","mensaje":"Mensaje de prueba"}'
```

**Resultado esperado:** `HTTP 400`, cuerpo con `"exito": false` y el mensaje `El campo "Nombre" es obligatorio.`; no se crea ningún registro nuevo.

**Resultado obtenido:** ✅ Coincide con lo esperado. Se probó tanto con la validación de JavaScript activa (el campo se marca en rojo antes de enviar) como deshabilitando JavaScript en el navegador (el servidor devuelve `HTTP 400` con el mensaje de error). La prueba automatizada `caso2_campoObligatorioVacio` confirma el código `400` y que el cuerpo contiene la palabra "obligatorio".

---

## Caso 3 — Intento de envío con un correo electrónico inválido

**Datos usados:**

| Campo   | Valor                    |
|---------|---------------------------|
| Nombre  | Ana Gómez                 |
| Correo  | ana.gomez-correo.com *(sin arroba, formato inválido)* |
| Asunto  | Consulta                  |
| Mensaje | Mensaje de prueba         |

**Solicitud HTTP equivalente:**
```bash
curl -i -X POST http://localhost:8080/api/contacto \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ana Gómez","correo":"ana.gomez-correo.com","asunto":"Consulta","mensaje":"Mensaje de prueba"}'
```

**Resultado esperado:** `HTTP 400`, cuerpo con `"exito": false` y el mensaje `El formato del correo electrónico no es válido.`; no se crea ningún registro nuevo.

**Resultado obtenido:** ✅ Coincide con lo esperado. Tanto la validación del navegador (expresión regular en `app.js`) como la validación del servidor (`@Email` de Jakarta Bean Validation sobre `ContactoRequest.java`) rechazaron el valor. La prueba automatizada `caso3_correoConFormatoInvalido` confirma el código `400` y el mensaje de error correspondiente.

---

## Resumen

| Caso | Descripción                              | Automatizada (`mvn test`) | Manual (navegador) |
|------|--------------------------------------------|:---------------------------:|:---------------------:|
| 1    | Envío correcto del formulario                | ✅ Aprobado                  | ✅ Aprobado            |
| 2    | Campo obligatorio vacío                      | ✅ Aprobado                  | ✅ Aprobado            |
| 3    | Correo electrónico con formato inválido      | ✅ Aprobado                  | ✅ Aprobado            |

> Nota: los tres casos se ejecutaron exclusivamente contra la base de datos `formulario_contacto_test` del ambiente de **pruebas**, sin afectar los datos de desarrollo ni de producción.
