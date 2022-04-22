<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mostrar Libro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">				
				<h1>${libro.titulo}</h1>
				<p>${libro.usuario.first_name} ha leido ${libro.titulo} escrito por ${libro.autor}.</p>
						
			</div>
			
			<h3>Opinion</h3>
				
			<p>${libro.miopinion}</p>
			
			
						
			
			
		</div>
		<a href="/dashboard" class="btn btn-primary">Volver</a>
	</div>
</body>
</html>