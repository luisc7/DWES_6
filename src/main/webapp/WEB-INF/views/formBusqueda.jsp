<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="css/style.css"/>
<title>Búsqueda de libros por título</title>
</head>
<body>

	<jsp:include page="menu.jsp"/>
		
	<form action="buscar" method="post" name="formBusqueda">
	<fieldset>
		<legend>Busca libros en nuestra colección:</legend>
		<div class="grid">		
			<label for="busqueda">Escribe aquí: </label>
			<input type="text" name="busqueda" id="busqueda" />
		</div>
		
	</fieldset>
	
	<input class="send-button" type="submit" value="Busca libros" />
	
	</form>
</body>
</html>