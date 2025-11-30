package com.asistencia.test;

import com.asistencia.dao.UsuarioDAO;
import com.asistencia.model.Usuario;

public class TestUsuarioDAO {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        
        System.out.println("--- PRUEBA DE REGISTRO ---");
        // Creamos un usuario de prueba (Rol 3 = Alumno)
        Usuario nuevo = new Usuario("11223344-5", "Juan", "Pérez", "juan@test.com", "1234", 3);
        
        if(dao.insert(nuevo)) {
            System.out.println("✅ Usuario registrado con éxito en Supabase.");
        } else {
            System.out.println("⚠️ Error al registrar (¿Quizás el email o RUT ya existen?)");
        }
        
        System.out.println("\n--- PRUEBA DE LOGIN ---");
        // Intentamos loguearnos con el usuario recién creado
        Usuario logueado = dao.validarLogin("juan@test.com", "1234");
        
        if(logueado != null) {
            System.out.println("✅ Login Exitoso: Bienvenido " + logueado.getNombre());
            System.out.println("Rol ID: " + logueado.getIdRol());
        } else {
            System.out.println("❌ Login Fallido.");
        }
    }
}