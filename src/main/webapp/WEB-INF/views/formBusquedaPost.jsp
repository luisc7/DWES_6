<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" media="screen" href="/css/style.css"/>
	<title>Búsqueda de libros por título</title>
</head>

<body class="container-fluid">

	<jsp:include page="menu.jsp"/>
	
	<h1 class="text-primary">Búsqueda de libros</h1>
	<form action="buscar" method="post" name="formBusqueda">
	<fieldset class="form-group">		
		<div class="grid">		
			<label for="busqueda">Título del libro </label>
			<input type="text" name="busqueda" id="busqueda" class="form-control"  />
		</div>
		
	</fieldset>
	
	<input class="send-button btn btn-primary" type="submit" value="Busca libros" />
	
	</form>
</body>
</html>