<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle Asistencia</title>
    <link rel="stylesheet" href="styles/AlumnoHistorial.css">
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
            <a href="dashboardAlumno.jsp" class="button-volver">Volver a mis ramos</a>
        </div>
    </header>

    <div class="mensaje-bienvenida">
        <h2> Asistencia: <c:out value="${nombreCurso}"/></h2>
    </div>
    <hr>

    <div class="contenido-ramos">
        <div class="tabla-container">
            <h2>Detalle de Clases</h2>
            <div class="filtros-container">
                <label for="filtroFecha" style="color: var(--color-primario); font-weight: 500;">Filtrar por fecha:</label>
                <input type="date" id="filtroFecha" onchange="filtrarFecha()">
                <button onclick="document.getElementById('filtroFecha').value=''; filtrarFecha();">Limpiar Filtro</button>
            </div>
            
            <table id="tablaAsistencia" cellpadding="10"><thead>
                <tr>
                    <th>Fecha</th>
                    <th>Estado</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="item" items="${historial}">
                    <tr>
                        <td><fmt:formatDate value="${item.fecha}" pattern="dd/MM/yyyy" /></td>

                        <td>
                            <c:choose>
                                <c:when test="${item.estado == 'Presente'}">
                                    <span class="presente">PRESENTE</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="ausente">AUSENTE</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty historial}">
                    <tr><td colspan="2" style="text-align:center;">No hay registros de asistencia para este curso.</td></tr>
                </c:if>
            </tbody>
        </table>
    </div>

    <script>
    function filtrarFecha() {
        var input = document.getElementById("filtroFecha").value; // Formato yyyy-mm-dd
        // // Convertimos al formato de la tabla dd/mm/yyyy
        if(input) {
            var partes = input.split("-");
            // Usa parseInt para asegurar que el día y mes no tengan ceros al inicio (opcional pero más robusto)
            var dia = partes[2];
            var mes = partes[1];
            var anio = partes[0];
            // Asegúrate de que el formato coincida EXACTAMENTE con JSTL (dd/MM/yyyy)
            var fechaBuscada = dia + "/" + mes + "/" + anio;
            
            var tabla = document.getElementById("tablaAsistencia");
            var tr = tabla.getElementsByTagName("tr");
            
            for (var i = 1; i < tr.length; i++) {
                var tdFecha = tr[i].getElementsByTagName("td")[0];
                if (tdFecha) {
                    var textoFecha = tdFecha.textContent || tdFecha.innerText;
                    if (textoFecha === fechaBuscada) {

                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        } else {
            var tabla = document.getElementById("tablaAsistencia");
            var tr = tabla.getElementsByTagName("tr");
            for (var i = 1; i < tr.length; i++) {
                tr[i].style.display = "";
            }
        }
    }
    </script>
</body>
</html>