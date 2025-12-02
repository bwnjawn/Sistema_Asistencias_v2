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

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion"); 

        if ("login".equals(accion)) {
            procesarLogin(request, response);
        } else if ("registro".equals(accion)) {
            procesarRegistro(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void procesarLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        System.out.println("--- INTENTO DE LOGIN ---");
        System.out.println("Email recibido: " + email);

        Usuario usuario = usuarioDAO.validarLogin(email, pass);

        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario.getNombre());
            System.out.println("ROL DETECTADO ID: " + usuario.getIdRol());

            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario); 

            switch (usuario.getIdRol()) {
                case 1: 
                    System.out.println("Redirigiendo a Dashboard ADMIN...");
                    response.sendRedirect("dashboardAdmin.jsp");
                    break;
                case 2: 
                    System.out.println("Redirigiendo a Dashboard PROFESOR...");
                    // CAMBIO AQUÍ: Redirigimos al Servlet para cargar los cursos antes de mostrar la vista
                    response.sendRedirect("cursos?accion=mis_cursos");
                    break;
                case 3: 
                    System.out.println("Redirigiendo a Dashboard ALUMNO...");
                    response.sendRedirect("dashboardAlumno.jsp");
                    break;
                default:
                    System.out.println("ROL DESCONOCIDO (" + usuario.getIdRol() + "). Redirigiendo a login.");
                    response.sendRedirect("login.jsp");
                    break;
            }
            
        } else {
            System.out.println("Usuario NO encontrado o contraseña incorrecta.");
            request.setAttribute("error", "Correo o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void procesarRegistro(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        
        // Por defecto rol 3 (Alumno)
        Usuario nuevoUsuario = new Usuario(rut, nombre, apellido, email, pass, 3);
        boolean registrado = usuarioDAO.insert(nuevoUsuario);

        if (registrado) {
            request.setAttribute("mensaje", "¡Registro exitoso! Ahora puedes iniciar sesión.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Error al registrar.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}