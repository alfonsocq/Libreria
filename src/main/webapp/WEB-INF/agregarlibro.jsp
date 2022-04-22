<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Libro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h1>Nuevo Libro</h1>
		<form:form action="/libros/create" method="post" modelAttribute="libro">
			<div class="form-group">
				<form:label path="titulo">Título:</form:label>
				<form:input path="titulo" class="form-control"/>
				<form:errors path="titulo"/>
			</div>
			<div class="form-group">
				<form:label path="autor">Autor:</form:label>
				<form:input path="autor" class="form-control"/>
				<form:errors path="autor"/>
			</div>
			<div class="form-group">
				<form:label path="miopinion">Mi opinion:</form:label>
				<form:textarea path="miopinion" class="form-control"/>
				<form:errors path="miopinion"/>
			</div>
			
			<form:hidden path="usuario" value="${usuario_session.id }"/>			
			<input type="submit" value="Guardar" class="btn btn-success">
			<a href="/dashboard" class="btn btn-danger">Cancelar</a>
		</form:form>
	</div>

</body>
</html>