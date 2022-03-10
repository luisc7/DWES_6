<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Cliente ${cliente.username}</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
		<link rel="stylesheet" media="screen" href="/css/style.css"/>
	</head>
	
	<body>
	
	<jsp:include page="menu.jsp"/>
	
	<p><a href="javascript:history.back()"><button type="button" class="btn btn-outline-secondary">Volver</button></a></p>
	
	<h1 class="text-primary">Detalles del cliente</h1>
	
	<table class="table table-striped table-sm" >
		<tr>
			<th>Nombre y apellidos</th>
			<th>Usuario</th>
			<th>Correo electrónico</th>
			<th>¿Activo?</th>
			<th>Dirección</th>
			<th>Libros comprados</th>
			<th>Cantidad de temáticas</th>
			<th>Importe total de pedidos (€)</th>
		</tr>		
			<tr>
				<td>${cliente.nombre}&nbsp;${cliente.apellido}</td>
				<td>${cliente.username} </td>
				<td>${cliente.email} </td>
				<td>
					<c:if test="${cliente.enabled==1 }">
						<span>Sí </span><a href="${cliente.username}/desactivar" class="btn btn-danger btn-sm">Desactivar</a>
					</c:if>
					<c:if test="${cliente.enabled==0 }">
						<span>No </span><a href="${cliente.username}/activar" class="btn btn-success btn-sm">Activar</a>
					</c:if>
				</td>
				<td>${cliente.direccion}&nbsp;</td>
				<td>${librosTotalCompradosCliente}</td>
				<td>${librosTematicasDiferentesCliente }</td>
				
					<c:set var="gastado" value="${importeTotalPedidosCliente}" />
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${gastado}" /></td>
					
			</tr>
	</table>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>