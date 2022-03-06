USE LIBRERO_BBDD;

INSERT INTO USUARIOS (USERNAME, PASSWORD, ENABLED, EMAIL, NOMBRE) 
    VALUES 
    ('Tutankadmon', 'faraon', 1, 'tutan@admon.com', 'Admón Faraón'),
    ('cliente1', 'secreto1', 1, 'cliente1@test.com', 'Cliente de prueba 1'),
    ('cliente2', 'secreto2', 1, 'cliente2@test.com', 'Cliente de prueba 2');

INSERT INTO USUARIO_PERFILES (USERNAME, ID_PERFIL) 
    VALUES 
    ('Tutankadmon', (SELECT ID_PERFIL FROM perfiles WHERE perfiles.descripcion='ROL_ADMON')),
    ('cliente1', (SELECT ID_PERFIL FROM perfiles WHERE perfiles.descripcion='ROL_CLIENTE')),
    ('cliente2', (SELECT ID_PERFIL FROM perfiles WHERE perfiles.descripcion='ROL_CLIENTE'));

INSERT INTO TEMAS (DESC_TEMA, ABREVIATURA)
    VALUES
    ('Fotografía', 'FOTO'),
    ('Idiomas', 'IDIOM'),
    ('Mecánica', 'MECAN'),
    ('Narrativa', 'NARR');

INSERT INTO LIBROS (ISBN, TITULO, AUTOR, PRECIO_UNITARIO, PAGINAS, NOVEDAD, ID_TEMA)
    VALUES
    (9788417492045, 'Magnum hojas de contacto', 'Blume', 35, 524, 'N', (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='FOTO')),
    (9788470631810, 'Normalización del dibujo industrial', 'F. Javier Rodríguez de Abajo, Roberto Galarraga Astibia', 60, 307, 'N', (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='MECAN')),
    (9788425327926, 'Collins Compact Diccionario Inglés', 'Grijalbo', 18, 875, NULL, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='IDIOM')),
    (9788487227134, 'Gran Diccionario Larouse Español-Inglés | Inglés-Español', 'Ramón García-Pelayo y Gross', 17.6, 1542, NULL, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='IDIOM')),
    (9788408067610, 'El príncipe de la niebla', 'Carlos Ruiz Zafón', 15, 230, NULL, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='NARR')),
    (9788428319683, 'Máquinas prontuario. Técnicas, máquinas, herramientas', 'N. Larburu', 30, 626, NULL, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='MECAN')),
    (9788440072160, 'Maquinas. Cálculos de Taller', 'A. L. Casillas', 25, 643, NULL, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='MECAN')),
    (9788408033431, 'El alquimista', 'Paulo Coelho', 28, 222, NULL, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='NARR')),
    (9788409183296, 'Teoría del color. Aplicación práctica en fotografía', 'Jesús Manuel García Flores', 32, 340, 'N', (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='FOTO')),
    (9788434822061, 'Chis y Garabis', 'Paloma Bordons', 12, 120, NULL, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='NARR'));