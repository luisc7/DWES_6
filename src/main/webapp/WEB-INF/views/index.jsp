<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Novedades</title>
	</head>
	
	<body class="container-fluid">

		<jsp:include page="menu.jsp"/>
		
		<p class="alert ${tipoMensaje}" role="alert">${mensajeCliente}</p>
		
		<h1 class="text-primary">${h1pagina}</h1>
		<table class="table table-striped table-sm" >
			<tr>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Precio (€)</th>
				<th>Opciones</th>
			</tr>		
			<c:forEach var="libro" items="${listaNovedades }" >
				<tr>
					<td>${libro.titulo}</td>
					<td>${libro.autor}</td>
					
					<c:set var="precio" value="${libro.precioUnitario}" />
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${precio}" /></td>
					
					
					<td>					
						<a href="/cliente/verDetalle/${libro.isbn}" class="btn btn-info btn-sm">Ver detalle</a>
						
						<sec:authorize access="hasAuthority('ROL_CLIENTE')">
						<c:set var="contains" value="false" />
						<c:forEach var="libroCarrito" items="${carrito}">
						  <c:if test="${libroCarrito.isbn eq libro.isbn}">
						    <c:set var="contains" value="true" />
						  </c:if>
						</c:forEach>	
						
						
						<c:if test="${contains  == 'false'}">
							<a href="/cliente/addCarrito/${libro.isbn}" class="btn btn-success btn-sm">Añadir al carrito</a>
						</c:if>
						
						<c:if test="${contains  == 'true'}">
							<a class="btn btn-outline-success btn-sm">Ya está en el carrito</a>
						</c:if>
	
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