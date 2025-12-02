<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${curso != null ? 'Editar Curso' : 'Nuevo Curso'}</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>${curso != null ? 'Editar Curso' : 'Nuevo Curso'}</h2>

    <form action="cursos" method="post">
        
        <c:if test="${curso != null}">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="id" value="${curso.idCurso}">
        </c:if>
        <c:if test="${curso == null}">
            <input type="hidden" name="accion" value="insertar">
        </c:if>

        <label>Código del Curso (Ej: PGY4121):</label><br>
        <input type="text" name="codigo" value="<c:out value='${curso.codigoCurso}' />" required><br><br>

        <label>Nombre del Curso:</label><br>
        <input type="text" name="nombre" value="<c:out value='${curso.nombreCurso}' />" required><br><br>

        <label>Descripción:</label><br>
        <textarea name="descripcion" rows="4" cols="50"><c:out value='${curso.descripcion}' /></textarea><br><br>

        <input type="submit" value="Guardar Curso" class="button">
    </form>
    
    <br>
    <a href="cursos">Volver a la lista</a>
</body>
</html>