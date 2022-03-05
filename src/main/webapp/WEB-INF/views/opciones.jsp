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
					
						<a href="/cliente/verDetalle/${libro.isbn}" class="btn btn-info btn-sm">Ver detalle</a>
						
						<sec:authorize access="hasAuthority('ROL_CLIENTE')">
							<a href="/addCarrito/${libro.isbn}" class="btn btn-success btn-sm">Add carrito</a>
		     			</sec:authorize>
		     			
		     			<sec:authorize access="hasAuthority('ROL_ADMON')">
							<a href="/modificar/${libro.isbn}" class="btn btn-warning btn-sm">Modificar</a>
							<a href="/eliminar/${libro.isbn}" class="btn btn-danger btn-sm">Eliminar</a>
		     			</sec:authorize>

	</body>
</html>