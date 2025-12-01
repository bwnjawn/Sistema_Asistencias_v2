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

    // --- 1. VALIDAR LOGIN ---
    public Usuario validarLogin(String email, String password) {
        Usuario usuario = null;
        // CORRECCIÓN: Usamos 'password_hash' en lugar de 'password'
        String sql = "SELECT * FROM usuario WHERE email = ? AND password_hash = ?";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setRut(rs.getString("rut"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setEmail(rs.getString("email"));
                    // CORRECCIÓN: Recuperamos de la columna 'password_hash'
                    usuario.setPassword(rs.getString("password_hash")); 
                    usuario.setIdRol(rs.getInt("id_rol"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    // --- 2. INSERTAR NUEVO USUARIO ---
    public boolean insert(Usuario usuario) {
        // CORRECCIÓN: 'password_hash' en el INSERT
        String sql = "INSERT INTO usuario (rut, nombre, apellido, email, password_hash, id_rol) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getRut());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getPassword()); 
            pstmt.setInt(6, usuario.getIdRol());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- 3. LISTAR TODOS ---
    public List<Usuario> selectAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY id_usuario ASC";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setRut(rs.getString("rut"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setEmail(rs.getString("email"));
                // CORRECCIÓN: Recuperamos 'password_hash'
                u.setPassword(rs.getString("password_hash")); 
                u.setIdRol(rs.getInt("id_rol"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // --- 4. BUSCAR POR ID ---
    public Usuario selectById(int id) {
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getInt("id_usuario"));
                    u.setRut(rs.getString("rut"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellido(rs.getString("apellido"));
                    u.setEmail(rs.getString("email"));
                    // CORRECCIÓN: Recuperamos 'password_hash'
                    u.setPassword(rs.getString("password_hash"));
                    u.setIdRol(rs.getInt("id_rol"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // --- 5. ACTUALIZAR ---
    public boolean update(Usuario usuario) {
        // CORRECCIÓN: 'password_hash' en el UPDATE
        String sql = "UPDATE usuario SET rut=?, nombre=?, apellido=?, email=?, password_hash=?, id_rol=? WHERE id_usuario=?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getRut());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getApellido());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setString(5, usuario.getPassword());
            pstmt.setInt(6, usuario.getIdRol());
            pstmt.setInt(7, usuario.getIdUsuario());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- 6. ELIMINAR ---
    public boolean delete(int id) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}