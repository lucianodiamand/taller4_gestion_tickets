# Sistema de Gestión de Tickets — Contexto del proyecto

## Qué es esto
Trabajo final de Programación 4 (Taller 4). Defensa oral: mesa de septiembre,
semana del 28/09. Equipo de 3 integrantes, cada uno con disponibilidad horaria
distinta — el trabajo avanza en paralelo, no todos a la vez.

## Stack
- Backend: Spring Boot + JPA/Hibernate + PostgreSQL
- Frontend: Angular (NO EMPEZAR hasta que el backend esté completo y testeado en Postman)
- Seguridad: JWT + autorización por roles

## Principio no negociable
Construir y testear el backend por completo ANTES de tocar Angular.
Nunca debuggear las dos capas al mismo tiempo.

## Modelo de datos (ya definido y aprobado por el docente — NO REDISEÑAR sin confirmar con Lucas)

### Entidades (3)
- Usuario
- Ticket
- Comentario

### Enums (no son tablas separadas — decisión de diseño deliberada)
- Estado
- Prioridad
- Categoria
- Rol (valores: ADMIN, USUARIO)

### Relaciones
- Ticket tiene DOS FKs a Usuario: `creado_por` y `asignado_a` (nullable)
  → refleja que quien crea un ticket no siempre es quien lo resuelve
- Comentario tiene FK a Ticket y FK a Usuario

## Base de datos
- Motor: PostgreSQL
- Nombre de la base local: `tickets_db`
- [Completar: puerto si no es el 5432 default, usuario de conexión]

## Estructura de paquetes del backend
Arquitectura en capas, un paquete por responsabilidad:

com.proyecto.ticket
├── controller     → expone endpoints REST, no contiene lógica de negocio
├── service        → lógica de negocio
├── repository     → interfaces JPA (extends JpaRepository)
├── entity         → clases @Entity, mapeo a tablas
├── dto            → objetos de transferencia (Request/Response), nunca exponer entity directamente
├── exception      → excepciones custom + GlobalExceptionHandler
└── config         → configuración de seguridad, JWT, CORS, etc.

## Convenciones de nombres
| Elemento | Convención | Ejemplo |
|---|---|---|
| Clases Java | PascalCase | `Usuario`, `TicketService` |
| Métodos y variables | camelCase | `crearTicket()`, `usuarioActual` |
| Columnas de tabla | snake_case | `creado_por`, `fecha_creacion` |
| Paquetes | minúsculas, sin guiones | `com.proyecto.ticket.service` |
| DTOs | sufijo según uso | `TicketRequestDTO`, `TicketResponseDTO` |
| Endpoints REST | plural, minúsculas | `/api/tickets`, `/api/tickets/{id}/comentarios` |
| Branches Git | tipo/descripción corta | `feature/entidad-usuario`, `fix/validacion-email` |
| Commits | Conventional Commits | `feat: agregar entidad Usuario`, `fix: corregir validación de rol` |

## Manejo de errores
- Excepciones custom por caso de negocio (ej. `TicketNoEncontradoException`)
- Un único `GlobalExceptionHandler` con `@RestControllerAdvice` centraliza las respuestas de error
- Nunca devolver stack traces crudos al frontend

## DTOs — regla fija
Los controllers NUNCA reciben ni devuelven entidades JPA directamente.
Siempre a través de un DTO. Esto evita exponer campos internos y desacopla
la API del modelo de base de datos.

## Reglas de trabajo con Claude Code
1. Explicá siempre el PORQUÉ de cada decisión de código, no solo el qué.
   El objetivo es que cualquiera del equipo pueda defenderlo en el examen oral.
2. No cambies el modelo de datos de la sección "Modelo de datos" sin preguntar primero.
3. No cambies la estructura de paquetes ni las convenciones de nombres sin preguntar.
4. Si hay varias formas válidas de implementar algo, decir cuál se eligió y por qué
   es mejor para este caso puntual.
5. Priorizar código simple y legible por sobre "clever" — el equipo está aprendiendo,
   no optimizando performance de una empresa.
6. No avanzar con el frontend (Angular) hasta que el backend esté completo y
   testeado en Postman.
7. No instalar dependencias nuevas sin justificar por qué son necesarias.