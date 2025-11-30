package com.asistencia.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asistencia.dao.UsuarioDAO;
import com.asistencia.model.Usuario;

// Esta anotación define la URL que pusiste en el HTML: action="auth"
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        // Inicializamos el DAO una sola vez
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Identificar qué formulario se envió (Login o Registro)
        String accion = request.getParameter("accion"); // "login" o "registro"

        if ("login".equals(accion)) {
            procesarLogin(request, response);
        } else if ("registro".equals(accion)) {
            procesarRegistro(request, response);
        } else {
            // Si no hay acción clara, volver al inicio
            response.sendRedirect("login.jsp");
        }
    }

    // --- LÓGICA DE LOGIN ---
    private void procesarLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        // Usamos el DAO para preguntar a la Base de Datos
        Usuario usuario = usuarioDAO.validarLogin(email, pass);

        if (usuario != null) {
            // ¡ÉXITO! Creamos la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario); // Guardamos al usuario en la memoria del servidor

            // Redirigimos según el rol (Por ahora, como no tenemos dashboards, mandamos a una página de éxito temporal)
            // En el futuro: switch(usuario.getIdRol()) ...
            response.sendRedirect("index.jsp"); // Redirige a la página de bienvenida temporal
        } else {
            // ¡ERROR! Credenciales malas
            request.setAttribute("error", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // --- LÓGICA DE REGISTRO ---
    private void procesarRegistro(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Recibimos todos los campos del formulario de registro
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        
        // Creamos el objeto (Rol 3 = Alumno por defecto)
        Usuario nuevoUsuario = new Usuario(rut, nombre, apellido, email, pass, 3);

        // Guardamos en BD
        boolean registrado = usuarioDAO.insert(nuevoUsuario);

        if (registrado) {
            // Si se guardó, mandamos al login con mensaje de éxito
            request.setAttribute("mensaje", "¡Registro exitoso! Ahora puedes iniciar sesión.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Si falló (ej: RUT duplicado), volvemos con error
            request.setAttribute("error", "Error al registrar. El RUT o Email ya existen.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}