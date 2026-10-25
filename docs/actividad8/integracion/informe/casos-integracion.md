# Casos de pruebas de integración

## I01 — Registro correcto del contacto

**Resultado obtenido:** La solicitud de contacto fue procesada correctamente mediante el método de prueba `caso1_envioCorrectoDelFormulario`. Se obtuvo HTTP 200, la respuesta cumplió las validaciones de éxito y los logs confirmaron que el contacto fue almacenado correctamente.

**Estado:** APROBADO.

**Evidencias:**
- Código de la prueba: `evidencias/Caso1.jpg`
- Resultado de ejecución: `evidencias/TestI01.jpg`

## I02 — Actualización del contador de contactos

**Resultado obtenido:** La prueba `caso6_totalContactosSeActualiza` consultó el contador antes y después de registrar un contacto. El total inicial fue 0 y el total final fue 1. La aserción `totalDespues == totalAntes + 1` se cumplió.

**Estado:** APROBADO.

**Evidencias:**
- Código de la prueba: `evidencias/Caso6.jpg`
- Resultado de ejecución: `evidencias/TestI02.jpg`

## Resultado general

Se ejecutaron 2 pruebas de integración, con 0 fallos y 0 errores. La ejecución finalizó con `BUILD SUCCESS`.

**Evidencia general:** `evidencias/ResultadoTest.jpg`