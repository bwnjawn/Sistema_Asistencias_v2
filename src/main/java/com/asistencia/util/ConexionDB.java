package com.asistencia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // --- CONFIGURACIÓN DE CONEXIÓN A SUPABASE (Connection Pooler) ---
    
    // 1. URL JDBC:
    private static final String URL = "jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres?sslmode=require";
    
    // 2. USUARIO:
  
    private static final String USER = "postgres.bwfnosoulhjketfappxx"; 
    
    // 3. CONTRASEÑA:
    private static final String PASS = "cortemagnate123!"; 

    // --- PATRÓN SINGLETON (Para reutilizar la conexión) ---
    private static ConexionDB instancia;
    private Connection connection;

    // Constructor privado
    private ConexionDB() { }

    // Método para obtener la instancia única
    public static ConexionDB getInstance() {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    // Método para abrir/obtener la conexión
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Cargar driver de PostgreSQL
                Class.forName("org.postgresql.Driver");
                // Establecer conexión
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("--> ¡Conexión establecida con Supabase (Pooler)!");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Error: No se encontró el driver de PostgreSQL.", e);
            }
        }
        return connection;
    }
}