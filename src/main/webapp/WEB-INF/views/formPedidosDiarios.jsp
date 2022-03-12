<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" media="screen" href="/css/style.css"/>
	<title>Listar pedidos por fecha</title>
</head>

<body class="container-fluid">

	<jsp:include page="menu.jsp"/>
	
	<h1 class="text-primary">Pedidos</h1>
	<form action="pedidos" method="get" name="formPedidosDiarios">
	<fieldset class="form-group">		
		<div class="grid">		
			<label for="fecha">Fecha </label>
			<input type="date" name="fecha" id="fecha" class="form-control"  />
		</div>
		
	</fieldset>
	
	<input class="send-button btn btn-primary" type="submit" value="Listar pedidos" />
	
	</form>
</body>
</html>