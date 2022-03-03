# AI-6. Librería con Spring Security

## Enunciado

Modelo de bases de datos (creación de las tablas al final):

![Modelo de datos del ejercicio](src/main/resources/static/img/AI6_BBDD_librero.png)

Un librero quiere empezar a vender sus libros por Internet. Desarrollar con Spring Boot una aplicación que la puedan usar tanto los clientes que se registren (ROL_CLIENTE) como el propietario (ROL_ADMON). El propietario podrá realizar operaciones de mantenimiento de las tablas a través de la aplicación (CRUD), así como gestionar sus clientes y pedidos.

Se empleará un carrito simple: Lo primero que aparece es una pantalla pidiendo el usuario y la contraseña, además de la opción de registrarse. En la base de datos se crea un usuario de cada ROL:

- Los usuarios cliente se pueden registrar en esta página (el rol lo ponemos nosotros desde el programa).
- El ROL_ADMINISTRADOR solo podrá dar de alta usuarios desde dentro de la aplicación.

a) Al entrar en la aplicación aparecerá un menú de navegación compartido por todos los usuarios, pero cuyos apartados se mostrarán o no según el ROL que tenga:

  1. Usuarios, Perfiles, Clientes, Temas: solo visibles por el ADMINISTRADOR.
  2. Buscar por Tema, Buscar por palabra (LIKE): ADMINISTRADOR y CLIENTE.
  3. Mis Datos y ver carrito: SOLO CLIENTE.
  4. (Versión carrito complejo) Inicio y Registro: solo si el usuario NO está autenticado (usuarios no autenticados pueden  navegar por la aplicación).
  5. Cerrar Sesión: SOLO si el usuario está autenticado.
  6. Un enlace a nuevo Libro (ROL_ADMON).

Y un listado de los libros marcados como novedad en la tabla de libros con la información:

- Titulo, autor, precio.
- Y las opciones :
  - Ver detalle (ROL_ADMON y ROL_CLIENTE).
  - Añadir Carrito (ROL_CLIENTE).
  - Editar, eliminar (ROL_ADMON).

### LIBRERÍA. ROL_CLIENTE: casos de uso(**“/cliente”**)

- **“/tema"** : mostrar la lista de libros de ese tema (con las opciones ver detalle y añadir carrito).
- **“/buscar"** : con la cadena introducida por parámetro, buscar los libros cuyo título contenga esa cadena.
- **“/verDetalle/{isbn}"** : Mostrar los campos de la base de datos incluida la imagen del libro (si no lo tienes saca siempre la misma imagen. Con las opciones: volver, añadir carrito.

### CARRITO

El carrito va a ser un arraylist de Libro, para hacerlo fácil, porque la cantidad de libros pedidos solo será uno (el librero de momento tiene miedo al stock), y el precio venta será de momento el del libro.

Opciones de carrito:

- **“/addCarrito/{isbn}"** : añadir el libro al arraylist del carrito de la sesión (Si el cliente escoge dos veces el mismo libro, solo le aparecerá una sola vez en el carrito (de momento no contemplamos cantidad).
- **“/verCarrito"** : en página nueva sacar la lista de libros del carrito, incluir en esta pantalla la opción de Cerrar Sesión.

Cuando aparezca el carrito con los libros seleccionados, veremos las siguientes opciones:

1. **“/comprar"** : se grabarán los datos de los libros comprados. La clase pedido se genera con la lista de libros_en_pedido. La información será:

    - Clase Pedido: el username, de la sesión, la dirección de entrega del usuario registrado, estado “terminado”.
    - Líneas del pedido: el pedido el creado, el libro, del carrito(arraylist), la cantidad 1, y el precio el del libro. Al salvar el pedido, como la clase Pedido tiene la opción “cascade persist”, se inserta el pedido en la base de datos, y se inserta una fila en la tabla “líneas_pedido” POR CADA ELEMENTO DEL CARRITO.

2. **“/eliminar"** : Eliminar el libro del carrito y recargar la página
3. **“/volver"** : volver a la pantalla de inicio.
4. **“/logout"** : cerrar la sesión.
    - a) Si no has hecho formulario de login, security te cierra la sesión, y te devuelve al formulario de Inicio sesión de forma automática.
    - b) Si tienes un formulario de login propio, debes implementar un método con esta url para poner los mandatos de security indicados en clase, y volver al formulario de inicio de sesión.

### LIBRERÍA. ROL_ADMON.(**“/admon**)

1. Dar de alta un Tema.
2. Dar de alta un Libro.
3. Ver los pedidos realizados en un día: con información del nombre y dirección del cliente, y la lista de libros que incorpora el pedido. Puedes sacar un listado de los pedidos y un ver detalle en que sacas los datos del cliente y la lista de libros. No vamos a ir más allá. No vamos a gestionar si lo ha procesado o no, ni la gestión del envío al cliente. Es una especie de guía para que el librero no se deje ningún pedido por procesar.
4. Ver los datos de un cliente: cuantos libros ha comprado, de cuantos temas y el total del importe comprado. Aquí cuando te pulsen en el menú en el botón de cliente, sacar un listado de los clientes, y una opción Info, para sacr los datos de ese cliente.
5. etc.

Y si quieres meter más cosas eres libre de hacerlo. Crea tus propias url.

## Secuencia SQL para creación de BBDD, usuario y perfiles

    CREATE DATABASE LIBRERO_BBDD;
    USE LIBRERO_BBDD;

    CREATE TABLE TEMAS (
        ID_TEMA INTEGER PRIMARY KEY, 
        DESC_TEMA VARCHAR(50) NOT NULL,
        ABREVIATURA VARCHAR(6)
    );    

    CREATE TABLE LIBROS (
        ISBN DEC(15) PRIMARY KEY, 
        TITULO VARCHAR(200) NOT NULL,
        AUTOR VARCHAR(200) NOT NULL, 
        PRECIO_UNITARIO DECIMAL(9,2),
        PAGINAS INTEGER,
        NOVEDAD char(1),
        -- IMAGEN varchar(50),
        ID_TEMA INTEGER NOT NULL,
        FOREIGN KEY(ID_TEMA) REFERENCES TEMAS(ID_TEMA)
    );
    
    CREATE TABLE PERFILES (
        ID_PERFIL INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
        DESCRIPCION VARCHAR(20) NOT NULL
    );

    CREATE TABLE USUARIOS (
        USERNAME VARCHAR(50) PRIMARY KEY, 
        PASSWORD VARCHAR(20) NOT NULL, 
        EMAIL VARCHAR(100)  NOT NULL UNIQUE,
        NOMBRE VARCHAR(50), 
        APELLIDO VARCHAR(50), 
        DIRECCION VARCHAR(100), 
        FECHA_ALTA DATE
    );

    CREATE TABLE USUARIO_PERFILES (
        USERNAME VARCHAR(50) NOT NULL,
        ID_PERFIL INTEGER NOT NULL,
        PRIMARY KEY(USERNAME, ID_PERFIL),
        FOREIGN KEY(USERNAME) REFERENCES USUARIOS(USERNAME),
        FOREIGN KEY(ID_PERFIL) REFERENCES PERFILES(ID_PERFIL)
    );

    CREATE TABLE PEDIDOS (
        ID_PEDIDO INTEGER PRIMARY KEY, 
        DIRECCION_ENTREGA VARCHAR(50),
        ESTADO VARCHAR(30) NOT NULL, 
        FECHA_ALTA DATE,
        USERNAME VARCHAR(50) NOT NULL ,
        FOREIGN KEY(USERNAME) REFERENCES USUARIOS(USERNAME)
    );

    CREATE TABLE LINEAS_PEDIDO (
        NUM_ORDEN INTEGER  AUTO_INCREMENT,
        ID_PEDIDO INTEGER NOT NULL,
        ISBN DEC(15) NOT NULL, 
        FECHA_ALTA DATE, 
        CANTIDAD INTEGER,
        PRECIO_VENTA DECIMAL(9,2),
        PRIMARY KEY(NUM_ORDEN),
        FOREIGN KEY(ID_PEDIDO) REFERENCES PEDIDOS(ID_PEDIDO),
        FOREIGN KEY(ISBN) REFERENCES LIBROS(ISBN)
    );

    CREATE USER 'ulibrero'@'localhost' IDENTIFIED BY 'ulibrero';
    GRANT ALL PRIVILEGES ON LIBRERO_BBDD.* TO  'ulibrero'@'localhost';

    -- DROP USER 'ulibrero'@'localhost' ;

    INSERT INTO PERFILES (DESCRIPCION) 
        VALUES 
        (ROL_ADMON),
        (ROL_CLIENTE);
