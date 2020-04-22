<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Excluir Comentario</title>
</head>
<body>
    <p><label>ID: </label> ${comentario.id]</p>
    <p><label>Corpo: </label> ${comentario.corpo]</p>
    <p><label>Usuario: </label> ${comentario.usuario.nome]</p>
    <p><label>Coluna: </label> ${comentario.coluna.titulo]</p>
</body>
</html>