<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bienvenido</title>
    <link rel="stylesheet" href="styles/index-style.css">
</head>

<body class="paleta">

<div class="container">

    <% 
        com.asistencia.model.Usuario u = 
            (com.asistencia.model.Usuario) session.getAttribute("usuario");
        
        if (u != null) {
    %>

        <h1 class="title">Bienvenido</h1>

        <div class="card">
            <p class="username"><%= u.getNombre() %> <%= u.getApellido() %></p>
            <p class="text">Has iniciado sesión correctamente.</p>
            <p class="text">Rol: <strong><%= u.getIdRol() %></strong></p>

            <a class="btn" href="login.jsp">Cerrar sesión</a>
        </div>

    <% } else { %>

        <h1 class="title">No has iniciado sesión</h1>
        <a class="btn" href="login.jsp">Ir al Login</a>

    <% } %>

</div>

</body>
</html>
