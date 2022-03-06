<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Alta de nuevo tema</title>
</head>
<body>

	<jsp:include page="menu.jsp"/>
	
	<p><a href="javascript:history.back()"><button type="button" class="btn btn-outline-secondary">Volver</button></a></p>
		
	<h1 class="text-primary">Dar de alta un nuevo tema</h1>
	<form action="altaTema" method="post" name="altaTema">
	<fieldset>
		<legend>Añadir nuevas temáticos de libros</legend>
		<div class="grid">
		
			<label for="nombreTema">Nombre del nuevo tema </label>
			<input type="text" name="nombreTema" id="nombreTema"  class="form-control"/>
			
			<label for="abrevTema">Abreviatura </label>
			<input type="text" name="abrevTema" id="abrevTema" placeholder="Máximo 6 caracteres" maxlength="6" class="form-control"/>
		</div>
		
	</fieldset>
	
	<input class="send-button btn btn-primary" type="submit" value="Añadir tema" />
	
	</form>
</body>
</html>