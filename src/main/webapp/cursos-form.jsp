<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${curso != null ? 'Editar Curso' : 'Nuevo Curso'}</title>
    <%-- Enlaza al CSS de Asistencia para usar su estilo (Asistencia-Profe.css) --%>
    <link rel="stylesheet" href="styles/cursos-form.css"> 
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
        </div>
    </header>

    <div class="mensaje-bienvenida">
        <h2>
        <c:if test="${curso != null}">Editar Curso</c:if>
        <c:if test="${curso == null}">Nuevo Curso</c:if>
        </h2>
    </div>
    <hr>
    
    <div class="contenido-ramos">
        <div class="tabla-container form-card"> 
            <form action="cursos" method="post">
                
                <c:if test="${curso != null}">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="id" value="${curso.idCurso}">
                </c:if>
                <c:if test="${curso == null}">
                    <input type="hidden" name="accion" value="insertar">
                </c:if>

                <div class="form-group">
                    <label for="codigo">Código del Curso (Ej: PGY4121):</label>
                    <input type="text" id="codigo" name="codigo" value="<c:out value='${curso.codigoCurso}' />" required>
                </div>

                <div class="form-group">
                    <label for="nombre">Nombre del Curso:</label>
                    <input type="text" id="nombre" name="nombre" value="<c:out value='${curso.nombreCurso}' />" required>
                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <%-- Se ajusta el estilo del textarea con la misma clase que los inputs --%>
                    <textarea id="descripcion" name="descripcion" rows="4"><c:out value='${curso.descripcion}' /></textarea>
                </div>
                
                <div class="form-actions">
                    <%-- Usamos las clases de botón de asistencia: btn-accion btn-guardar --%>
                    <button type="submit" class="btn-accion btn-guardar">Guardar Curso</button>
                </div>
            </form>
            
            <div class="acciones-footer">
                <a href="cursos" class="link-cancelar">Volver a la lista de Cursos</a>
            </div>
        </div>
    </div>
    
</body>
</html>