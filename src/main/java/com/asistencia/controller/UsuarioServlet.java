package com.asistencia.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asistencia.dao.UsuarioDAO;
import com.asistencia.model.Usuario;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    // Manejar peticiones GET (Listar, Mostrar formularios, Borrar)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("accion");
        if (action == null) action = "listar";

        switch (action) {
            case "nuevo":
                showNewForm(request, response);
                break;
            case "insertar":
                // Se maneja en doPost usualmente, pero por si acaso
                break;
            case "borrar":
                deleteUsuario(request, response);
                break;
            case "editar":
                showEditForm(request, response);
                break;
            default: // "listar"
                listUsuarios(request, response);
                break;
        }
    }

    // Manejar peticiones POST (Guardar creación o edición)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("accion");
        if ("insertar".equals(action)) {
            insertUsuario(request, response);
        } else if ("actualizar".equals(action)) {
            updateUsuario(request, response);
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private void listUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> listUsuarios = usuarioDAO.selectAll();
        request.setAttribute("listUsuarios", listUsuarios);
        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("usuarios-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuarioExistente = usuarioDAO.selectById(id);
        request.setAttribute("usuario", usuarioExistente);
        request.getRequestDispatcher("usuarios-form.jsp").forward(request, response);
    }

    private void insertUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int idRol = Integer.parseInt(request.getParameter("idRol"));

        Usuario newUser = new Usuario(rut, nombre, apellido, email, password, idRol);
        usuarioDAO.insert(newUser);
        response.sendRedirect("usuarios"); // Redirigir a la lista
    }

    private void updateUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int idRol = Integer.parseInt(request.getParameter("idRol"));

        Usuario user = new Usuario(rut, nombre, apellido, email, password, idRol);
        user.setIdUsuario(id);
        
        usuarioDAO.update(user);
        response.sendRedirect("usuarios");
    }

    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        usuarioDAO.delete(id);
        response.sendRedirect("usuarios");
    }
}