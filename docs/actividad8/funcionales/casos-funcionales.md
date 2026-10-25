# Actividad 8: Pruebas funcionales

**Responsable:** Sebastián  
**Proyecto:** Formulario de contacto La Salle - Equipo 5  
**Ambiente:** testing  
**Fecha de ejecución:** 24 de octubre de 2026

## F01 — Envío exitoso del formulario

**Objetivo:** Comprobar que el usuario puede completar y enviar correctamente el formulario de contacto.

**Requisito relacionado:** El sistema debe permitir registrar un contacto cuando los datos ingresados son válidos.

**Precondiciones:**
- La aplicación Spring Boot está ejecutándose.
- El formulario está disponible en `http://localhost:8080`.
- El ambiente activo es `testing`.

**Datos de entrada:**
- Nombre: Juan Pérez
- Correo: juan.prueba@example.com
- Teléfono: 3001234567
- Asunto: Consulta de admisiones
- Mensaje: Buenos días, quisiera conocer las fechas de inscripción disponibles.

**Pasos realizados:**
1. Abrir el formulario de contacto en el navegador.
2. Completar los campos con los datos de prueba.
3. Pulsar el botón «Enviar mensaje».
4. Observar el mensaje de respuesta y el contador de contactos.

**Resultado esperado:** El sistema acepta los datos válidos, registra el contacto y muestra una confirmación de envío exitoso.

**Resultado obtenido:** El formulario permitió ingresar los datos válidos, procesó el envío y mostró al usuario el mensaje: «¡Tu mensaje se envió correctamente! Gracias por escribirnos, pronto nos pondremos en contacto contigo». El contador de contactos aumentó de cero a uno.

**Estado:** APROBADO.

**Evidencias:**
- `evidencias/F00_formulario_inicial.png`
- `evidencias/F01_datos_validos.png`
- `evidencias/F01_confirmacion.png`

## F02 — Validación de correo electrónico inválido

**Objetivo:** Comprobar que el formulario detecta un correo electrónico inválido y evita un envío incorrecto.

**Requisito relacionado:** El sistema debe validar el formato del correo electrónico antes de aceptar el formulario.

**Precondiciones:**
- La aplicación Spring Boot está ejecutándose.
- El formulario está disponible en el navegador.
- El ambiente activo es `testing`.

**Datos de entrada:** Se completaron los campos obligatorios y se utilizó `juan-correo.com` como correo electrónico, sin el símbolo `@`.

**Pasos realizados:**
1. Abrir el formulario de contacto.
2. Completar los campos obligatorios.
3. Introducir `juan-correo.com` en el campo de correo electrónico.
4. Intentar enviar el formulario.
5. Observar el mensaje de validación y el contador de contactos.

**Resultado esperado:** El sistema identifica el correo electrónico inválido, muestra un mensaje de validación y bloquea el envío.

**Resultado obtenido:** El campo de correo electrónico apareció resaltado en rojo y mostró el mensaje «Ingresa un correo electrónico válido». Al intentar enviar el formulario, no apareció una confirmación de éxito y el contador permaneció en un contacto.

**Estado:** APROBADO.

**Evidencias:**
- `evidencias/F02_correo_invalido.png`
- `evidencias/F02_mensaje_validacion.png`

## Conclusión

La prueba F01 confirmó que el formulario permite enviar un contacto con datos válidos y muestra una confirmación de éxito. La prueba F02 confirmó que el formulario identifica un correo electrónico con formato incorrecto, muestra el mensaje de validación y evita que se complete el envío. El contador permaneció en un contacto después del intento de envío con el correo inválido.

Las pruebas funcionales se realizaron manualmente desde el navegador en el ambiente de pruebas. Las nueve pruebas automatizadas ejecutadas con Maven finalizaron con `BUILD SUCCESS`, sin fallos ni errores.

## Referencia de evidencias

Las capturas de pantalla de los casos funcionales se encuentran en el directorio `docs/actividad8/funcionales/evidencias/` del repositorio.