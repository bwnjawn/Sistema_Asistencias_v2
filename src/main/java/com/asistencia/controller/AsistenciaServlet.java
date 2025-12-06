package com.asistencia.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asistencia.dao.AsistenciaDAO;
import com.asistencia.dao.CursoDAO;
import com.asistencia.dao.UsuarioDAO;
import com.asistencia.model.Asistencia;
import com.asistencia.model.Curso;
import com.asistencia.model.Usuario;

@WebServlet("/asistencia")
public class AsistenciaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AsistenciaDAO asistenciaDAO;
    private UsuarioDAO usuarioDAO;
    private CursoDAO cursoDAO;

    public void init() {
        asistenciaDAO = new AsistenciaDAO();
        usuarioDAO = new UsuarioDAO();
        cursoDAO = new CursoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        // Validación de seguridad básica
        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // --- CASO 1: PROFESOR TOMA ASISTENCIA ---
        if ("tomar".equals(accion)) {
            if (usuario.getIdRol() == 2) { // Solo profesores
                int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                List<Usuario> alumnos = usuarioDAO.listarAlumnosPorCurso(idCurso);
                
                request.setAttribute("idCurso", idCurso);
                request.setAttribute("listaAlumnos", alumnos);
                request.setAttribute("fechaHoy", new Date());
                
                request.getRequestDispatcher("asistencia-form.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        
        // --- CASO 2: ALUMNO VE SUS RAMOS Y ESTADÍSTICAS ---
        } else if ("mis_ramos".equals(accion)) {
            if (usuario.getIdRol() == 3) { // Solo alumnos
                // 1. Buscamos los cursos del alumno
                List<Curso> misCursos = cursoDAO.listarPorAlumno(usuario.getIdUsuario());
                
                // 2. CALCULADORA DE ESTADÍSTICAS (Regla de tres)
                for (Curso c : misCursos) {
                    // Pedimos el historial de este curso específico
                    List<Asistencia> lista = asistenciaDAO.listarPorAlumnoYCurso(usuario.getIdUsuario(), c.getIdCurso());
                    
                    int totalClases = lista.size();
                    int clasesPresente = 0;
                    
                    if (totalClases > 0) {
                        for (Asistencia a : lista) {
                            if ("Presente".equalsIgnoreCase(a.getEstado())) {
                                clasesPresente++;
                            }
                        }
                        // Fórmula: (Presentes * 100) / Total
                        int porcentaje = (clasesPresente * 100) / totalClases;
                        c.setPorcentajeAsistencia(porcentaje);
                    } else {
                        // Si no hay clases registradas aún, mostramos 100% por defecto
                        c.setPorcentajeAsistencia(100); 
                    }
                }
                
                request.setAttribute("misCursos", misCursos);
                // CORRECCIÓN 1.A (Previa): Añadir este atributo para que la tabla se muestre en el JSP
                request.setAttribute("mostrarRamos", true); 
                // CORRECCIÓN 1.B (Previa): Cambiar la ruta al JSP correcto
                request.getRequestDispatcher("dashboardAlumno.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }

        // --- CASO 3: ALUMNO VE DETALLE DE ASISTENCIA (Fechas) ---
        } else if ("ver_detalle".equals(accion)) {
            if (usuario.getIdRol() == 3) { 
                int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                
                // Buscamos asistencia filtrada por alumno Y curso
                List<Asistencia> historial = asistenciaDAO.listarPorAlumnoYCurso(usuario.getIdUsuario(), idCurso);
                
                request.setAttribute("historial", historial);
                // Sacamos el nombre del curso del primer registro (si existe) para el título
                String nombreCurso = (!historial.isEmpty()) ? historial.get(0).getNombreCurso() : "el Curso";
                request.setAttribute("nombreCurso", nombreCurso);
                
                request.getRequestDispatcher("alumno-historial-detalle.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Lógica de guardado (Solo Profesores)
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        List<Usuario> alumnos = usuarioDAO.listarAlumnosPorCurso(idCurso);
        Date fecha = new Date();
        
        for (Usuario alumno : alumnos) {
            String paramName = "presente_" + alumno.getIdUsuario();
            
            // Si viene el parámetro es true, si no viene es false
            boolean checkboxMarcado = (request.getParameter(paramName) != null);
            String estado = checkboxMarcado ? "Presente" : "Ausente";
            
            asistenciaDAO.registrar(idCurso, alumno.getIdUsuario(), fecha, estado);
        }
        
        // 🎉 CORRECCIÓN FINAL (Problema del Profesor):
        // Redirigimos al Servlet de Cursos con la acción 'mis_cursos' para que 
        // recargue la lista de ramos del profesor antes de mostrar el dashboard.
        response.sendRedirect("cursos?accion=mis_cursos");
    }
}