package com.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.asistencia.model.Usuario;
import com.asistencia.util.ConexionDB;

public class AlumnoDAO {

    // Clase auxiliar interna
    public static class AlumnoInscrito extends Usuario {
        private int idAlumnoCurso; 
        public int getIdAlumnoCurso() { return idAlumnoCurso; }
        public void setIdAlumnoCurso(int id) { this.idAlumnoCurso = id; }
    }

    public List<AlumnoInscrito> listarPorCurso(int idCurso) {
        List<AlumnoInscrito> lista = new ArrayList<>();
        // Ajusta la consulta a tus tablas reales
        String sql = "SELECT u.*, ac.id_alumno_curso " +
                     "FROM usuario u " +
                     "JOIN alumno_curso ac ON u.id_usuario = ac.id_usuario " +
                     "WHERE ac.id_curso = ? AND u.id_rol = 3 " +
                     "ORDER BY u.apellido, u.nombre";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idCurso);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                AlumnoInscrito al = new AlumnoInscrito();
                al.setIdUsuario(rs.getInt("id_usuario"));
                al.setRut(rs.getString("rut"));
                al.setNombre(rs.getString("nombre"));
                al.setApellido(rs.getString("apellido"));
                al.setIdAlumnoCurso(rs.getInt("id_alumno_curso")); 
                lista.add(al);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}