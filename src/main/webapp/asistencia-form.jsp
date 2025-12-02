<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tomar Asistencia</title>
    <link rel="stylesheet" href="style.css">
    <script>
        function seleccionarTodos(marcar) {
            var checkboxes = document.querySelectorAll('input[type="checkbox"][name^="presente_"]');
            for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = marcar;
            }
        }
    </script>
</head>
<body>
    <h2>Tomar Asistencia</h2>
    <p>Fecha de la clase: <strong><fmt:formatDate value="${fechaHoy}" pattern="dd/MM/yyyy" /></strong></p>
    
    <div style="margin-bottom: 10px;">
        <button type="button" onclick="seleccionarTodos(true)" class="button" style="background-color: #4CAF50;">Marcar Todos</button>
        <button type="button" onclick="seleccionarTodos(false)" class="button" style="background-color: #f44336;">Desmarcar Todos</button>
    </div>

    <form action="asistencia" method="post">
        <input type="hidden" name="idCurso" value="${idCurso}">
        
        <table border="1" cellpadding="10" style="width: 60%;">
            <thead>
                <tr>
                    <th>Nombre Alumno</th>
                    <th>¿Presente?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alumno" items="${listaAlumnos}">
                    <tr>
                        <td>
                            <c:out value="${alumno.nombre}" /> 
                            <c:out value="${alumno.apellido}" />
                        </td>
                        <td style="text-align: center;">
                            <input type="checkbox" name="presente_${alumno.idUsuario}" value="true">
                        </td>
                    </tr>
                </c:forEach>
                
                <c:if test="${empty listaAlumnos}">
                    <tr><td colspan="2" style="color:red;">No hay alumnos inscritos en este curso (Tabla alumno_curso vacía).</td></tr>
                </c:if>
            </tbody>
        </table>
        
        <br>
        <c:if test="${not empty listaAlumnos}">
            <input type="submit" value="Guardar Asistencia" class="button">
        </c:if>
        <a href="dashboardProfesor.jsp" style="margin-left: 20px;">Cancelar</a>
    </form>
</body>
</html>