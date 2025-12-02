<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 
    CAMBIO 1: Directiva JSP al inicio.
    Esta línea es obligatoria para que el servidor sepa que este archivo contiene código Java
    y que debe usar la codificación UTF-8 para soportar tildes y ñ.
-->
<!doctype html>
<html lang="es">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sistema de Asistencia</title>
    
    <!-- 
        CAMBIO 2: Rutas Relativas.
        Como movimos los archivos a la misma carpeta (src/main/webapp), 
        ahora podemos llamar a 'style.css' directamente sin rutas complejas.
    -->
    <link rel="stylesheet" href="style.css">
    
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap'); 
    </style>
</head>
<body class="paleta">
    <header>
        <a href="#" class="logo-header"></a>
        <nav class="navegacion">
            <a href="#">Inicio</a>
            <button class="btnLogin-popup">Ingresar</button>
        </nav>
    </header>

    <!-- ================= ZONA DE MENSAJES (Lógica Java) ================= -->
    <!-- 
         CAMBIO 5: Bloques de Scriptlet Java (<% %>).
         Aquí verificamos si el Servlet nos envió algún mensaje (error o éxito).
         Si existe, inyectamos un DIV con la clase correspondiente para mostrarlo.
    -->
    
    <%-- Mensaje de ERROR (Si falla el login o registro) --%>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <%-- Mensaje de ÉXITO (Si el registro funciona) --%>
    <% if (request.getAttribute("mensaje") != null) { %>
        <div class="alert alert-success">
            <%= request.getAttribute("mensaje") %>
        </div>
        <!-- 
            CAMBIO 6: Script Automático.
            Si el registro fue exitoso, este JS abre automáticamente el popup
            para que el usuario vea el mensaje y pueda loguearse de inmediato.
        -->
        <script>
            window.onload = function() {
                document.querySelector('.wrapper').classList.add('active-popup');
            };
        </script>
    <% } %>
    <!-- =================================================================== -->

    <div class="wrapper">
        <span class="icon-close"><ion-icon name="close-circle-sharp"></ion-icon></span>

        <!-- LOGIN -->
        <div class="form-box login">
            <h2>Iniciar Sesión</h2>
            
            <!-- 
                CAMBIO 7: Configuración del Formulario (<form>).
                - action="auth": Envía los datos al Servlet 'AuthServlet'.
                - method="post": Envía los datos de forma oculta (seguridad).
            -->
            <form action="auth" method="post">
                
                <!-- Input Oculto: Le dice al Servlet que esta operación es un "login" -->
                <input type="hidden" name="accion" value="login">
                
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail-sharp"></ion-icon></span>
                    <!-- Agregado name="email" para que Java pueda leer este campo -->
                    <input type="email" name="email" required>
                    <label>Email</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed-sharp"></ion-icon></span>
                    <!-- Agregado name="password" -->
                    <input type="password" name="password" required>
                    <label>Contraseña</label>
                </div>
                
                <!-- ... resto del login sin cambios ... -->
                
                <div class="recordar-olvidar">
                    <label><input type="checkbox">Recuérdame</label>
                    <a href="#">¿Has olvidado tu contraseña?</a>
                </div>
                <button type="submit" class="btn">Ingresar</button>
                <div class="iniciar-registrar">
                    <p>¿No tienes cuenta? <a href="#" class="registrar-link">Regístrate</a></p>
                </div>
            </form>
        </div>

        <!-- REGISTRO -->
        <div class="form-box registrar">
            <h2>Registrarse</h2>
            <form action="auth" method="post">
                <!-- Input Oculto: Le dice al Servlet que esto es un "registro" -->
                <input type="hidden" name="accion" value="registro">

                <!-- 
                    CAMBIO 8: Nuevos Campos Adaptados a la Base de Datos.
                    Se agregaron inputs para RUT, Nombre y Apellido porque son
                    obligatorios en la tabla 'usuario' de Supabase.
                -->
                <div class="input-box">
                    <span class="icon"><ion-icon name="card-sharp"></ion-icon></span>
                    <input type="text" name="rut" required>
                    <label>RUT (Ej: 12345678-9)</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="person-sharp"></ion-icon></span>
                    <input type="text" name="nombre" required>
                    <label>Nombre</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="person-sharp"></ion-icon></span>
                    <input type="text" name="apellido" required>
                    <label>Apellido</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail-sharp"></ion-icon></span>
                    <input type="email" name="email" required>
                    <label>Email</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed-sharp"></ion-icon></span>
                    <input type="password" name="password" required>
                    <label>Contraseña</label>
                </div>

                <!-- ... resto del registro ... -->
                
                <div class="recordar-olvidar">
                    <label><input type="checkbox" required>Acepto los Términos y Condiciones</label>
                </div>
                <button type="submit" class="btn">Registrarse</button>
                <div class="iniciar-registrar">
                    <p>¿Ya tienes cuenta? <a href="#" class="login-link">Inicia sesión</a></p>
                </div>
            </form>
        </div>
    </div>

    <script src="script.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</body>
</html>