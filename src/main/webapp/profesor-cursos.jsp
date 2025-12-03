<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Cursos</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Mis Cursos Asignados</h2>
    <h3>Profesor: ${usuario.nombre} ${usuario.apellido}</h3>
    
    <a href="dashboardProfesor.jsp">Volver al Dashboard</a>
    <br><br>

    <table border="1" cellpadding="10">
        <thead>
            <tr>
                <th>Código</th>
                <th>Nombre del Curso</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty listaCursos}">
                <tr><td colspan="3">No tienes cursos asignados.</td></tr>
            </c:if>

            <c:forEach var="c" items="${listaCursos}">
                <tr>
                    <td>${c.codigo}</td>
                    <td>${c.nombre}</td>
                    <td>
                        <a href="asistencia?accion=tomar&idCurso=${c.idCurso}" class="button">Tomar Asistencia</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>