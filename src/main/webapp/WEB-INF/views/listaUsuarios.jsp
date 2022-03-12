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
	</head>
	
	<body class="container-fluid">	
	
		<jsp:include page="menu.jsp"/>
		
		<p class="alert ${tipoMensaje}" role="alert">${mensaje}</p>
		
		<h1 class="text-primary">${h1pagina}</h1>
		
		<a href="/admon/registroAdmon" class="btn btn-primary">Registrar nuevo administrador</a>
		
		<h2 class="text-secondary">Administradores</h2>
		<table class="table table-striped table-sm" >
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Usuario</th>
				<th>Correo electrónico</th>
				<th>Opciones</th>
			</tr>		
			<c:forEach var="admon" items="${listadoAdmons }" >
				<tr>
					<td>${admon.nombre}</td>
					<td>${admon.apellido}</td>
					<td>${admon.username}</td>
					<td>${admon.email}</td>
					
					<c:set var="desactivarOpciones" value="" />
					 <c:if test="${admon.username eq superAdmon || admon.username eq superAdmon}">
					 	<c:set var="desactivarOpciones" value="disabled\" tabindex=\"-1\" role=\"button\" aria-disabled=\"true" />
					 </c:if>
					
					<td>
						<c:if test="${admon.enabled == 1}">
							<a href="/admon/usuarios/${admon.username}?desactivar" class="btn btn-secondary btn-sm ${desactivarOpciones}">Desactivar</a>
						</c:if>
							
						<c:if test="${admon.enabled == 0}">
							<a href="/admon/usuarios/${admon.username}?activar" class="btn btn-primary btn-sm ${desactivarOpciones}">&nbsp;&nbsp;Activar&nbsp;&nbsp;&nbsp;</a>
						</c:if>
							<a href="/admon/usuarios/${admon.username}?borrar" class="btn btn-danger btn-sm ${desactivarOpciones}">Borrar</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>