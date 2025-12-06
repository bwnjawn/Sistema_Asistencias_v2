<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${curso != null}">Editar Curso</c:when>
            <c:otherwise>Agregar Nuevo Curso</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="styles/cursos.css"> 
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap'); 
        /* Aquí iría CSS adicional para el formulario si no está en cursos.css */
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
        <h2>Lista de Cursos</h2>
    </div>
    <hr>

    <div class="navegacion">
        <ul>
            <a href="cursos?accion=nuevo" class="button">Agregar Nuevo Curso</a>
            <a href="dashboardAdmin.jsp">Volver al Dashboard</a>
        </ul>
    </div>
    

    <div class="contenido-ramos">
        <div class="tabla-container">
            <h2>Lista de Cursos</h2>
            
            <div class="navegacion">
                <a href="cursos?accion=nuevo" class="btn-accion btn-marcar">➕ Agregar Nuevo Curso</a>
                <a href="dashboardAdmin.jsp" class="button-volver">Volver al Dashboard</a>
            </div>
            
            <table id="tablaCursos" cellpadding="10">
                <thead>
                    <tr>
                        <th style="width: 5%;">ID</th>
                        <th style="width: 15%;">Código</th>
                        <th style="width: 25%;">Nombre</th>
                        <th style="width: 40%;">Descripción</th>
                        <th style="width: 15%;">Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="curso" items="${listCursos}">
                        <tr>
                            <td style="text-align: center;"><c:out value="${curso.idCurso}" /></td>
                            <td><c:out value="${curso.codigoCurso}" /></td>
                            <td><c:out value="${curso.nombreCurso}" /></td>
                            <td><c:out value="${curso.descripcion}" /></td>
                            <td class="acciones-tabla">
                                <a href="cursos?accion=editar&id=${curso.idCurso}">✏️ Editar</a>
                                &nbsp;|&nbsp;
                                <a href="cursos?accion=borrar&id=${curso.idCurso}" 
                                   onclick="return confirm('¿Eliminar el curso ${curso.codigoCurso}?');">🗑️ Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty listCursos}">
                        <tr><td colspan="5" style="text-align:center;">No hay cursos registrados.</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>