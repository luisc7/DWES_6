USE LIBRERO_BBDD;

INSERT INTO USUARIOS (USERNAME, PASSWORD, ENABLED, EMAIL, NOMBRE) 
    VALUES 
    ('Tutankadmon', 'faraon', 1, 'tutan@admon.com', 'Admón Faraón'),
    ('ClientePrueba1', 'secreto1', 1, 'cliente1@test.com', 'Cliente de prueba 1'),
    ('ClientePrueba2', 'secreto2', 1, 'cliente2@test.com', 'Cliente de prueba 2');

INSERT INTO USUARIO_PERFILES (USERNAME, ID_PERFIL) 
    VALUES 
    ('Tutankadmon', (SELECT ID_PERFIL FROM perfiles WHERE perfiles.descripcion='ROL_ADMON')),
    ('ClientePrueba1', (SELECT ID_PERFIL FROM perfiles WHERE perfiles.descripcion='ROL_CLIENTE')),
    ('ClientePrueba2', (SELECT ID_PERFIL FROM perfiles WHERE perfiles.descripcion='ROL_CLIENTE'));

INSERT INTO TEMAS (DESC_TEMA, ABREVIATURA)
    VALUES
    ('Fotografía', 'FOTO'),
    ('Idiomas', 'IDIOM'),
    ('Mecánica', 'MECAN'),
    ('Narrativa', 'NARR');

INSERT INTO LIBROS (ISBN, TITULO, AUTOR, PRECIO_UNITARIO, PAGINAS, ID_TEMA)
    VALUES
    (9788417492045, 'Magnum hojas de contacto', 'Blume', 35, 524, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='FOTO')),
    (9788470631810, 'Normalización del dibujo industrial', 'F. Javier Rodríguez de Abajo, Roberto Galarraga Astibia', 60, 307, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='MECAN')),
    (9788425327926, 'Collins Compact Diccionario Inglés', 'Grijalbo', 18, 875, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='IDIOM')),
    (9788408067610, 'El príncipe de la niebla', 'Carlos Ruiz Zafón', 15, 230, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='NARR')),
    (9788428319683, 'Máquinas prontuario. Técnicas, máquinas, herramientas', 'N. Larburu', 30, 626, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='MECAN')),
    (9788440072160, 'Maquinas. Cálculos de Taller', 'A. L. Casillas', 25, 643, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='MECAN')),
    (9788408033431, 'El alquimista', 'Paulo Coelho', 28, 222, (SELECT ID_TEMA FROM temas WHERE temas.abreviatura='NARR'));