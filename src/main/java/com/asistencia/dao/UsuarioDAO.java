package com.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asistencia.model.Usuario;
import com.asistencia.util.ConexionDB;

public class UsuarioDAO {

    private Connection getConnection() throws SQLException {
        return ConexionDB.getInstance().getConnection();
    }

    // 1. VALIDAR LOGIN (Para el Ingreso)
    public Usuario validarLogin(String email, String password) {
        Usuario usuario = null;
        // Buscamos por email y contraseña exacta
        String sql = "SELECT * FROM usuario WHERE email = ? AND password_hash = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Si hay resultado, construimos el objeto Usuario con los datos de la BD
                    usuario = mapUsuario(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al validar login: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

    // 2. INSERTAR USUARIO (Para el Registro)
    public boolean insert(Usuario u) {
        String sql = "INSERT INTO usuario (rut, nombre, apellido, email, password_hash, id_rol) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, u.getRut());
            stmt.setString(2, u.getNombre());
            stmt.setString(3, u.getApellido());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getPassword()); 
            stmt.setInt(6, u.getIdRol()); // Por defecto enviaremos 3 (Alumno) desde el Servlet
            
            return stmt.executeUpdate() > 0; // Retorna true si se insertó al menos 1 fila
            
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Helper para mapear ResultSet a Objeto (Evita repetir código)
    private Usuario mapUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setRut(rs.getString("rut"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password_hash"));
        u.setIdRol(rs.getInt("id_rol"));
        return u;
    }
}
