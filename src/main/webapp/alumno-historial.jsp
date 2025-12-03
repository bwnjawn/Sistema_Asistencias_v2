<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mi Historial</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .presente { color: green; font-weight: bold; }
        .ausente { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>Mi Historial de Asistencia</h2>
    <h3>Alumno: ${usuario.nombre} ${usuario.apellido}</h3>
    
    <a href="dashboardAlumno.jsp">Volver al Dashboard</a>
    <br><br>

    <table border="1" cellpadding="10">
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Bloque</th>
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty listaAsistencia}">
                <tr><td colspan="3">No hay registros de asistencia aún.</td></tr>
            </c:if>

            <c:forEach var="a" items="${listaAsistencia}">
                <tr>
                    <td>${a.fecha}</td>
                    <td>${a.bloque}</td> <td>
                        <c:choose>
                            <c:when test="${a.estado == 'Presente'}">
                                <span class="presente">PRESENTE</span>
                            </c:when>
                            <c:otherwise>
                                <span class="ausente">AUSENTE</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>