<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${usuario != null ? 'Editar Usuario' : 'Nuevo Usuario'}</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>
        <c:if test="${usuario != null}">Editar Usuario</c:if>
        <c:if test="${usuario == null}">Nuevo Usuario</c:if>
    </h2>

    <form action="usuarios" method="post">
        
        <c:if test="${usuario != null}">
            <input type="hidden" name="accion" value="actualizar" />
            <input type="hidden" name="id" value="${usuario.idUsuario}" />
        </c:if>
        <c:if test="${usuario == null}">
            <input type="hidden" name="accion" value="insertar" />
        </c:if>

        <label>RUT:</label><br>
        <input type="text" name="rut" value="${usuario.rut}" required /><br><br>

        <label>Nombre:</label><br>
        <input type="text" name="nombre" value="${usuario.nombre}" required /><br><br>

        <label>Apellido:</label><br>
        <input type="text" name="apellido" value="${usuario.apellido}" required /><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" value="${usuario.email}" required /><br><br>

        <label>Contraseña:</label><br>
        <input type="text" name="password" value="${usuario.password}" required /><br><br>

        <label>Rol (1=Admin, 2=Profe, 3=Alumno):</label><br>
        <input type="number" name="idRol" value="${usuario.idRol}" required /><br><br>

        <button type="submit">Guardar</button>
    </form>
    
    <br>
    <a href="usuarios">Volver a la lista</a>
</body>
</html>