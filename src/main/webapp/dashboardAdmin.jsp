<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Administrador</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Bienvenido Administrador: ${usuario.nombre}</h2>
    <hr>
    
    <nav>
        <ul>
            <li><a href="usuarios">Gestionar Usuarios</a>
            <li><a href="cursos">Gestionar Cursos</a> <li><a href="cursos">Gestionar Cursos</a></li></li>
        </ul>
    </nav>
    
    <br>
    <a href="logout">Cerrar Sesión</a>
</body>
</html>