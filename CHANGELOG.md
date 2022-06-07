# Changelog

## [3.1] 2022-06-05 (RAMÓN ROSA PÉREZ)
- Arreglado registro (modificando las reglas de SecurityConfig y cambiando algunos detalles de funcionesUser.js, registro.html y UserController.java)
- Añadidas mejoras hechas en el examen (Actualizado listado de cines en vista de administración y añadido precio entradas variable y editable por sesión)

## [3.0] - 2022-05-19
- Añadida gestión (añadir y editar) de películas, cines, salas y sesiones en el panel de administración.
- Correciones post-feedback:
  - Solucionado un error que no capturaba la excepción al no encontrar el código de una entrada y no mostraba el error de "no encontrada"
  - Añadida la fecha de la sesión al abrir el modal de comprobar código
  - Refactorizado el código javascript para añadir mensajes de feedback al usuario en funciones parametrizadas en funcionesGestion.js y funcionesUser.js
 
## [2.3] - 2022-04-27
- Página de [Gestión de Entradas](http://localhost:8080/admin/gestion-entradas/) funcionando. Permite consultar los asientos de cada sesión al administrador del cine, validar una entrada (al acceder al cine) y comprobar los asientos de la misma.
- Añadida librería para generar códigos QR en la página de [Resumen de la Entrada](http://localhost:8080/entradas/67G5HJ4F).
- Ahora la página de cada [película](http://localhost:8080/peliculas/1) contiene información de la misma obtenida de IMDB (junto con la almacenada en la BD).
- Mejoras en panel de administración. Ahora los modals cargan la información de cada entidad.
- Aplicación desplegada en [contenedor Docker](https://vm38.containers.fdi.ucm.es/)
- Solucionado un error que no permitía comprar entradas con los mismos IDs de asientos. La relación Entrada-Asiento ahora es M a N (antes era 1 a N).
- Solucionado un error que no deshabilitaba el botón de una sesión cuando se agotaban las butacas.
- Correciones post-feedback:
  - El directorio /data ahora está a la misma altura que /src y contiene las imágenes de películas y cines
  - Cambiada la forma de acceder a la página de una entrada, ahora se utiliza el código (antes el id)
  - Se utilizan websockets en chat de [atención al cliente](http://localhost:8080/admin/atencion-cliente)
  - Validación de entradas hecha (para que no entren dos veces con el mismo código)

## [2.2] - 2022-04-08
- Chat de atención al cliente con Websockets funcionando (permite al administrador atender a varios clientes).
- Filtro por cine, sala, fecha, título, categoría, orden alfabético o duración añadido al listado de películas.
- Correciones post-feedback:
  - Eliminados @Getter y @Setter (dejando @Data).
  - @SuppressWarnings("unchecked") cambiados por createNamedQuery con 2 argumentos.

## [2.1] - 2022-03-23
- Prueba externa para la compra de entradas.
- Desactivado botón comprar cuando el número de asientos seleccionados es 0.
- Añadido mensaje de error al hacer login erroneo.

## [2.0] - 2022-03-16
- Vista de [Cines](http://localhost:8080/cines/) consultando a la base de datos
- Vista de [Películas y Sesiones](http://localhost:8080/peliculas/) consultando a la base de datos
- Vista de [Comprar Entradas](http://localhost:8080/entradas/compra-entradas?sesion=1) consultando a la base de datos y actualizando el mapa de asientos con AJAX
- Vista de [Resumen entrada](http://localhost:8080/entradas/67G5HJ4F) consultando a la base de datos.
- Vista de [Perfil de Usuario](http://localhost:8080/user/1) mostrando listado de entradas compradas y pudiendo cambiar información del usuario (imagen, datos y contraseña).
- Vista de [Panel de administración](http://localhost:8080/admin/) mostrando los listados desde la base de datos.
- Posibilidad de hacer una compra de entradas completa (con almacenamiento en base de datos) y ¡posibilidad de imprimir la entrada!

## [1.0] - 2022-02-22
- Actualización del modelo con todas las entidades creadas en la base de datos con JPA
- Correciones post-feedback:
  - Añadido filtro por cine, por día y por sala a todos los listados de sesiones.
  - Cambio de tipo en fechas y horas de sesiones de String a LocalDateTime.
  - Añadida la entidad Sala
  - Añadidos días de apertura y festivos (consultables desde el modal de añadir cine en el panel de administración).
- Vista dinámica de [Listado de Cines](http://localhost:8080/cines/) y consulta a API Google Maps.
