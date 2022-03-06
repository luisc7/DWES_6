<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Modificar libro</title>
</head>
<body>

	<jsp:include page="menu.jsp"/>
	
	<h1 class="text-primary">Modificar los datos del libro</h1>
	<form action="modificarLibro" method="post" name="modificarLibro">
	<fieldset>
		<legend>Datos del libro a modificar</legend>
		<div class="grid">
		
		
			<label for="ISBN">ISBN </label>
			<input type="number" name="ISBN" id="ISBN" value="${libroModificar.isbn }" class="form-control" readonly/>
			
			<label for="tema">Temática</label>
            <select name="tema">    
            <c:forEach var="temaBBDD" items="${listaTemas}" >
            
            <c:choose>
	            <c:when test="${temaBBDD.idTema == libroModificar.tema.idTema}">
	            	<c:set var="selectedTema" value="selected"/>
	            </c:when>
	            <c:otherwise>
	                <c:set var="selectedTema" value=""/>
	            </c:otherwise>
            </c:choose>
            
            <option value="${temaBBDD.idTema}" ${selectedTema}>${temaBBDD.descTema}</option>
            </c:forEach>        
            </select>
		
			<label for="nombreLibro">Nombre del libro </label>
			<input type="text" name="nombreLibro" id="nombreLibro"  class="form-control" value="${libroModificar.titulo }" />
		
			<label for="autor">Autor </label>
			<input type="text" name="autor" id="autor" value="${libroModificar.autor }"  class="form-control"/>
			
			<label for="paginas">Páginas </label>
			<input type="number"  required value="${libroModificar.paginas }"  name="paginas" id="paginas"  class="form-control"/>
			
			<label for="precio">Precio unitario </label>
			<input type="number" step="0.01"  required value="${libroModificar.precioUnitario }"  name="precio" id="precio"  class="form-control"/>
			
			<label for="novedad">¿Novedad?</label>
			
			<c:choose>
	            <c:when test="${'N' == libroModificar.novedad}">
	            	<c:set var="selectedN" value="selected"/>
	            	<c:set var="selected-" value=""/>
	            </c:when>
	            <c:otherwise>
	            	<c:set var="selectedN" value=""/>
	            	<c:set var="selectedEmpty" value="selected"/>
	            </c:otherwise>
            </c:choose>			
			
            <select name="novedad">
                <option value="" ${selectedEmpty}>-</option>
                <option value="N" ${selectedN}>Sí</option>
            </select>
			
			</div>
		
	</fieldset>
	
	<input class="send-button btn btn-primary" type="submit" value="Modificar libro" />
	
	</form>
</body>
</html>