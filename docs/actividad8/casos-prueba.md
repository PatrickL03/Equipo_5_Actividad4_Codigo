
# Actividad 8 - Estrategia de pruebas de software

## Proyecto
Formulario de contacto La Salle - Equipo 5

## Objetivo
Diseñar, ejecutar y documentar seis casos de prueba correspondientes a tres niveles complementarios: unitarias, integración y funcionales.

## Estrategia
- Unitarias: verifican reglas de validación de manera aislada.
- Integración: verifican la comunicación entre controlador, servicio y persistencia.
- Funcionales: verifican el comportamiento observable desde el navegador.

## Matriz de casos

| ID | Tipo | Nombre | Responsable | Estado |
|---|---|---|---|---|
| U01 | Unitaria | Teléfono con formato válido | David | Aprobado |
| U02 | Unitaria | Teléfono con formato inválido | David | Aprobado |
| I01 | Integración | Registro de contacto | Patrick | Pendiente de ejecución individual |
| I02 | Integración | Actualización del contador | Patrick | Pendiente de ejecución individual |
| F01 | Funcional | Envío exitoso del formulario | Sebastián | Pendiente |
| F02 | Funcional | Validación de correo inválido | Sebastián | Pendiente |

## Resultados unitarios confirmados

Comando: `mvn clean test`

Resultado: 9 pruebas ejecutadas, 0 fallos, 0 errores y BUILD SUCCESS.

Las pruebas unitarias U01 y U02 aprobaron. Las otras siete corresponden a pruebas existentes del proyecto.

## Evidencias pendientes

Agregar enlaces a las capturas y documentos aportados por Patrick y Sebastián después de sus ejecuciones reales.


## U01 - Validación de teléfono correcto

**Tipo:** Prueba unitaria  
**Responsable:** David Padilla Correa

**Objetivo:** Verificar que el DTO ContactoRequest acepte un número telefónico con formato válido.

**Requisito relacionado:** El campo teléfono es opcional y, cuando se proporciona, debe respetar el formato establecido.

**Precondiciones:** Java 17, Maven, JUnit 5 y Jakarta Bean Validation disponibles. Clase ContactoRequestValidationTest implementada.

**Datos de entrada:** Teléfono 3001234567. Los demás campos obligatorios contienen valores válidos.

**Pasos de ejecución:**
1. Crear una instancia válida de ContactoRequest.
2. Asignar el número 3001234567.
3. Ejecutar la validación mediante Validator.
4. Verificar que no existan errores de validación.

**Resultado esperado:** No se generan errores de validación.

**Resultado obtenido:** La prueba U01 finalizó satisfactoriamente.

**Estado:** APROBADO.

**Evidencia:** ContactoRequestValidationTest.java y registro Maven con 2 pruebas unitarias exitosas.

## U02 - Rechazo de teléfono inválido

**Tipo:** Prueba unitaria  
**Responsable:** David Padilla Correa

**Objetivo:** Verificar que el DTO ContactoRequest detecte un teléfono con caracteres no permitidos.

**Requisito relacionado:** El sistema debe rechazar números telefónicos que incumplan el patrón definido.

**Precondiciones:** Entorno de pruebas unitarias configurado y DTO ContactoRequest disponible.

**Datos de entrada:** Teléfono abc123. Los demás campos obligatorios contienen valores válidos.

**Pasos de ejecución:**
1. Crear una instancia válida de ContactoRequest.
2. Asignar el valor abc123 al teléfono.
3. Ejecutar Validator.validate().
4. Comprobar que exista un error asociado al campo telefono.
5. Verificar que el mensaje contenga la palabra formato.

**Resultado esperado:** Se identifica un error de formato del teléfono.

**Resultado obtenido:** La prueba U02 detectó correctamente el valor inválido y finalizó satisfactoriamente.

**Estado:** APROBADO.

**Evidencia:** ContactoRequestValidationTest.java y ejecución Maven.

## Resultado de la automatización

Comando ejecutado:

```powershell
mvn clean test
```

Resultado observado:

- Pruebas totales: 9.
- Pruebas unitarias nuevas: 2.
- Pruebas anteriores: 7.
- Fallos: 0.
- Errores: 0.
- BUILD SUCCESS.

Las pruebas U01 y U02 están integradas en master mediante el Pull Request #9.

Repositorio: https://github.com/PatrickL03/Equipo_5_Actividad4_Codigo

## I01 - Registro correcto de contacto

**Tipo:** Integración  
**Responsable:** Patrick López

**Objetivo:** Verificar la recepción y procesamiento de un contacto válido mediante el endpoint POST /api/contacto.

**Componente:** Controlador, servicio y repositorio JPA.

**Precondiciones:** Aplicación de pruebas configurada con H2 y perfil testing activo.

**Datos:** Nombre válido, correo válido, teléfono, asunto y mensaje completos.

**Pasos:**
1. Preparar una solicitud válida.
2. Enviarla al endpoint POST /api/contacto.
3. Comprobar HTTP 200 y respuesta de éxito.
4. Revisar la evidencia disponible de persistencia.

**Resultado esperado:** Solicitud procesada y contacto registrado correctamente.

**Resultado obtenido:** Pendiente de ejecución individual de Patrick.

**Estado:** PENDIENTE.

**Evidencia:** Pendiente.

## I02 - Actualización del contador de contactos

**Tipo:** Integración  
**Responsable:** Patrick López

**Objetivo:** Verificar que el contador aumente cuando se registra un nuevo contacto.

**Componente:** GET /api/contactos/total, servicio y repositorio.

**Precondiciones:** Perfil testing y H2 disponibles.

**Datos:** Solicitud válida de contacto.

**Pasos:**
1. Consultar el total inicial.
2. Registrar un contacto válido.
3. Consultar nuevamente el total.
4. Comparar ambos valores.

**Resultado esperado:** totalDespues = totalAntes + 1.

**Resultado obtenido:** Pendiente de ejecución individual de Patrick.

**Estado:** PENDIENTE.

**Evidencia:** Pendiente.

## F01 - Envío exitoso del formulario

**Tipo:** Funcional  
**Responsable:** Sebastián López

**Objetivo:** Verificar que una persona pueda completar y enviar el formulario correctamente desde el navegador.

**Componente:** Interfaz web del formulario.

**Precondiciones:** Aplicación ejecutándose y accesible desde el navegador.

**Datos:** Nombre, correo válido, teléfono, asunto y mensaje.

**Pasos:**
1. Abrir la aplicación.
2. Completar los campos.
3. Presionar Enviar.
4. Comprobar la confirmación visible.

**Resultado esperado:** El sistema informa que el mensaje fue enviado correctamente.

**Resultado obtenido:** Pendiente de ejecución de Sebastián.

**Estado:** PENDIENTE.

**Evidencia:** Pendiente.

## F02 - Validación de correo inválido

**Tipo:** Funcional  
**Responsable:** Sebastián López

**Objetivo:** Verificar que el formulario detecte un correo electrónico con formato inválido.

**Componente:** Validaciones del formulario web.

**Precondiciones:** Aplicación abierta en el navegador.

**Datos:** Correo juan.prueba-correo.com y demás campos válidos.

**Pasos:**
1. Abrir el formulario.
2. Completar los campos obligatorios.
3. Introducir el correo inválido.
4. Presionar Enviar.
5. Observar el resultado.

**Resultado esperado:** El sistema rechaza el correo inválido y presenta una indicación de validación.

**Resultado obtenido:** Pendiente de ejecución de Sebastián.

**Estado:** PENDIENTE.

**Evidencia:** Pendiente.

