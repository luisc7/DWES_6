<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Carrito</title>
	</head>
	
	<body class="container-fluid">
	
		<jsp:include page="menu.jsp"/>		
		
		<p><a href="volver"><button type="button" class="btn btn-outline-secondary">Volver</button></a></p>
						
		<c:set var="contenido" value="true" />
		
		<c:if test="${empty carrito}">
			<c:set var="contenido" value="false" />
	 	 </c:if>
		
		<c:if test="${contenido  == 'false'}">
			<p class="alert alert-secondary" role="alert">Tu carrito está vacío</p>
	 	 </c:if>
	 	 
	 	 <c:if test="${contenido  == 'true'}">
		<a href="/cliente/comprar" class="btn btn-primary btn-lg">Comprar</a>
	
		<table class="table table-striped table-sm" >
			<tr>
				<th>ISBN</th>
				<th>Titulo</th>
				<th>Precio</th>
				<th>Temática</th>
				<th>Opciones</th>
			</tr>		
			<c:forEach var="libro" items="${carrito }" >
				<tr>
					<td>${libro.isbn}</td>
					<td>${libro.titulo}</td>
					<td>${libro.precioUnitario}</td>
					<td>${libro.tema.descTema}</td>
					<td>	
						<a href="/cliente/verDetalle/${libro.isbn}" class="btn btn-info btn-sm">Ver detalle</a>
						<a href="/cliente/eliminar/${libro.isbn}" class="btn btn-danger btn-sm">Quitar</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		 </c:if>
	 	 
		<p class="alert ${tipoMensaje}" role="alert">${mensajeCarrito }</p>
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>