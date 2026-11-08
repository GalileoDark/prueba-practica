# Prueba Técnica Full-Stack: Spring Boot & Angular (RealDooh)

Este repositorio contiene la solución completa para la prueba técnica de desarrollo full-stack, compuesta por un **backend en Spring Boot** y un **frontend en Angular**.

---

## 🛠️ Tecnologías y Stack Tecnológico Utilizado

### **Backend (Spring Boot)**
- **Java**: Versión **21**
- **Framework**: Spring Boot (v4.1.2)
- **API REST**: `spring-boot-starter-web`
- **Persistencia & Base de Datos**: `spring-boot-starter-data-jpa` + **H2 Database** (en memoria)
- **Seguridad & JWT**: **JJWT (v0.12.5)** (`jjwt-api`, `jjwt-impl`, `jjwt-jackson`) para la generación y validación de tokens de forma stateless.
- **Mapeo de Objetos**: **MapStruct (v1.5.5.Final)** integrado con Lombok mediante procesadores de anotaciones en el compilador de Maven (`lombok-mapstruct-binding`).
- **Utilidades de Código**: **Lombok** para la reducción de código repetitivo (*boilerplate*).

### **Frontend (Angular)**
- **Framework**: Angular **16.2.16**
- **UI Components**: Angular Material **16.2.14**
- **Internacionalización**: `@ngx-translate/core`
- **Estilos**: SCSS

---

## 📁 Estructura del Proyecto y Arquitectura

El proyecto sigue una arquitectura **Hexagonal (Puertos y Adaptadores)** en el Backend para garantizar el desacoplamiento de la lógica de negocio, y una arquitectura modular basada en **ITCSS (Inverted Triangle CSS)** en el Frontend para la gestión de estilos.

---

### ☕ Backend (Spring Boot - Arquitectura Hexagonal)

La estructura del código fuente (`src/main/java/com/econocom/backend`) se organiza por **dominios funcionales** (`login`, `user`, `shared`):

```text
backend/src/main/java/com/econocom/backend/
│   BackendApplication.java                     # Clase principal de arranque de Spring Boot
│   
├───config
│       CorsConfig.java                         # Configuración global de CORS para comunicación con Angular
│       
├───login                                       # Módulo Funcional: Autenticación / Login
│   ├───application                             # Capa de Aplicación (Casos de Uso)
│   │   └───usecase
│   │           LoginUseCaseImpl.java           # Orquestación de la lógica de negocio del login
│   │           
│   ├───domain                                  # Capa de Dominio (Reglas de negocio puras, sin dependencias)
│   │   │   InvalidCredentialsException.java    # Excepción de dominio para credenciales incorrectas
│   │   │   
│   │   ├───model                               # Objetos de dominio (Modelos core)
│   │   │       AuthResponse.java               # Modelo con la respuesta del JWT
│   │   │       LoginCredentials.java           # Modelo con las credenciales ingresadas
│   │   │       
│   │   └───port                                # Puertos (Contratos / Interfaces)
│   │       ├───in                              # Puertos de Entrada (Lógica expuesta hacia la aplicación)
│   │       │       LoginUseCase.java           
│   │       └───out                             # Puertos de Salida (Contratos para servicios externos)
│   │               TokenPort.java              
│   │               
│   └───infrastructure                          # Capa de Infraestructura (Detalles técnicos y conectores)
│       ├───in                                  # Adaptadores de Entrada (Puntos de contacto externos)
│       │   └───rest
│       │       │   AuthController.java         # Endpoint REST (/api/auth/login)
│       │       └───dto
│       │               AuthResponseOutputDto.java   # DTO de salida enviado al cliente HTTP
│       │               LoginCredentialsInputDto.java # DTO de entrada recibido del cliente HTTP
│       │               
│       └───out                                 # Adaptadores de Salida (Implementaciones concretas)
│           ├───persistence
│           │   ├───entity
│           │   └───mapper                      # Mappers de MapStruct entre DTOs y Objetos de Dominio
│           │           AuthResponseMapper.java
│           │           LoginCredentialsMapper.java
│           │           
│           └───security                        # Adaptador de Seguridad / Token
│                   JwtTokenAdapter.java        # Implementación concreta de TokenPort mediante JJWT
│                   
├───shared                                      # Componentes Compartidos (Cross-cutting Concerns)
│   └───infrastructure
│       └───rest
│           └───exception                       # Manejo Global de Errores HTTP
│                   ErrorResponseDto.java       # DTO estándar de respuesta de error
│                   ErrorResponseFactory.java   # Factoría estática para construir respuestas de error
│                   GlobalExceptionHandler.java # Interceptor global de excepciones (@ControllerAdvice)
│                   
└───user                                        # Módulo Funcional: Gestión de Usuarios
    ├───application
    │   └───usecase
    │           FindUserByEmailUseCaseImpl.java # Caso de uso para recuperar usuario por email
    │           
    ├───domain
    │   ├───exceptions
    │   │       UserNotFoundException.java      # Excepción de dominio cuando el usuario no existe
    │   │       
    │   ├───model
    │   │       User.java                       # Entidad de dominio pura de Usuario
    │   │       
    │   └───port
    │       ├───in
    │       │       FindUserByEmailUseCase.java
    │       └───out
    │               UserRepositoryPort.java     # Contrato para el acceso a datos del usuario
    │               
    └───infrastructure
        ├───in
        └───out
            └───persistence                     # Adaptador de Persistencia JPA
                ├───adapter
                │       UserPersistenceAdapter.java # Implementación de UserRepositoryPort
                ├───entity
                │       UserJpa.java            # Entidad JPA para mapeo en tabla H2
                ├───mapper
                │       UserMapper.java         # MapStruct para convertir UserJpa <-> User
                └───repository
                        UserJpaRepository.java  # Repositorio Spring Data JPA
```

🅰️ Frontend (Angular 16 + Estructura SCSS ITCSS)

La estructura del código en el cliente (src/) organiza los componentes, la internacionalización y un diseño de hojas de estilo modular:

```text
frontend/src/
│   favicon.ico
│   index.html                                  # HTML base de la SPA
│   main.ts                                     # Punto de entrada de bootstrapping de Angular
│   styles.scss                                 # Importador global de hojas de estilo
│   
├───app                                         # Módulo principal de la aplicación Angular
│   │   app-routing.module.ts                   # Definición y mapeo de rutas de la aplicación
│   │   app.component.html                      # Layout raíz
│   │   app.component.scss
│   │   app.component.spec.ts
│   │   app.component.ts
│   │   app.module.ts                           # Declaración de componentes, módulos e ngx-translate
│   │   
│   ├───components                              # Componentes de interfaz de usuario
│   │   └───login                               # Componente de inicio de sesión
│   │           login.component.html            # Plantilla HTML con formulario reactivo y banners de mensaje
│   │           login.component.scss            # Estilos aislados del componente de login
│   │           login.component.spec.ts
│   │           login.component.ts              # Lógica del formulario, consumo de errores e i18n
│   │           
│   └───services                                # Servicios de Angular
│           auth.service.ts                     # Servicio HTTP (`HttpClient`) para consumir `/api/auth/login`
│           
├───assets                                      # Recursos estáticos de la aplicación
│   ├───fonts                                   # Tipografías locales del proyecto (Lato)
│   │   └───Lato
│   │           Lato-Black.ttf
│   │           Lato-Bold.ttf
│   │           Lato-Regular.ttf
│   │           
│   ├───i18n                                    # Diccionarios JSON de internacionalización (ngx-translate)
│   │       en.json                             # Inglés
│   │       es.json                             # Español
│   │       fr.json                             # Francés
│   │       pt.json                             # Portugués
│   │       
│   ├───images                                  # Ilustraciones e isotipos del diseño
│   │   │   login.svg
│   │   └───logos
│   │           Econocom.svg
│   │           Logo-RD.svg
│   │           
│   └───scss                                    # Arquitectura de estilos ITCSS (Inverted Triangle CSS)
│       │   custom-material-theme.scss          # Tema personalizado para Angular Material
│       │   _material-overrides.scss            # Sobrescritura global de estilos de Angular Material
│       │   
│       ├───1-settings                          # Variables globales (colores, fuentes, espaciados)
│       │       _variables.scss
│       │       
│       ├───2-design-tokens                     # Tokens de diseño tipográficos
│       │       _typography.scss
│       │       
│       ├───3-tools                             # Mixins y funciones SCSS reutilizables
│       │       _aspect-ratio.scss
│       │       _breakpoints.scss               # Mixins para diseño responsive
│       │       _ellipsis.scss
│       │       _flexbox.scss
│       │       _fluid-type.scss
│       │       _font-face.scss                 # Carga de fuentes locales
│       │       _notouch-hover.scss
│       │       _nth-child.scss
│       │       _transition.scss
│       │       
│       ├───4-generic                           # Reseteos de estilos base y normalización del navegador
│       │       _normalize.scss
│       │       _reset.scss
│       │       
│       ├───5-elements                          # Estilos para etiquetas HTML puras sin clases (botones, inputs)
│       │       _buttons.scss
│       │       
│       ├───6-skeleton                          # Estructuras de layout y grid global
│       │       _layouts.scss
│       │       
│       ├───7-components                        # Estilos para componentes genéricos (formularios, inputs)
│       │       _forms.scss
│       │       
│       └───8-utilities                         # Clases utilitarias y modificadores de estado (BEM)
│               _modifiers.scss
│               _states.scss
│               
└───environments                                # Configuración de entornos de la aplicación
        environment.development.ts              # Entorno local / desarrollo
        environment.ts                          # Entorno base con la URL del Backend (`apiUrl`)
```

## 🔌 Conexión Frontend ⇄ Backend

Para lograr la comunicación entre la aplicación cliente y la API REST, se configuró lo siguiente en cada extremo:

- **En el Backend (Spring Boot):**
    - **CORS (`CorsConfig.java`):** Se habilitaron los permisos necesarios para permitir peticiones HTTP desde el servidor de desarrollo de Angular (`http://localhost:4200`).
    - **Endpoints REST (`/api`):** Se expusieron las rutas de autenticación para procesar peticiones y retornar respuestas JSON estandarizadas (un token JWT en caso de éxito o un `ErrorResponseDto` controlado mediante el `GlobalExceptionHandler` en caso de fallo).

- **En el Frontend (Angular):**
    - **Variables de Entorno (`environment.ts`):** Se centralizó la URL base del servidor apuntando a `http://localhost:8080/api` para evitar URLs quemadas en el código.
    - **Servicio HTTP (`AuthService`):** Se implementó la lógica con `HttpClient` para enviar las credenciales del formulario por método `POST`.
    - **Mestión de Respuestas:** Se programó el almacenamiento local del token JWT en el `LocalStorage` tras un éxito (`200 OK`), y la captura dinámica del mensaje de error del backend para mostrarlo en el banner de la interfaz si las credenciales fallan (`401` / `404`).

## 🚀 Guía de Inicio Rápido (Despliegue Local)

Si has descargado o clonado este repositorio y deseas poner en marcha la aplicación completa en tu entorno local, sigue los pasos a continuación.

## 📋 1. Requisitos Previos
Asegúrate de tener instalado en tu sistema:
- **Java Development Kit (JDK):** Versión **21**
- **Maven:** Para la gestión de dependencias del backend.
- **Node.js y npm:** (Se recomienda Node.js v16 o v18).
- **Angular CLI:** Versión **16.2.16** (`npm install -g @angular/cli@16.2.16`).

---

## ⚙️ 2. Clonar el Repositorio
- Abre tu terminal y clona el proyecto:
```bash
git clone https://github.com/GalileoDark/prueba-practica.git
cd prueba-practica
```

## ☕ 3. Arrancar el Backend (Spring Boot)

Navega al directorio del backend:
```bash
cd backend
```
 

Compila el proyecto y arranca el servidor utilizando Maven:

```bash
mvn clean spring-boot:run
```

(O si prefieres usar el empaquetado jar: mvn clean package y luego java -jar target/backend-0.0.1-SNAPSHOT.jar)

- El servidor backend arrancará por defecto en el puerto http://localhost:8080.

  - (Opcional) Puedes comprobar la base de datos en memoria H2 accediendo a http://localhost:8080/h2-console.

🅰️ 4. Arrancar el Frontend (Angular)

Abre una nueva pestaña o ventana de la terminal y navega al directorio del frontend:
```bash
cd frontend
```

Instala las dependencias del proyecto:
```bash
npm install
```

Inicia el servidor de desarrollo de Angular:
```bash
ng serve
```

Abre tu navegador web y accede a http://localhost:4200. La aplicación se conectará automáticamente con el backend en el puerto 8080.

## 🔑 5. Credenciales de Prueba

Para probar el inicio de sesión en la interfaz:

Usuario / Correo: admin@econocom.com

Contraseña: 123456