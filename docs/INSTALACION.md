# Instrucciones de instalación y ejecución

## Requisitos

- JDK 17 o superior.
- Apache Maven 3.9+ (o usar el que trae integrado tu IDE: IntelliJ, Eclipse o VS Code con la extensión "Extension Pack for Java").
- XAMPP (o cualquier servidor MySQL equivalente) para los ambientes de **desarrollo** y **pruebas**.
- Git (para el control de versiones).

## 1. Obtener el proyecto

Descomprimir `Equipo_N_Actividad4_Codigo.zip`, o clonar el repositorio si ya fue subido a GitHub:

```bash
git clone https://github.com/tu-usuario/formulario-contacto-lasalle.git
cd formulario-contacto-lasalle
```

## 2. Preparar MySQL con XAMPP (ambientes de desarrollo y pruebas)

1. Abrir el panel de control de XAMPP e iniciar el módulo **MySQL** (Apache no es necesario, porque el propio Spring Boot sirve la aplicación).
2. No es obligatorio crear las bases de datos manualmente: la cadena de conexión incluye `createDatabaseIfNotExist=true`, por lo que Spring Boot las crea automáticamente al arrancar (el usuario `root` de XAMPP tiene permisos por defecto).
   - Si tu instalación de XAMPP no lo permite, créalas manualmente desde phpMyAdmin:
     - `formulario_contacto_dev`
     - `formulario_contacto_test`
3. Las tablas se crean automáticamente gracias a `spring.jpa.hibernate.ddl-auto=update` (ver `database/schema.sql` como referencia del modelo de datos).

## 3. Configurar el ambiente activo

El ambiente se selecciona con la variable de entorno `APP_ENV` (`development`, `testing` o `production`). Puedes definirla de varias formas:

**Opción A — Variable de entorno del sistema (Linux/Mac):**
```bash
export APP_ENV=development
```

**Opción B — Variable de entorno del sistema (Windows PowerShell):**
```powershell
$env:APP_ENV = "development"
```

**Opción C — Parámetro al ejecutar Maven:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=development
```

**Opción D — Configuración de ejecución del IDE** (recomendado para IntelliJ/Eclipse/VS Code): en la configuración de "Run/Debug" de la clase `ContactoApplication`, agregar la variable de entorno `APP_ENV=development`.

Si no se define nada, la aplicación arranca por defecto en `development`.

## 4. Ejecutar el proyecto

**Con Maven (IntelliJ, Eclipse, VS Code o terminal):**
```bash
mvn spring-boot:run
```

**O generando el JAR ejecutable:**
```bash
mvn clean package
java -jar target/formulario-contacto.jar
```

Luego abrir en el navegador: `http://localhost:8080`

## 5. Ejecutar los casos de prueba automatizados

Con el módulo MySQL de XAMPP encendido (la base `formulario_contacto_test` se crea sola):

```bash
mvn test
```

Esto ejecuta `ContactoControllerTest.java`, que corre y valida automáticamente los 3 casos obligatorios del ambiente de pruebas (ver `tests/casos_prueba.md`).

## 6. Cambiar de ambiente

Basta con cambiar el valor de `APP_ENV` (o del perfil de Spring) y reiniciar la aplicación. Cada ambiente usa su propia base de datos y su propia configuración de logging, por lo que nunca se mezclan los datos entre ambientes.

| Ambiente      | Valor de APP_ENV | Base de datos                    | Depuración |
|---------------|-------------------|-----------------------------------|------------|
| Desarrollo    | `development`      | `formulario_contacto_dev` (MySQL/XAMPP) | Habilitada |
| Pruebas       | `testing`           | `formulario_contacto_test` (MySQL/XAMPP) | Habilitada |
| Producción    | `production`        | Definida por `DB_URL` (variable de entorno del servicio de despliegue) | Deshabilitada |

## 7. Estructura de carpetas

```
Equipo_N_Actividad4_Codigo/
├── pom.xml
├── .env.example
├── .gitignore
├── src/
│   ├── main/
│   │   ├── java/com/lasalle/contacto/
│   │   │   ├── ContactoApplication.java
│   │   │   ├── config/
│   │   │   │   ├── AmbienteInfo.java
│   │   │   │   └── DatosDesarrolloSeeder.java
│   │   │   ├── controller/ContactoController.java
│   │   │   ├── dto/  (ContactoRequest, ContactoResponse, ErrorResponse, AmbienteResponse)
│   │   │   ├── exception/ManejadorErroresGlobal.java
│   │   │   ├── model/Contacto.java
│   │   │   ├── repository/ContactoRepository.java
│   │   │   └── service/ContactoService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-development.properties
│   │       ├── application-testing.properties
│   │       ├── application-production.properties
│   │       └── static/           (frontend: index.html, css/, js/)
│   └── test/java/com/lasalle/contacto/ContactoControllerTest.java
├── database/schema.sql
├── tests/casos_prueba.md
└── docs/ (INSTALACION.md, AMBIENTES.md, DESPLIEGUE.md)
```
