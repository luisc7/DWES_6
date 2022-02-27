CREATE DATABASE LIBRERO_BBDD;
USE LIBRERO_BBDD;

CREATE TABLE TEMAS(
	ID_TEMA INTEGER AUTO_INCREMENT  PRIMARY KEY, 
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
    -- IMAGEN varchar(50);
	ID_TEMA INTEGER NOT NULL,
	FOREIGN KEY(ID_TEMA) REFERENCES TEMAS(ID_TEMA)
);
 
CREATE TABLE PERFILES (
	ID_PERFIL INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	DESCRIPCION VARCHAR(20) NOT NULL
);

CREATE TABLE USUARIOS (
	USERNAME VARCHAR(50) PRIMARY KEY, 
	PASSWORD VARCHAR(200) NOT NULL,
	ENABLED int,
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
	ID_PEDIDO INTEGER PRIMARY KEY AUTO_INCREMENT, 
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

INSERT INTO PERFILES (DESCRIPCION) 
    VALUES 
    (ROL_ADMON),
    (ROL_CLIENTE);

-- DROP USER 'ulibrero'@'localhost' ;