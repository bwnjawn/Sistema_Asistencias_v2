<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="style.css"> 
</head>
<body>
    <h2>Lista de Usuarios</h2>
    
    <a href="usuarios?accion=nuevo" class="button">Agregar Nuevo Usuario</a>
    <a href="dashboardAdmin.jsp">Volver al Dashboard</a>
    <br><br>

    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <th>ID</th>
                <th>RUT</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Email</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${listUsuarios}">
                <tr>
                    <td><c:out value="${user.idUsuario}" /></td>
                    <td><c:out value="${user.rut}" /></td>
                    <td><c:out value="${user.nombre}" /></td>
                    <td><c:out value="${user.apellido}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.idRol == 1}">Admin</c:when>
                            <c:when test="${user.idRol == 2}">Profesor</c:when>
                            <c:otherwise>Alumno</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="usuarios?accion=editar&id=${user.idUsuario}">Editar</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="usuarios?accion=borrar&id=${user.idUsuario}" 
                           onclick="return confirm('¿Seguro que deseas eliminar este usuario?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>