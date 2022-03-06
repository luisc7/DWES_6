<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>${tituloPagina}</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	
	<body>	
	
		<jsp:include page="menu.jsp"/>
		
		<h1>${h1pagina}</h1>
		<table class="table table-striped table-sm" >
			<tr>
				<th>ISBN</th>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Precio</th>
				<th>Páginas</th>
				<th>Temática</th>
				<th>Opciones</th>
			</tr>		
			<c:forEach var="libro" items="${listadoLibros }" >
				<tr>
					<td>${libro.isbn}</td>
					<td>${libro.titulo}</td>
					<td>${libro.autor}</td>
					<td>${libro.precioUnitario}</td>
					<td>${libro.paginas}</td>
					<td>${libro.tema.descTema}</td>
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
							<a href="/cliente/addCarrito/${libroEntero.isbn}" class="btn btn-success btn-sm">Add carrito</a>
						</c:if>
	
		     			</sec:authorize>
		     			
		     			<sec:authorize access="hasAuthority('ROL_ADMON')">
							<a href="/modificar/${libro.isbn}" class="btn btn-warning btn-sm">Modificar</a>
							<a href="/eliminar/${libro.isbn}" class="btn btn-danger btn-sm">Eliminar</a>
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