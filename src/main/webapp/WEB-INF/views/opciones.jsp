<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>${tituloPagina}</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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