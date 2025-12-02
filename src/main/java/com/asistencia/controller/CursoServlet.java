package com.asistencia.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; // Importante
import com.asistencia.model.Usuario;   // Importante

import com.asistencia.dao.CursoDAO;
import com.asistencia.model.Curso;

@WebServlet("/cursos")
public class CursoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CursoDAO cursoDAO;

    public void init() {
        cursoDAO = new CursoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("accion");
        if (action == null) action = "listar";

        switch (action) {
            case "nuevo":
                request.getRequestDispatcher("cursos-form.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                Curso curso = cursoDAO.selectById(id);
                request.setAttribute("curso", curso);
                request.getRequestDispatcher("cursos-form.jsp").forward(request, response);
                break;
            case "borrar":
                int idDel = Integer.parseInt(request.getParameter("id"));
                cursoDAO.delete(idDel);
                response.sendRedirect("cursos");
                break;
            
            // --- NUEVO CASO PARA EL PROFESOR ---
            case "mis_cursos":
                HttpSession session = request.getSession();
                Usuario u = (Usuario) session.getAttribute("usuario");
                
                // Verificamos que sea profesor (Rol 2)
                if (u != null && u.getIdRol() == 2) {
                    List<Curso> misCursos = cursoDAO.listarPorProfesor(u.getIdUsuario());
                    request.setAttribute("misCursos", misCursos);
                    request.getRequestDispatcher("dashboardProfesor.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login.jsp");
                }
                break;
            // -----------------------------------

            default: // "listar" (Para el Admin)
                List<Curso> listCursos = cursoDAO.selectAll();
                request.setAttribute("listCursos", listCursos);
                request.getRequestDispatcher("cursos.jsp").forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("accion");
        
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Curso curso = new Curso(codigo, nombre, descripcion);

        if ("insertar".equals(action)) {
            cursoDAO.insert(curso);
        } else if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            curso.setIdCurso(id);
            cursoDAO.update(curso);
        }
        response.sendRedirect("cursos");
    }
}