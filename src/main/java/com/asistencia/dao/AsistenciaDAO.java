package com.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asistencia.model.Asistencia;
import com.asistencia.util.ConexionDB;

public class AsistenciaDAO {

    // 1. REGISTRAR ASISTENCIA (Actualizado con Bloque)
    public boolean registrar(Asistencia a) {
        // NOTA: Usamos INSERT normal. Si ya existe la combinación (alumno, curso, fecha, bloque),
        // la BD dará error gracias a la restricción UNIQUE que creamos en el paso 1.
        // Si quisieras que se actualice en vez de dar error, se usaría "ON CONFLICT...".
        // Por ahora, lo dejaremos simple.
        
        String sql = "INSERT INTO asistencia (id_alumno_curso, fecha, estado, bloque) VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, a.getIdAlumnoCurso());
            pstmt.setString(2, a.getFecha());
            pstmt.setString(3, a.getEstado());
            pstmt.setString(4, a.getBloque()); // <--- ¡Nuevo dato!
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. LISTAR POR ALUMNO (Para el historial)
    public List<Asistencia> listarPorAlumno(int idUsuarioAlumno) {
        List<Asistencia> lista = new ArrayList<>();
        // Hacemos JOIN con 'curso' y 'alumno_curso' para obtener datos legibles si fuera necesario,
        // pero principalmente necesitamos filtrar por el ID del usuario alumno.
        // Asumiendo que 'id_alumno_curso' conecta con la tabla intermedia.
        
        // Ajusta esta consulta según tus nombres exactos de tabla. 
        // Aquí busco todas las asistencias donde el alumno coincida.
        String sql = "SELECT a.* FROM asistencia a " +
                     "JOIN alumno_curso ac ON a.id_alumno_curso = ac.id_alumno_curso " +
                     "WHERE ac.id_usuario = ? " +
                     "ORDER BY a.fecha DESC, a.bloque ASC"; 

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuarioAlumno);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Asistencia asis = new Asistencia();
                asis.setIdAsistencia(rs.getInt("id_asistencia"));
                asis.setIdAlumnoCurso(rs.getInt("id_alumno_curso"));
                asis.setFecha(rs.getString("fecha"));
                asis.setEstado(rs.getString("estado"));
                asis.setBloque(rs.getString("bloque")); // <--- Recuperamos el bloque
                lista.add(asis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    // Método auxiliar para verificar si ya existe asistencia (Opcional, para validaciones visuales)
    public boolean existeRegistro(int idAlumnoCurso, String fecha, String bloque) {
        String sql = "SELECT 1 FROM asistencia WHERE id_alumno_curso = ? AND fecha = TO_DATE(?, 'YYYY-MM-DD') AND bloque = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAlumnoCurso);
            pstmt.setString(2, fecha);
            pstmt.setString(3, bloque);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}