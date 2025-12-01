<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Alumno</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Bienvenido Alumno: ${usuario.nombre}</h2>
    <hr>
    
    <nav>
        <ul>
            <li><a href="asistencia?accion=historial">Mi Historial de Asistencia</a> (Pendiente Paso 12)</li>
        </ul>
    </nav>
    
    <br>
    <a href="logout">Cerrar Sesión</a>
</body>
</html>