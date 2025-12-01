package com.asistencia.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// @WebFilter define qué URLs vamos a proteger.
// Si alguien intenta entrar a cualquiera de estas sin sesión, el filtro lo detendrá.
@WebFilter(urlPatterns = { 
    "/dashboardAdmin.jsp", 
    "/dashboardProfesor.jsp", 
    "/dashboardAlumno.jsp", 
    "/usuarios",     // Protege el Servlet de Usuarios
    "/cursos",       // Protege futuros servlets
    "/asistencia"    // Protege futuros servlets
})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Se ejecuta cuando inicia el servidor (podemos dejarlo vacío)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 1. Obtener la sesión actual (false = no crear una nueva si no existe)
        HttpSession session = httpRequest.getSession(false);

        // 2. Verificar si existe la sesión y si tiene un usuario guardado
        boolean isLoggedIn = (session != null && session.getAttribute("usuario") != null);

        if (isLoggedIn) {
            // ¡TIENE PASE VIP! -> Dejar pasar la petición al recurso solicitado
            chain.doFilter(request, response);
        } else {
            // ¡INTRUSO! -> Redirigir al login
            System.out.println("ALERTA DE SEGURIDAD: Intento de acceso no autorizado a " + httpRequest.getRequestURI());
            
            // Usamos getContextPath() para asegurar que la redirección sea correcta
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        // Se ejecuta al apagar el servidor
    }
}