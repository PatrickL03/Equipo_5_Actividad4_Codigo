# Actividad 8: Pruebas funcionales

**Responsable:** Sebastián  
**Proyecto:** Formulario de contacto La Salle - Equipo 5  
**Ambiente:** testing  
**Fecha de ejecución:** 24 de octubre de 2026

## F01 — Envío exitoso del formulario

- **Objetivo:** comprobar que el usuario puede completar y enviar correctamente el formulario de contacto.
- **Requisito relacionado:** el sistema debe permitir registrar un contacto cuando los datos ingresados son válidos.
- **Precondiciones:**
  - La aplicación Spring Boot está ejecutándose.
  - El formulario está disponible en `http://localhost:8080`.
  - El ambiente activo es `testing`.
- **Datos de entrada:**
  - Nombre: Juan Pérez
  - Correo: juan.prueba@example.com
  - Teléfono: 3001234567
  - Asunto: Consulta de admisiones
  - Mensaje: Buenos días, quisiera conocer las fechas de inscripción disponibles.
- **Pasos realizados:**
  1. Abrir el formulario de contacto en el navegador.
  2. Completar los campos con los datos de prueba.
  3. Pulsar el botón «Enviar mensaje».
  4. Observar el mensaje de respuesta y el contador de contactos.
- **Resultado esperado:** el sistema acepta los datos válidos, registra el contacto y muestra una confirmación de envío exitoso.
- **Resultado obtenido:** apareció el mensaje «¡Tu mensaje se envió correctamente! Gracias por escribirnos, pronto nos pondremos en contacto contigo». El contador aumentó de cero a un contacto.
- **Estado:** APROBADO.
- **Evidencias:**
  - `evidencias/F00_formulario_inicial.png`
  - `evidencias/F01_datos_validos.png`
  - `evidencias/F01_confirmacion.png`

## F02 — Validación de correo incorrecto

- **Objetivo:** comprobar que el formulario detecta un correo electrónico inválido y evita un envío incorrecto.
- **Requisito relacionado:** el sistema debe validar el formato del correo electrónico antes de aceptar el formulario.
- **Precondiciones:**
  - La aplicación Spring Boot está ejecutándose.
  - El formulario está disponible en el navegador.
  - El ambiente activo es `testing`.
- **Datos de entrada:** se completaron los campos obligatorios y se utilizó `juan-correo.com` como correo electrónico, sin el símbolo `@`.
- **Pasos realizados:**
  1. Abrir el formulario de contacto.
  2. Completar los campos obligatorios.
  3. Introducir `juan-correo.com` en el campo de correo.
  4. Intentar enviar el formulario.
  5. Observar el mensaje de validación y el contador de contactos.
- **Resultado esperado:** el sistema identifica el correo inválido y bloquea el envío.
- **Resultado obtenido:** el campo de correo apareció resaltado en rojo y mostró el mensaje «Ingresa un correo electrónico válido». Al intentar enviar, no se mostró una confirmación de envío y el contador permaneció en un contacto.
- **Validación observada:** se mostró un mensaje de validación junto al campo de correo en el navegador.
- **Estado:** APROBADO.
- **Evidencias:**
  - `evidencias/F02_correo_invalido.png`
  - `evidencias/F02_mensaje_validacion.png`

## Conclusión

La prueba F01 confirmó el envío exitoso de un contacto con datos válidos. La prueba F02 confirmó que el formulario identifica el formato incorrecto del correo y evita que se confirme un segundo envío. El contador permaneció en un contacto después de intentar enviar el formulario con el correo inválido.

Las pruebas funcionales se realizaron manualmente desde el navegador en el ambiente de pruebas. Las nueve pruebas automatizadas ejecutadas con Maven finalizaron con `BUILD SUCCESS`, sin fallos ni errores.