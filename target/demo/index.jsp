<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bienvenido</title>
    <link rel="stylesheet" href="style.css">
</head>
<body class="paleta" style="color: white; text-align: center; padding-top: 100px;">
    
    <% 
        // Verificamos si hay usuario en sesión
        com.asistencia.model.Usuario u = (com.asistencia.model.Usuario) session.getAttribute("usuario");
        if (u != null) {
    %>
        <h1>¡Bienvenido, <%= u.getNombre() %> <%= u.getApellido() %>!</h1>
        <p>Has iniciado sesión correctamente.</p>
        <p>Tu Rol es: <%= u.getIdRol() %></p>
        <br>
        <a href="login.jsp" style="color: yellow;">Cerrar Sesión (Volver)</a>
    <% } else { %>
        <h1>No has iniciado sesión</h1>
        <a href="login.jsp" style="color: yellow;">Ir al Login</a>
    <% } %>

</body>
</html>