package com.asistencia.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asistencia.dao.AlumnoDAO;
import com.asistencia.dao.AlumnoDAO.AlumnoInscrito; 
import com.asistencia.dao.AsistenciaDAO;
import com.asistencia.dao.CursoDAO;
import com.asistencia.model.Asistencia;
import com.asistencia.model.Curso;
import com.asistencia.model.Usuario;

@WebServlet("/asistencia")
public class AsistenciaServlet extends HttpServlet {
    
    private CursoDAO cursoDAO;
    private AlumnoDAO alumnoDAO;
    private AsistenciaDAO asistenciaDAO;

    @Override
    public void init() {
        cursoDAO = new CursoDAO();
        alumnoDAO = new AlumnoDAO();
        asistenciaDAO = new AsistenciaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        if (accion == null) accion = "listarCursos";

        switch (accion) {
            case "listarCursos":
                listarCursosProfesor(request, response);
                break;
            case "tomar":
                mostrarFormularioAsistencia(request, response);
                break;
            // --- NUEVO: Caso para el alumno ---
            case "historial":
                verHistorialAlumno(request, response);
                break;
            default:
                response.sendRedirect("login.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if ("registrar".equals(accion)) {
            registrarAsistencia(request, response);
        } else {
            doGet(request, response);
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private void listarCursosProfesor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || usuario.getIdRol() != 2) { 
            response.sendRedirect("login.jsp");
            return;
        }

        List<Curso> misCursos = cursoDAO.selectByProfesor(usuario.getIdUsuario());
        request.setAttribute("listaCursos", misCursos);
        request.getRequestDispatcher("profesor-cursos.jsp").forward(request, response);
    }

    private void mostrarFormularioAsistencia(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        
        // Obtener alumnos del curso
        List<AlumnoInscrito> alumnos = alumnoDAO.listarPorCurso(idCurso);
        
        // Enviar datos al JSP
        request.setAttribute("alumnos", alumnos);
        request.setAttribute("idCurso", idCurso);
        request.setAttribute("fechaHoy", LocalDate.now().toString()); 
        
        request.getRequestDispatcher("asistencia-form.jsp").forward(request, response);
    }

    private void registrarAsistencia(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        String fecha = request.getParameter("fecha");
        String bloque = request.getParameter("bloque"); // Capturamos el bloque

        List<AlumnoInscrito> alumnos = alumnoDAO.listarPorCurso(idCurso);

        for (AlumnoInscrito alumno : alumnos) {
            String checkboxName = "presente_" + alumno.getIdAlumnoCurso();
            
            // Checkbox marcado = presente, desmarcado = ausente
            boolean isPresente = request.getParameter(checkboxName) != null;
            String estado = isPresente ? "Presente" : "Ausente";

            Asistencia asis = new Asistencia(
                alumno.getIdAlumnoCurso(), 
                fecha, 
                estado, 
                bloque 
            );

            asistenciaDAO.registrar(asis);
        }

        response.sendRedirect("asistencia?accion=listarCursos");
    }

    // --- NUEVO MÉTODO PARA EL ALUMNO (Paso 3 del ajuste final) ---
    private void verHistorialAlumno(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Validar que sea Alumno (Rol 3)
        if (usuario == null || usuario.getIdRol() != 3) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Usar el DAO para buscar su historial
        List<Asistencia> historial = asistenciaDAO.listarPorAlumno(usuario.getIdUsuario());
        
        request.setAttribute("listaAsistencia", historial);
        request.getRequestDispatcher("alumno-historial.jsp").forward(request, response);
    }
}