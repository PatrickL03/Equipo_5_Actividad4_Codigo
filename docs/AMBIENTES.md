# Configuración de los tres ambientes

El mismo artefacto (`formulario-contacto.jar`) y el mismo código fuente se
ejecutan sin cambios en los tres ambientes. Lo único que cambia es el
perfil de Spring activo, seleccionado mediante la variable de entorno
`APP_ENV`, que determina qué archivo `application-<perfil>.properties`
se carga.

## 1. Ambiente de desarrollo (`development`)

- Ejecución local, típicamente desde el IDE (IntelliJ / Eclipse / VS Code) o con `mvn spring-boot:run`.
- Base de datos MySQL propia servida por **XAMPP**: `formulario_contacto_dev`.
- `app.debug=true`: el endpoint `GET /api/ambiente` devuelve `debug: true` y el total de contactos almacenados; el frontend muestra el panel de depuración.
- Registro (logging) detallado: `logging.level.com.lasalle.contacto=DEBUG`, incluyendo las consultas SQL generadas por Hibernate (`spring.jpa.show-sql=true`), guardado en `logs/app-development.log`.
- Datos identificables: al arrancar por primera vez, `DatosDesarrolloSeeder` inserta automáticamente dos registros de ejemplo con el prefijo `[DEV]` y correos `@ejemplo-lasalle.local`, para que nunca se confundan con datos reales.

## 2. Ambiente de pruebas (`testing`)

- Configuración completamente separada de desarrollo: archivo `application-testing.properties` propio.
- Base de datos MySQL independiente: `formulario_contacto_test` (nunca se mezcla con desarrollo ni producción).
- Registro guardado en `logs/app-testing.log`.
- En este ambiente se ejecutaron y documentaron los 3 casos de prueba obligatorios (ver `tests/casos_prueba.md`), tanto de forma manual (desde el navegador) como automatizada (`mvn test`, clase `ContactoControllerTest.java`):
  1. Envío correcto del formulario.
  2. Intento de envío con un campo obligatorio vacío.
  3. Intento de envío con un correo electrónico con formato inválido.

## 3. Ambiente de producción / demostración (`production`)

- `app.debug=false`: el panel de depuración nunca aparece y el endpoint `/api/ambiente` no expone el total de contactos.
- `logging.level.root=WARN`: sin registro detallado de depuración.
- Base de datos definida por la variable de entorno `DB_URL` (y `DB_USERNAME` / `DB_PASSWORD`), configurada directamente en el panel del servicio de despliegue — nunca en el código fuente.
- Pensado para desplegarse en un servicio gratuito (Render, Railway u otro aprobado por el docente); ver `docs/DESPLIEGUE.md`.

## Resumen comparativo

| Aspecto                     | Desarrollo                  | Pruebas                       | Producción                          |
|------------------------------|-------------------------------|---------------------------------|----------------------------------------|
| Ejecución                   | Local (IDE / `mvn spring-boot:run`) | Local (aislada de dev) / `mvn test` | Servicio gratuito desplegado (Render/Railway) |
| Panel de depuración          | Visible                       | Visible                          | Oculto                                 |
| Registro (log) detallado     | Sí (incluye SQL)               | Sí                                | No                                      |
| Base de datos                | MySQL `formulario_contacto_dev` (XAMPP) | MySQL `formulario_contacto_test` (XAMPP) | MySQL provista por el servicio de despliegue |
| Credenciales                 | `root` sin contraseña (valor local por defecto de XAMPP) | `root` sin contraseña (valor local por defecto de XAMPP) | Variables de entorno `DB_URL`/`DB_USERNAME`/`DB_PASSWORD` |
| Datos                        | Identificables (`[DEV]`)      | Datos de los 3 casos de prueba  | Datos reales de demostración            |
