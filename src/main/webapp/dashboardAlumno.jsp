<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Alumno</title>
    <link rel="stylesheet" type="text/css" href="styles/dashboard-alumno.css">
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
        <h2>Bienvenido Alumno: <c:out value="${usuario.nombre}" /></h2>
    </div>
    <hr>
    
    <div class="navegacion">
        <ul>
            <a href="asistencia?accion=mis_ramos">Ver Mis Ramos y Asistencia</a>
        </ul>
    </div>

    <div class="contenido-ramos">
    <c:if test="${mostrarRamos}">
    
        <div class="tabla-container">
            <h2>Mis Ramos Inscritos</h2>

            <input type="text" id="buscador" onkeyup="filtrarTabla()" placeholder="Buscar por nombre o código..." style="padding: 10px; width: 300px; margin-bottom: 10px;">

            <table id="tablaCursos" border="1" cellpadding="10" style="width: 90%; margin: 0 auto;">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Asignatura</th>
                        <th>% Asistencia</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="curso" items="${misCursos}">
                        <tr>
                            <td><c:out value="${curso.codigoCurso}" /></td>
                            <td><c:out value="${curso.nombreCurso}" /></td>
                            
                            <td>
                                <c:choose>
                                    <c:when test="${curso.porcentajeAsistencia >= 80}">
                                        <span class="bueno">APROBADO (<c:out value="${curso.porcentajeAsistencia}"/>%)</span>
                                        <div class="barra-fondo">
                                            <div class="barra-progreso" data-ancho="${curso.porcentajeAsistencia}" style="background-color: #4CAF50;"></div>
                                        </div>
                                    </c:when>
                                    <c:when test="${curso.porcentajeAsistencia >= 75}">
                                        <span class="limite">AL LÍMITE (<c:out value="${curso.porcentajeAsistencia}"/>%)</span>
                                        <div class="barra-fondo">
                                            <div class="barra-progreso" data-ancho="${curso.porcentajeAsistencia}" style="background-color: orange;"></div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="malo">REPROBANDO (<c:out value="${curso.porcentajeAsistencia}"/>%)</span>
                                        <div class="barra-fondo">
                                            <div class="barra-progreso" data-ancho="${curso.porcentajeAsistencia}" style="background-color: #f44336;"></div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            
                            <td>
                                <a href="asistencia?accion=ver_detalle&idCurso=${curso.idCurso}" class="button">Ver Detalle</a>
                            </td>
                        </tr>
                    </c:forEach>
                    
                    <c:if test="${empty misCursos}">
                        <tr><td colspan="4" style="text-align:center;">No tienes ramos inscritos.</td></tr>
                    </c:if>
                </tbody>
            </table>

            <script>
                // Función para el buscador
                function filtrarTabla() {
                    // ... (pegar el código de filtrarTabla aquí) ...
                    var input = document.getElementById("buscador");
                    var filtro = input.value.toUpperCase();
                    var tabla = document.getElementById("tablaCursos");
                    var tr = tabla.getElementsByTagName("tr");

                    for (var i = 1; i < tr.length; i++) {
                        var tdCodigo = tr[i].getElementsByTagName("td")[0];
                        var tdNombre = tr[i].getElementsByTagName("td")[1];
                        
                        if (tdNombre || tdCodigo) {
                            var textoCodigo = tdCodigo.textContent || tdCodigo.innerText;
                            var textoNombre = tdNombre.textContent || tdNombre.innerText;
                            
                            if (textoNombre.toUpperCase().indexOf(filtro) > -1 || textoCodigo.toUpperCase().indexOf(filtro) > -1) {
                                tr[i].style.display = "";
                            } else {
                                tr[i].style.display = "none";
                            }
                        }      
                    }
                }

                // Aplicar anchos después de cargar la página
                document.addEventListener("DOMContentLoaded", function() {
                    var barras = document.querySelectorAll('.barra-progreso');
                    for (var i = 0; i < barras.length; i++) {
                        var barra = barras[i];
                        var porcentaje = barra.getAttribute('data-ancho');
                        if(porcentaje) {
                            barra.style.width = porcentaje + '%';
                        }
                    }
                });
            </script>
        </div>
        </c:if>
    </div>

</body>
</html>