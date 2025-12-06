<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="styles/usuarios.css"> 
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
        <h2>Lista de Usuarios</h2>
    </div>
    <hr>
    
    <div class="contenido-ramos">
        <div class="tabla-container">
            <h2>Lista de Usuarios</h2>
            
            <div class="acciones-container">
                <a href="usuarios?accion=nuevo" class="btn-accion btn-marcar">➕ Agregar Nuevo Usuario</a>
                
                <a href="dashboardAdmin.jsp" class="button-volver">Volver al Dashboard</a>
            </div>
            
            <table id="tablaUsuarios" cellpadding="10">
                <thead>
                    <tr>
                        <th style="width: 5%;">ID</th>
                        <th style="width: 15%;">RUT</th>
                        <th style="width: 20%;">Nombre</th>
                        <th style="width: 20%;">Email</th>
                        <th style="width: 10%;">Rol</th>
                        <th style="width: 15%;">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${listUsuarios}">
                        <tr>
                            <td style="text-align: center;"><c:out value="${user.idUsuario}" /></td>
                            <td><c:out value="${user.rut}" /></td>
                            <td><c:out value="${user.nombre} ${user.apellido}" /></td>
                            <td><c:out value="${user.email}" /></td>
                            <td style="text-align: center;">
                                <c:choose>
                                    <c:when test="${user.idRol == 1}"><span class="presente">Admin</span></c:when>
                                    <c:when test="${user.idRol == 2}"><span class="ausente">Profesor</span></c:when>
                                    <c:otherwise>Alumno</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="acciones-tabla">
                                <a href="usuarios?accion=editar&id=${user.idUsuario}">✏️ Editar</a>
                                &nbsp;|&nbsp;
                                <a href="usuarios?accion=borrar&id=${user.idUsuario}" 
                                   onclick="return confirm('¿Seguro que deseas eliminar a ${user.nombre}?');">🗑️ Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${empty listUsuarios}">
                        <tr><td colspan="6" style="text-align:center;">No hay usuarios registrados en el sistema.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>