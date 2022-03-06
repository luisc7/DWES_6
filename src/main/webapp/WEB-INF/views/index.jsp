<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Novedades</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	
	<body>

		<jsp:include page="menu.jsp"/>
		
		<p class="alert ${tipoMensaje}" role="alert">${mensajeCliente}</p>
		
		<h1 class="text-primary">${h1pagina}</h1>
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
						
						<sec:authorize access="hasAuthority('ROL_CLIENTE')">
							<a href="/cliente/addCarrito/${libro.isbn}" class="btn btn-success btn-sm">Add carrito</a>
		     			</sec:authorize>
		     			
		     			<sec:authorize access="hasAuthority('ROL_ADMON')">
							<a href="/admon/modificarLibro/${libro.isbn}" class="btn btn-warning btn-sm">Modificar</a>
							<a href="/admon/borrarLibro/${libro.isbn}" class="btn btn-danger btn-sm">Borrar libro</a>
		     			</sec:authorize>
					</td>
				</tr>
			</c:forEach>
		</table>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>