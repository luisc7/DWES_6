<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Mis datos</title>
		<link rel="stylesheet" media="screen" href="/css/style.css"/>
	</head>
	
	<body class="container-fluid">
	
	<jsp:include page="menu.jsp"/>
	
	<p><a href="volver"><button type="button" class="btn btn-outline-secondary">Volver</button></a></p>
	
		<table class="table table-striped table-sm" >
			<tr>
				<th>Nombre de usuario </th>
				<th>Correo electrónico </th>
				<th>Dirección</th>
				<th>Perfil</th>
			</tr>		
				<tr>
					<td><sec:authentication property="name"/></td>
					<td>${usuarioActivo.email}</td>
					<td>${usuarioActivo.direccion}</td>
					<td>${usuarioActivo.perfiles}</td>
				</tr>
		</table>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>