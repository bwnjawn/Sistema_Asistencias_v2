<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Ramos</title>
    <link rel="stylesheet" href="style.css">
    <style>
        /* Estilos exclusivos para la barra de estadísticas */
        .bueno { color: green; font-weight: bold; }
        .malo { color: red; font-weight: bold; }
        .limite { color: orange; font-weight: bold; }
        
        .barra-fondo { 
            background-color: #e0e0e0; 
            width: 100%; 
            max-width: 150px; 
            height: 10px; 
            display: inline-block; 
            border-radius: 5px; 
            overflow: hidden;
            vertical-align: middle;
            margin-left: 10px;
        }
        .barra-progreso { 
            height: 100%; 
            border-radius: 5px; 
            width: 0%; /* Ancho inicial 0, se anima con JS */
            transition: width 0.5s ease-in-out; /* Animación suave */
        }
    </style>
</head>
<body>
    <h2>Mis Ramos Inscritos</h2>
    <a href="dashboardAlumno.jsp">Volver al Inicio</a>
    <br><br>

    <!-- Buscador en tiempo real -->
    <input type="text" id="buscador" onkeyup="filtrarTabla()" placeholder="Buscar por nombre o código..." style="padding: 10px; width: 300px; margin-bottom: 10px;">

    <table id="tablaCursos" border="1" cellpadding="10" style="width: 90%;">
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
                    
                    <!-- Lógica Visual del Reporte de Asistencia -->
                    <td>
                        <c:choose>
                            <c:when test="${curso.porcentajeAsistencia >= 80}">
                                <!-- Caso APROBADO (Verde) -->
                                <span class="bueno">APROBADO (<c:out value="${curso.porcentajeAsistencia}"/>%)</span>
                                <div class="barra-fondo">
                                    <div class="barra-progreso" data-ancho="${curso.porcentajeAsistencia}" style="background-color: #4CAF50;"></div>
                                </div>
                            </c:when>
                            <c:when test="${curso.porcentajeAsistencia >= 75}">
                                <!-- Caso AL LÍMITE (Naranja) -->
                                <span class="limite">AL LÍMITE (<c:out value="${curso.porcentajeAsistencia}"/>%)</span>
                                <div class="barra-fondo">
                                    <div class="barra-progreso" data-ancho="${curso.porcentajeAsistencia}" style="background-color: orange;"></div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <!-- Caso REPROBANDO (Rojo) -->
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

    <!-- SCRIPT MOVIDO AL FINAL PARA EVITAR CONFLICTOS DE PARSEO -->
    <script>
        // Función para el buscador
        function filtrarTabla() {
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
</body>
</html>