package com.asistencia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asistencia.model.Curso;
import com.asistencia.util.ConexionDB;

public class CursoDAO {

    public List<Curso> selectByProfesor(int idProfesor) {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso WHERE id_profesor = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idProfesor);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("id_curso"));
                c.setCodigo(rs.getString("codigo"));
                c.setNombre(rs.getString("nombre"));
                c.setIdProfesor(rs.getInt("id_profesor"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}