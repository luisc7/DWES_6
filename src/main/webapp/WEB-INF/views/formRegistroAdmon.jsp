<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" media="screen" href="/css/style.css"/>
	<title>Registro admon</title>
</head>

<body class="container-fluid">

	<jsp:include page="menu.jsp"/>
	
	<p><a href="/admon/usuarios"><button type="button" class="btn btn-outline-secondary">Volver</button></a></p>

	
	<h1 class="text-primary">Registro de un nuevo administrador</h1>
	<form action="/admon/registroAdmon" method="post" name="registroForm">
	<fieldset>
		<legend>Cumplimente sus datos</legend>
		<div class="grid">

			<label for="email">Correo electrónico* </label>
			<input type="text" name="email" id="email"  class="form-control"  placeholder="Obligatorio" required/>
			
			<label for="password">Contraseña* </label>
			<input type="password" name="password" id="password"  class="form-control"  placeholder="Obligatorio" required/>
			
			<label for="username">Alias (nombre de usuario)* </label>
			<input type="text" name="username" id="username"  class="form-control" placeholder="Obligatorio" required/>
			
			<label for="nombre">Nombre </label>
			<input type="text" name="nombre" id="nombre"  class="form-control"/>
			
			<label for="apellido">Apellido(s) </label>
			<input type="text" name="apellido" id="apellido"  class="form-control"/>
			
			<label for="direccion">Dirección </label>
			<input type="text" name="direccion" id="direccion"  class="form-control"/>
						
			</div>
		
	</fieldset>
	
	<input class="send-button btn btn-primary" type="submit" value="Crear usuario" />
	
	<p class="alert ${tipoMensaje}" role="alert">${mensajeAdmon}</p>
	
	</form>
</body>
</html>