<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Alumno</title>
    <link rel="stylesheet" type="text/css" href="styles/dashboard-alumno.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap'); 
    </style>
</head>

<body class="paleta">
    <header>
        <div class="grupo-izquierda">
            <a href="#" class="logo-header"></a>
        </div>

        <div class="grupo-derecha">
            <a href="logout" class="btn-logout">Cerrar Sesión</a>
        </div>
    </header>

    <div class="mensaje-bienvenida">
        <h2>Bienvenido Alumno: <c:out value="${usuario.nombre}" /></h2>
    </div>
    <hr>
    
    <div class="navegacion">
        <ul>
            <a href="asistencia?accion=mis_ramos">Ver Mis Ramos y Asistencia</a>
        </ul>
    </div>

</body>
</html>