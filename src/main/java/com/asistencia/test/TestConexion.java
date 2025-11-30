package com.asistencia.test;

import com.asistencia.util.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBA DE CONEXIÓN A SUPABASE ---");
        
        try {
            // Intentamos obtener la instancia de conexión
            Connection conn = ConexionDB.getInstance().getConnection();
            
            if(conn != null) {
                System.out.println("✅ ¡CONEXIÓN EXITOSA!");
                System.out.println("--------------------------------------------------");
                System.out.println("Base de Datos conectada: " + conn.getMetaData().getDatabaseProductName());
                System.out.println("Versión del Motor: " + conn.getMetaData().getDatabaseProductVersion());
                System.out.println("Driver utilizado: " + conn.getMetaData().getDriverName());
                System.out.println("--------------------------------------------------");
                
                // Cerramos la conexión para no dejarla abierta (solo por ser prueba)
                conn.close();
                System.out.println("🔒 Conexión cerrada correctamente tras la prueba.");
            } else {
                System.out.println("❌ ERROR: La conexión devolvió un objeto nulo.");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ FALLO LA CONEXIÓN:");
            System.err.println("Mensaje de Error: " + e.getMessage());
            System.err.println("Código SQLState: " + e.getSQLState());
            e.printStackTrace(); // Esto nos dirá exactamente dónde falló (password, url, firewall, etc.)
        }
        
        System.out.println("--- FIN DE LA PRUEBA ---");
    }
}