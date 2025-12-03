<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Alumno</title>
    <link rel="stylesheet" type="text/css" href="styles/dasboard.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap'); 
    </style>
</head>
<body class="root">

    <h2>Bienvenido Alumno: <c:out value="${usuario.nombre}" /></h2>
    <hr>
    
    <nav>
        <ul>
            <li><a href="asistencia?accion=mis_ramos">Ver Mis Ramos y Asistencia</a></li>
        </ul>
    </nav>
    
    <br><br>
    <a href="logout">Cerrar Sesión</a>
</body>
</html>