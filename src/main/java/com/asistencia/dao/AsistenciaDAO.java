package com.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asistencia.model.Asistencia;
import com.asistencia.util.ConexionDB;

public class AsistenciaDAO {

    // 1. REGISTRAR ASISTENCIA (Con lógica de tablas intermedias y estado texto)
    public boolean registrar(int idCurso, int idUsuarioAlumno, Date fechaJava, String estado) {
        // Busca automáticamente el ID de la matrícula (alumno_curso) antes de insertar
        String sql = "INSERT INTO asistencia (id_alumno_curso, fecha, estado) " +
                     "SELECT ac.id_alumno_curso, ?, ? " +
                     "FROM alumno_curso ac " +
                     "INNER JOIN alumno a ON ac.id_alumno = a.id_alumno " +
                     "WHERE ac.id_curso = ? AND a.id_usuario = ?";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, new java.sql.Date(fechaJava.getTime()));
            pstmt.setString(2, estado);
            pstmt.setInt(3, idCurso);
            pstmt.setInt(4, idUsuarioAlumno);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. LISTAR POR ALUMNO Y CURSO (Para el detalle y el cálculo de estadísticas)
    public List<Asistencia> listarPorAlumnoYCurso(int idUsuarioAlumno, int idCurso) {
        List<Asistencia> historial = new ArrayList<>();
        
        String sql = "SELECT a.fecha, a.estado, c.nombre_curso " +
                     "FROM asistencia a " +
                     "INNER JOIN alumno_curso ac ON a.id_alumno_curso = ac.id_alumno_curso " +
                     "INNER JOIN curso c ON ac.id_curso = c.id_curso " +
                     "INNER JOIN alumno al ON ac.id_alumno = al.id_alumno " +
                     "WHERE al.id_usuario = ? AND c.id_curso = ? " +
                     "ORDER BY a.fecha DESC";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuarioAlumno);
            pstmt.setInt(2, idCurso);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Asistencia asis = new Asistencia();
                    asis.setFecha(rs.getDate("fecha"));
                    asis.setEstado(rs.getString("estado"));
                    asis.setNombreCurso(rs.getString("nombre_curso"));
                    historial.add(asis);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historial;
    }
}