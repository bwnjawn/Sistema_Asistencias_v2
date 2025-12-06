<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${usuario != null ? 'Editar Usuario' : 'Nuevo Usuario'}</title>
    <link rel="stylesheet" href="styles/usuario-form.css">
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
            <a href="dashboardProfesor.jsp" class="button-volver">Volver al Dashboard</a>
        </div>
    </header>

    <div class="mensaje-bienvenida">
        <h2>
        <c:if test="${usuario != null}">Editar Usuario</c:if>
        <c:if test="${usuario == null}">Nuevo Usuario</c:if>
        </h2>
    </div>
    <hr>

    <div class="contenido-ramos">
        <div class="tabla-container form-card">
            <form action="usuarios" method="post">
                
                <c:if test="${usuario != null}">
                    <input type="hidden" name="accion" value="actualizar" />
                    <input type="hidden" name="id" value="${usuario.idUsuario}" />
                </c:if>
                <c:if test="${usuario == null}">
                    <input type="hidden" name="accion" value="insertar" />
                </c:if>

                <div class="form-group">
                    <label for="rut">RUT:</label>
                    <input type="text" id="rut" name="rut" value="${usuario.rut}" required />
                </div>

                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required />
                </div>

                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="${usuario.apellido}" required />
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${usuario.email}" required />
                </div>

                <div class="form-group">
                    <label for="password">Contraseña:</label>
                    <input type="text" id="password" name="password" value="${usuario.password}" required />
                </div>

                <div class="form-group">
                    <label for="idRol">Rol (1=Admin, 2=Profe, 3=Alumno):</label>
                    <input type="number" id="idRol" name="idRol" value="${usuario.idRol}" required />
                </div>
                
                <div class="form-actions">
                    <%-- Usamos la clase del botón de guardar de asistencia --%>
                    <button type="submit" class="btn-accion btn-guardar">Guardar Usuario</button>
                </div>
            </form>
            
            <div class="acciones-footer">
                <a href="usuarios" class="link-cancelar">Volver a la lista</a>
            </div>
        </div>
    </div>
</body>
</html>