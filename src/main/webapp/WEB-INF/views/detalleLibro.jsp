<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Libro ${libroEntero.titulo}</title>
		<link rel="stylesheet" media="screen" href="/css/style.css"/>
	</head>
	
	<body class="container-fluid">
	
	<jsp:include page="menu.jsp"/>
	
	<p><a href="javascript:history.back()"><button type="button" class="btn btn-outline-secondary">Volver</button></a></p>
	
	<sec:authorize access="hasAuthority('ROL_CLIENTE')">
	
	<c:set var="contains" value="false" />
	<c:forEach var="libro" items="${carrito}">
	  <c:if test="${libro.isbn eq libroEntero.isbn}">
	    <c:set var="contains" value="true" />
	  </c:if>
	</c:forEach>	
	
	
	<c:if test="${contains  == 'false'}">
		<p><a href="/cliente/addCarrito/${libroEntero.isbn}" class="btn btn-success btn-sm">Añadir al carrito</a></p>
	</c:if>
	
	<c:if test="${contains  == 'true'}">
		<p><a class="btn btn-outline-success btn-sm">Ya está en el carrito</a></p>
	</c:if>
	
	</sec:authorize>
	

	
	<img src="/img/${libroEntero.isbn}.jpg"/>
	
	<table class="table table-striped table-sm" >
		<tr>
			<th>ISBN</th>
			<th>Titulo</th>
			<th>Autor</th>
			<th>Precio (€)</th>
			<th>Páginas</th>
			<th>Temática</th>
		</tr>		
			<tr>
				<td>${libroEntero.isbn}</td>
				<td>${libroEntero.titulo}</td>
				<td>${libroEntero.autor}</td>
				
					<c:set var="precio" value="${libroEntero.precioUnitario}" />
					<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${precio}" /></td>
					
				<td>${libroEntero.paginas}</td>
				<td>${libroEntero.tema.descTema}</td>
			</tr>
	</table>
	
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	</body>
</html>