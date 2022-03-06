<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Novedades</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href="/">Librería</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav">
		    
		    <sec:authorize access="hasAuthority('ROL_ADMON')">
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/usuarios">Usuarios</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/perfiles">Perfiles</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/clientes">Clientes</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/admon/altaTema">Nuevo tema</a>
		      </li>
		     </sec:authorize>
		      
		      <li class="nav-item active">
		        <a class="nav-link" href="/cliente/tema">Buscar por tema</a>
		      </li>
		      <li class="nav-item active">
		        <a class="nav-link" href="/cliente/buscar">Buscar títulos</a>
		      </li>
		      
		      <sec:authorize access="hasAuthority('ROL_CLIENTE')">		      
			      <li class="nav-item active">
			        <a class="nav-link" href="/cliente/misDatos">Mis datos</a>
			      </li>
			      <li class="nav-item active">
			        <a class="nav-link" href="/cliente/verCarrito">Mi carrito</a>
			      </li>			      
			  </sec:authorize>
			  
			  <sec:authorize access="!isAuthenticated()">
			  <li class="nav-item"><a class="nav-link"
										href="/login">Iniciar Sesión</a></li>
			  <li class="nav-item"><a class="nav-link"
									href="/registro">Registrarse</a></li>			  
			  </sec:authorize>
			  
			  <sec:authorize access="isAuthenticated()">
				  <li class="nav-item"><a class="nav-link"
							href="/logout">Cerrar Sesión</a></li>
			  </sec:authorize>
		    </ul>
		  </div>
		</nav>
		
		
		 <sec:authorize access="!isAuthenticated()">
			  <p>No has iniciado sesión, <a href="/login">iníciala</a> o bien <a href="/registro">regístrate antes</a> si no tienes cuenta</p>		  
		</sec:authorize>
		
		
		 <sec:authorize access="hasAuthority('ROL_CLIENTE')">		      
			      <p>Hola <sec:authentication property="name"/>!</p>			      
		</sec:authorize>
		
		<sec:authorize access="hasAuthority('ROL_ADMON')">
			<p>Bienvenido <sec:authentication property="name"/>, tienes acceso de administrador</p>
		</sec:authorize>
		
		
		
		
		
	</body>
</html>