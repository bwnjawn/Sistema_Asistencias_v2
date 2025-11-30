<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="es">

<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sistema de Asistencia</title>
    <link rel="stylesheet" href="style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap');
        /* Ajuste pequeño para que quepan más campos en el registro */
        .wrapper.active { height: 620px; } 
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

    <!-- Mensaje de Error (Lógica Java) -->
    <% if (request.getAttribute("error") != null) { %>
        <div style="position: absolute; top: 80px; background: #ffdddd; color: red; padding: 10px; border-radius: 5px; z-index: 1000;">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <div class="wrapper">
        <span class="icon-close"><ion-icon name="close-circle-sharp"></ion-icon></span>

        <!-- ================= LOGIN ================= -->
        <div class="form-box login">
            <h2>Iniciar Sesión</h2>
            <form action="auth" method="post">
                <input type="hidden" name="accion" value="login">
                
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

                <button type="submit" class="btn">Ingresar</button>

                <div class="iniciar-registrar">
                    <p>¿No tienes cuenta? <a href="#" class="registrar-link">Regístrate</a></p>
                </div>
            </form>
        </div>

        <!-- ================= REGISTRO (Adaptado a BD) ================= -->
        <div class="form-box registrar">
            <h2>Registrarse</h2>
            <form action="auth" method="post">
                <input type="hidden" name="accion" value="registro">

                <!-- Campo RUT (Nuevo) -->
                <div class="input-box">
                    <span class="icon"><ion-icon name="card-sharp"></ion-icon></span>
                    <input type="text" name="rut" required>
                    <label>RUT (Ej: 12345678-9)</label>
                </div>

                <!-- Campo Nombre -->
                <div class="input-box">
                    <span class="icon"><ion-icon name="person-sharp"></ion-icon></span>
                    <input type="text" name="nombre" required>
                    <label>Nombre</label>
                </div>

                <!-- Campo Apellido (Nuevo) -->
                <div class="input-box">
                    <span class="icon"><ion-icon name="person-sharp"></ion-icon></span>
                    <input type="text" name="apellido" required>
                    <label>Apellido</label>
                </div>

                <!-- Campo Email -->
                <div class="input-box">
                    <span class="icon"><ion-icon name="mail-sharp"></ion-icon></span>
                    <input type="email" name="email" required>
                    <label>Email</label>
                </div>
                
                <!-- Campo Contraseña -->
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed-sharp"></ion-icon></span>
                    <input type="password" name="password" required>
                    <label>Contraseña</label>
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