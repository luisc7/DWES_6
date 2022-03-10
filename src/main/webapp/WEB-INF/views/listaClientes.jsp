<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>${tituloPagina}</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	
	<body>	
	
		<jsp:include page="menu.jsp"/>
		
		<p class="alert ${tipoMensaje}" role="alert">${mensaje}</p>
		
		<h1 class="text-primary">${h1pagina}</h1>
		<table class="table table-striped table-sm" >
			<tr>
				<th>Nombre</th>
				<th>Usuario</th>
				<th>Correo electrónico</th>
				<th>Opciones</th>
			</tr>		
			<c:forEach var="cliente" items="${listadoClientes }" >
				<tr>
					<td>${cliente.nombre}</td>
					<td>${cliente.username}</td>
					<td>${cliente.email}</td>
					<td>
							<a href="/admon/clientes/${cliente.username}" class="btn btn-info btn-sm">Info</a>
							<a href="/admon/clientes/${cliente.username}?borrar" class="btn btn-danger btn-sm">Borrar cliente</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>