<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tomar Asistencia</title>
    <link rel="stylesheet" href="styles/Asistencia-Profe.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap'); 
    </style>

    <script>
        function seleccionarTodos(marcar) {
            var checkboxes = document.querySelectorAll('input[type="checkbox"][name^="presente_"]');
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = marcar;
            }
        }
    </script>
</head>

<body class="paleta">
    <header>
        <div class="grupo-izquierda">
            <a href="#" class="logo-header"></a>
        </div>

        <div class="grupo-derecha">
            <a href="dashboardProfesor.jsp" class="button-volver">Volver al Dashboard</a>
        </div>
    </header>

    <div class="mensaje-bienvenida">
        <h2>Tomar Asistencia</h2>
            Fecha: <strong><fmt:formatDate value="${fechaHoy}" pattern="dd/MM/yyyy" /></strong>
        </p>
    </div>
    <hr>

    <div class="contenido-ramos">
        <div class="acciones-container">
            <button type="button" onclick="seleccionarTodos(true)" class="btn-accion btn-marcar">Marcar Todos</button>
            <button type="button" onclick="seleccionarTodos(false)" class="btn-accion btn-desmarcar">Desmarcar Todos</button>
        </div>

        <div class="tabla-container">
            <form action="asistencia" method="post">
                <input type="hidden" name="idCurso" value="${idCurso}">

                <table id="tablaAsistencia" cellpadding="10">
                    <thead>
                        <tr>
                            <th>Nombre Alumno</th>
                            <th style="text-align: center;">Asistencia</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="alumno" items="${listaAlumnos}">
                            <tr>
                                <td>
                                    <span style="font-weight: 500; color: var(--color-negro);">
                                        <c:out value="${alumno.nombre}" /> <c:out value="${alumno.apellido}" />
                                    </span>
                                </td>
                                <td style="text-align: center;">
                                    <input type="checkbox" name="presente_${alumno.idUsuario}" value="true">
                                </td>
                            </tr>
                        </c:forEach>
                        
                        <c:if test="${empty listaAlumnos}">
                            <tr>
                                <td colspan="2" style="text-align:center; color: #f44336;">
                                    No hay alumnos inscritos en este curso.
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>

                <div class="form-actions" style="margin-top: 20px;">
                    <c:if test="${not empty listaAlumnos}">
                        <button type="submit" class="btn-accion btn-guardar">Guardar Asistencia</button>
                    </c:if>
                </div>
            </form>
        </div>
        <br>
    </div>
</body>
</html>
