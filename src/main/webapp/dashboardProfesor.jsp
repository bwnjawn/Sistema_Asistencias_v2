<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Profesor</title>
    <link rel="stylesheet" type="text/css" href="styles/dashboard-profe.css">
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
        <h2>Bienvenido Profesor: <c:out value="${usuario.nombre}" /> <c:out value="${usuario.apellido}" /></h2>
    </div>
    <hr>
    
    <div class="navegacion">
        <ul>
            <a href="cursos?accion=mis_cursos">Ver Mis Cursos</a>
        </ul>
    </div>

    <main>

        <div class="contenedor-titulo">
            <h2>Mis Cursos Asignados</h2>
        </div>

        <div class="contenedor-tabla">
            <table class="tabla-cursos">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Asignatura</th>
                        <th>Descripción</th>
                        <th>Acciones</th>
                    </tr>
                </thead>

                <tbody>
                    <c:choose>
                        <c:when test="${not empty misCursos}">
                            <c:forEach var="curso" items="${misCursos}">
                                <tr>
                                    <td><c:out value="${curso.codigoCurso}" /></td>
                                    <td><c:out value="${curso.nombreCurso}" /></td>
                                    <td><c:out value="${curso.descripcion}" /></td>
                                    <td>
                                        <a href="asistencia?accion=tomar&idCurso=${curso.idCurso}" class="btn-accion">
                                            Tomar Asistencia
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" class="celda-vacia">
                                    No tienes cursos asignados actualmente.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
                
            </table>
        </div>
    </main>

    <!-- 
    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <th>Código</th>
                <th>Asignatura</th>
                <th>Descripción</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="curso" items="${misCursos}">
                <tr>
                    <td><c:out value="${curso.codigoCurso}" /></td>
                    <td><c:out value="${curso.nombreCurso}" /></td>
                    <td><c:out value="${curso.descripcion}" /></td>
                    <td>
                        <a href="asistencia?accion=tomar&idCurso=${curso.idCurso}" class="button">Tomar Asistencia</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty misCursos}">
                <tr>
                    <td colspan="4" style="text-align:center;">No tienes cursos asignados actualmente.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    -->
    <br><br>
    <a href="logout">Cerrar Sesión</a>
</body>
</html>
