<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Administrador</title>
    <link rel="stylesheet" type="text/css" href="styles/dasboard.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap'); 
    </style>
</head>

<body class="paleta">
    <header>
        <div class="grupo-izquierda">
            <button onclick="history.back()" class="btn-back">
                <ion-icon name="arrow-back-circle-outline"></ion-icon>
            </button> 
            <a href="#" class="logo-header"></a>
        </div>
    </header>

        <div class="mensaje-bienvenida">
            <h2>Bienvenido Administrador: ${usuario.nombre}</h2>
        </div>
        <a href="logout" class="logout-link">Cerrar Sesión</a>
    </div>
    
    <hr>

    <div class="navegacion">
        <ul>
            <li><a href="usuarios">Gestionar Usuarios</a>
            <li><a href="cursos">Gestionar Cursos</a> <li><a href="cursos">Gestionar Cursos</a></li></li>
        </ul>
    </div>


</body>
</html>