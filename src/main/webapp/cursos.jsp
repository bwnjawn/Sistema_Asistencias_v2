<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Cursos</title>
    <link rel="stylesheet" href="style.css"> 
</head>
<body>
    <h2>Lista de Cursos</h2>
    
    <a href="cursos?accion=nuevo" class="button">Agregar Nuevo Curso</a>
    <a href="dashboardAdmin.jsp">Volver al Dashboard</a>
    <br><br>

    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <th>ID</th>
                <th>Código</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="curso" items="${listCursos}">
                <tr>
                    <td><c:out value="${curso.idCurso}" /></td>
                    <td><c:out value="${curso.codigoCurso}" /></td>
                    <td><c:out value="${curso.nombreCurso}" /></td>
                    <td><c:out value="${curso.descripcion}" /></td>
                    <td>
                        <a href="cursos?accion=editar&id=${curso.idCurso}">Editar</a>
                        &nbsp;|&nbsp;
                        <a href="cursos?accion=borrar&id=${curso.idCurso}" 
                           onclick="return confirm('¿Eliminar este curso?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>