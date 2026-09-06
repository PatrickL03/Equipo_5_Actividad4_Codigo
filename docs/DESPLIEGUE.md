# Despliegue del ambiente de producción / demostración

No se requiere comprar dominio ni alojamiento. La aplicación es un único
JAR de Spring Boot (backend + frontend estático incluidos), por lo que
puede desplegarse en cualquier servicio gratuito compatible con
aplicaciones Java. Se sugieren **Render** o **Railway**, ambos con planes
gratuitos suficientes para esta actividad.

## Paso 0 — Subir el proyecto a GitHub

```bash
git init
git add .
git commit -m "Formulario de contacto La Salle - Actividad 4"
git branch -M main
git remote add origin https://github.com/tu-usuario/formulario-contacto-lasalle.git
git push -u origin main
```

> El `.gitignore` ya excluye `target/`, `logs/`, `.env` y archivos de IDE.
> Verifica antes de hacer push que no exista ningún archivo `.env` con datos reales.

## Opción A — Railway (con plugin de MySQL)

1. Crear una cuenta gratuita en Railway y conectar el repositorio de GitHub.
2. Agregar un servicio de base de datos **MySQL** desde el marketplace de Railway (plan gratuito).
3. Railway genera automáticamente variables como `MYSQLHOST`, `MYSQLPORT`, `MYSQLDATABASE`, `MYSQLUSER`, `MYSQLPASSWORD`. En el servicio de la aplicación, definir las variables que usa el proyecto:
   - `APP_ENV=production`
   - `DB_URL=jdbc:mysql://${{MYSQLHOST}}:${{MYSQLPORT}}/${{MYSQLDATABASE}}?useSSL=false&serverTimezone=UTC`
   - `DB_USERNAME=${{MYSQLUSER}}`
   - `DB_PASSWORD=${{MYSQLPASSWORD}}`
4. Railway detecta el `pom.xml` y compila el proyecto automáticamente (Nixpacks/Java). También inyecta la variable `PORT`, que ya está soportada en `application.properties` (`server.port=${PORT:8080}`).
5. Al finalizar el despliegue, Railway asigna un dominio gratuito `*.up.railway.app`.

## Opción B — Render (Web Service + Base de datos MySQL externa gratuita)

1. Crear una cuenta gratuita en Render y conectar el repositorio de GitHub.
2. Crear un **Web Service** de tipo "Java" apuntando al repositorio; Render ejecuta `mvn clean package` y luego `java -jar target/formulario-contacto.jar` automáticamente (o se puede especificar el comando de build/start manualmente).
3. Como Render no ofrece MySQL gratuito nativo, usar un proveedor externo gratuito de MySQL (por ejemplo, un plan gratuito de Aiven, Clever Cloud o FreeSQLDatabase) y obtener sus credenciales.
4. En el panel de "Environment" del servicio, definir:
   - `APP_ENV=production`
   - `DB_URL=jdbc:mysql://HOST:3306/NOMBRE_BD?useSSL=false&serverTimezone=UTC`
   - `DB_USERNAME=...`
   - `DB_PASSWORD=...`
5. Render asigna un dominio gratuito `*.onrender.com`.

## Opción C — Servicio institucional aprobado por el docente

Si la institución ofrece un servidor de prácticas con soporte para Java y MySQL, se sigue el mismo procedimiento: definir las mismas variables de entorno (`APP_ENV`, `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`) en la configuración del servicio, sin escribirlas en el código fuente.

## Verificación final antes de entregar el enlace

- [ ] La variable `APP_ENV` en el servicio de producción está en `production`.
- [ ] El panel de depuración **no** aparece en la página (verificar que `GET /api/ambiente` devuelva `"debug": false`).
- [ ] El envío correcto del formulario muestra el mensaje de confirmación.
- [ ] Los campos obligatorios y el formato de correo se siguen validando (400 con mensajes claros).
- [ ] Ningún archivo `.env` con datos reales fue subido al repositorio de GitHub (solo `.env.example`).

> **Enlace de la demostración:** completar aquí con la URL final una vez desplegada la aplicación: `https://__________________________`
