<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Novedades</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	
	<body>	
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href="/">Librería</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav">
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/usuarios">Usuarios</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/perfiles">Perfiles</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/clientes">Clientes</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/temas">Temas</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/cliente/tema">Buscar por tema</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/cliente/buscar">Buscar títulos</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/cliente/misDatos">Mis datos</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/cliente/verCarrito">Mi carrito</a>
		      </li>
		      
		      <li class="nav-item"><a class="nav-link"
									href="/index">Iniciar Sesión</a></li>
								<li class="nav-item"><a class="nav-link"
									href="/registro">Registrarse</a></li>
				<li class="nav-item"><a class="nav-link"
						href="/logout">Cerrar Sesión</a></li>
		    </ul>
		  </div>
		</nav>
		<table class="table table-striped table-sm" >
			<tr>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Precio</th>
				<th>Opciones</th>
			</tr>		
			<c:forEach var="libro" items="${listaNovedades }" >
				<tr>
					<td>${libro.titulo}</td>
					<td>${libro.autor}</td>
					<td>${libro.precioUnitario}</td>
					<td>
						<a href="/cliente/verDetalle/${libro.isbn}" class="btn btn-info btn-sm">Ver detalle</a>
						<a href="/addCarrito/${libro.isbn}" class="btn btn-success btn-sm">Add carrito</a>
						<a href="/modificar/${libro.isbn}" class="btn btn-warning btn-sm">Modificar</a>
						<a href="/eliminar/${libro.isbn}" class="btn btn-danger btn-sm">Eliminar</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>