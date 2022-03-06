<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" media="screen" href="/css/style.css"/>
<title>Alta nuevo libro</title>
</head>
<body>

	<jsp:include page="menu.jsp"/>
	
	<h1 class="text-primary">Dar de alta un nuevo libro</h1>
	<form action="altaLibro" method="post" name="altaLibro">
	<fieldset>
		<legend>Datos del libro a añadir</legend>
		<div class="grid">
		
		
			<label for="ISBN">ISBN </label>
			<input type="number" name="ISBN" id="ISBN"  class="form-control"/>
			
			<label for="tema">Temática</label>
            <select name="tema">    
            <c:forEach var="temaBBDD" items="${listaTemas}" >
                <option value="${temaBBDD.idTema}">${temaBBDD.descTema}</option>
            </c:forEach>        
            </select>
		
			<label for="nombreLibro">Nombre del nuevo libro </label>
			<input type="text" name="nombreLibro" id="nombreLibro"  class="form-control"/>
		
			<label for="autor">Autor </label>
			<input type="text" name="autor" id="autor"  class="form-control"/>
			
			<label for="paginas">Páginas </label>
			<input type="number"  required value="0" name="paginas" id="paginas"  class="form-control"/>
			
			<label for="precio">Precio unitario </label>
			<input type="number" step="0.01"  required value="0" name="precio" id="precio"  class="form-control"/>
			
			<label for="novedad">¿Novedad?</label>
            <select name="novedad">
                <option value="">-</option>
                <option value="N">Sí</option>
            </select>
			
			</div>
		
	</fieldset>
	
	<input class="send-button btn btn-primary" type="submit" value="Añadir libro" />
	
	</form>
</body>
</html>