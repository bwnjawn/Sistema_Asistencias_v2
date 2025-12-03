<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tomar Asistencia</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Registro de Asistencia</h2>
    
    <form action="asistencia" method="post">
        <input type="hidden" name="accion" value="registrar">
        <input type="hidden" name="idCurso" value="${idCurso}">

        <div style="margin-bottom: 15px; border: 1px solid #ccc; padding: 10px;">
            <label><strong>Fecha:</strong></label>
            <input type="date" name="fecha" value="${fechaHoy}" required>
            
            &nbsp;&nbsp;
            
            <label><strong>Bloque Horario:</strong></label>
            <select name="bloque" required>
                <option value="Bloque 1 (Mañana)">Bloque 1 (Mañana)</option>
                <option value="Bloque 2 (Tarde)">Bloque 2 (Tarde)</option>
                <option value="Vespertino">Vespertino</option>
            </select>
        </div>

        <table border="1" cellpadding="8" style="width: 100%;">
            <thead>
                <tr>
                    <th>RUT</th>
                    <th>Alumno</th>
                    <th>¿Presente?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alumno" items="${alumnos}">
                    <tr>
                        <td>${alumno.rut}</td>
                        <td>${alumno.nombre} ${alumno.apellido}</td>
                        <td style="text-align: center;">
                            <input type="checkbox" name="presente_${alumno.idAlumnoCurso}" value="true">
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <br>
        <button type="submit" class="button">Guardar Asistencia</button>
        <a href="asistencia?accion=listarCursos">Cancelar</a>
    </form>
</body>
</html>