# Formulario de contacto La Salle

Aplicación web con backend en **Java + Spring Boot** y frontend en
**HTML + CSS + JavaScript** (servido por el propio backend, sin necesidad
de un segundo servidor), que implementa un formulario de contacto
(nombre, correo electrónico, asunto y mensaje) con validación de campos
obligatorios y de formato de correo, mensaje de confirmación, y
almacenamiento en una base de datos **MySQL**. El objetivo de la
actividad no es la complejidad del sistema, sino demostrar que la
**misma aplicación** puede ejecutarse de forma controlada en tres
ambientes distintos: **desarrollo**, **pruebas** y **producción/demostración**.

## Tecnologías usadas

- **Backend:** Java 17 + Spring Boot 3 (Spring Web, Spring Data JPA, Bean Validation).
- **Frontend:** HTML, CSS y JavaScript nativo (sin frameworks), consumiendo la API REST del backend.
- **Base de datos:** MySQL — en local mediante **XAMPP** (desarrollo y pruebas), y mediante el servicio de base de datos del proveedor gratuito en producción.
- **Desarrollo:** compatible con IntelliJ IDEA, Eclipse o VS Code (proyecto Maven estándar).
- **Control de versiones:** Git / GitHub.
- **Despliegue:** Render, Railway u otro servicio gratuito aprobado por el docente (ver `docs/DESPLIEGUE.md`).

## Arquitectura

```
Navegador (HTML/CSS/JS)  <-- fetch() -->  API REST (Spring Boot)  <-- JPA/Hibernate -->  MySQL
        static/                          controller / service / repository        (XAMPP en local)
```

El frontend vive dentro de `src/main/resources/static/`, por lo que el
mismo JAR de Spring Boot sirve tanto la página web como la API. No se
necesita desplegar dos servicios por separado.

## Inicio rápido

```bash
export APP_ENV=development     # o definirlo en la config de ejecución del IDE
mvn spring-boot:run
```

Abrir `http://localhost:8080` en el navegador. Instrucciones completas
(incluida la configuración de MySQL con XAMPP): ver [`docs/INSTALACION.md`](docs/INSTALACION.md).

## Documentación

| Documento | Contenido |
|-----------|-----------|
| [`docs/INSTALACION.md`](docs/INSTALACION.md) | Cómo instalar y ejecutar el proyecto (IDE, Maven, XAMPP) |
| [`docs/AMBIENTES.md`](docs/AMBIENTES.md) | Detalle de la configuración de los 3 ambientes |
| [`docs/DESPLIEGUE.md`](docs/DESPLIEGUE.md) | Cómo desplegar el ambiente de producción/demostración en Render/Railway |
| [`tests/casos_prueba.md`](tests/casos_prueba.md) | Casos de prueba ejecutados y documentados (manuales y automatizados) |

## Requisitos funcionales cubiertos

- [x] Página principal (`index.html`).
- [x] Formulario con nombre, correo electrónico, asunto y mensaje.
- [x] Validación de campos obligatorios (cliente en `app.js` y servidor en `ContactoRequest.java`).
- [x] Validación del formato del correo electrónico (cliente y servidor).
- [x] Mensaje de confirmación al procesar correctamente el formulario.
- [x] Almacenamiento de la información en base de datos MySQL.

## API REST

| Método | Endpoint         | Descripción                                             |
|--------|------------------|----------------------------------------------------------|
| POST   | `/api/contacto`  | Recibe y valida el formulario; guarda el registro en MySQL |
| GET    | `/api/ambiente`  | Informa el ambiente activo (para la insignia y el panel de depuración) |

## Reglas técnicas

- No se incluyen contraseñas, tokens ni datos sensibles reales en el código (la contraseña vacía de `root` en desarrollo es el valor local por defecto de XAMPP, no un dato sensible real).
- Se incluye `.env.example` documentando las variables de entorno necesarias en producción, sin valores secretos.
- El `.gitignore` excluye `target/`, `logs/`, `.env` y archivos de configuración de IDE.
- El proyecto se ejecuta siguiendo las instrucciones de `docs/INSTALACION.md`, sin subir dependencias descargables (Maven las resuelve desde su repositorio local/remoto, no se versionan en el zip de entrega).

## Equipo

_Completar con los integrantes del equipo y el número de equipo (Equipo_N)._
