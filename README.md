# FilmAdmin

## [Trabajo pendiente](https://docs.google.com/document/d/1Jl7SJprP_qKExY1gCgEuXgIvUAb5TF3F0B_dflthpi4/edit?usp=sharing)

## Cambios realizados (versión 2.0)
- Vista de [Cines](http://localhost:8080/cines/) consultando a la base de datos
- Vista de [Películas y Sesiones](http://localhost:8080/peliculas/) consultando a la base de datos
- Vista de [Comprar Entradas](http://localhost:8080/entradas/compra-entradas?sesion=1) consultando a la base de datos y actualizando el mapa de asientos con AJAX
- Vista de [Resumen entrada](http://localhost:8080/entradas/1) consultando a la base de datos.
- Vista de [Perfil de Usuario](http://localhost:8080/user/1) mostrando listado de entradas compradas.
- Posibilidad de hacer una compra de entradas completa (con almacenamiento en base de datos) y ¡posibilidad de imprimir la entrada!


## Cambios realizados (versión 1.1)
- Actualización del modelo con todas las entidades creadas en la base de datos con JPA
- Correciones post-feedback:
  - Añadido filtro por cine, por día y por sala a todos los listados de sesiones.
  - Cambio de tipo en fechas y horas de sesiones de String a LocalDateTime.
  - Añadida la entidad Sala
  - Añadidos días de apertura y festivos (consultables desde el modal de añadir cine en el panel de administración).
- Vista dinámica de [Listado de Cines](http://localhost:8080/cines/) y consulta a API google maps.


## Index
[Página de Inicio](http://localhost:8080).

Pagina de inicio con el logo del cine que además actúa de navbar con enlaces al resto de páginas visibles para el usuario.

## Películas
[Listado de Películas](http://localhost:8080/peliculas/).

Listado de películas donde poder seleccionar un cine para ver las butacas disponibles en cada sesión, poder filtrar por título, categoría, duración u orden alfabético y poder acceder a la información de la película o comprar entradas para una sesión en particular.

## Información Película
[Información de una Película](http://localhost:8080/peliculas/1).

Información de la película obtenida de IMDB (imagen, trailer, duración, país, género, directores, etc) además del listado de sesiones de la vista anterior.

## Comprar entrada
[Compra de entradas](http://localhost:8080/compra-entradas).

Vista donde seleccionar la información de la compra (cine, número de entradas, códigos promocionales y ver el precio final). Nota: se pueden seleccionar los asientos dando al más y al menos en cuyo caso se asignarán los primeros que encuentre disponibles empezando por arriba a la izquierda o directamente pinchando en los que quieres, además de poder deseleccionar asientos en caso de haberte equivocado con el click derecho. Por último se muestra un alert al seleccionar un asiento ocupado).

## QR y Página postcompra entrada
[Resumen entrada](http://localhost:8080/entradas/67G5HJ4F).

Aquí tenemos la página postcompra donde encontramos un resumen de la información de nuestra entrada, las opciones de imprimirla o descargarla en pdf (aún no implementadas) y el código QR generado a su lado con el id del enlace que autogenere (en este caso 67G5HJ4F). Este ID se podrá utilizar en la página de gestión de entradas del administrador para comprobar la validez o los asientos de las mismas.

## Cines
[Listado de Cines](http://localhost:8080/cines/).

Listado de cines disponibles con un pequeño frame del google maps indicando su ubicación, cierta información del lugar y un slider de imágenes.

## Pefil de Usuario
[Perfil de Usuario](http://localhost:8080/user/1).

Perfil del usuario donde poder actualizar la información del mismo, ver las entradas que has comprado (con un enlace a la pagina postcompra donde ves el QR) y la opción de cambiar la contraseña.

## User - Atención al Cliente
[Chat de Atención al Cliente - USUARIO](http://localhost:8080/atencion-cliente/).

Chat básico de atención al cliente.

## Admin - Atención al Cliente
[Chat de Atención al Cliente - ADMIN](http://localhost:8080/admin/atencion-cliente).

Vista de administración de los chats de atención al cliente (con varios tabs para poder tener varios chat abiertos con diferentes clientes).

## Admin - Gestión de Entradas
[Ver sesiones y comprobar entradas](http://localhost:8080/admin/gestion-entradas/).

Vista en la que poder ver los asientos ocupados y disponibles en cada sesión y comprobar la validez y asientos asignados al código de una entrada (en un modal que se abre al pulsar en Comprobar código).

## Admin - Panel de Administración
[Panel de administración](http://localhost:8080/admin/).

Vista de administración en la que poder añadir, editar o eliminar cualquier usuario, película, cine o sesión. (Funciona con tabs para cada entidad y dentro de cada entidad encontramos una tabla con los elementos, un buscador y un boton añadir que abre un modal para crear un elemento de esta entidad).
