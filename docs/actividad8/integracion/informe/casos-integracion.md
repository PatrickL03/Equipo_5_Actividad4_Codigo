
I01:

Objetivo: verificar el registro correcto de un contacto.

Datos: nombre Juan Pérez, correo válido, teléfono 3001234567, asunto y mensaje completos.

Resultado esperado: respuesta HTTP 200 y registro almacenado correctamente.

I02:

Objetivo: verificar que el contador cambie después de registrar un nuevo contacto.

Resultado esperado: HTTP 200 y totalDespues = totalAntes + 1.

El controlador recibe la solicitud HTTP y delega el registro al servicio. El servicio utiliza el repositorio JPA para guardar los datos en H2. El endpoint del contador consulta la cantidad de registros mediante el repositorio y devuelve el total actualizado.


