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

    // INSERTAR
    public boolean insert(Curso curso) {
        // Usamos los nombres REALES de tu base de datos
        String sql = "INSERT INTO curso (CODIGO_CURSO, NOMBRE_CURSO, DESCRIPCION) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, curso.getCodigoCurso());
            pstmt.setString(2, curso.getNombreCurso());
            pstmt.setString(3, curso.getDescripcion());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR TODOS
    public List<Curso> selectAll() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso ORDER BY ID_CURSO ASC";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("ID_CURSO"));
                c.setCodigoCurso(rs.getString("CODIGO_CURSO"));
                c.setNombreCurso(rs.getString("NOMBRE_CURSO"));
                c.setDescripcion(rs.getString("DESCRIPCION"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    // BUSCAR POR ID
    public Curso selectById(int id) {
        String sql = "SELECT * FROM curso WHERE ID_CURSO = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Curso(
                        rs.getInt("ID_CURSO"),
                        rs.getString("CODIGO_CURSO"),
                        rs.getString("NOMBRE_CURSO"),
                        rs.getString("DESCRIPCION")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ACTUALIZAR
    public boolean update(Curso curso) {
        String sql = "UPDATE curso SET CODIGO_CURSO=?, NOMBRE_CURSO=?, DESCRIPCION=? WHERE ID_CURSO=?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, curso.getCodigoCurso());
            pstmt.setString(2, curso.getNombreCurso());
            pstmt.setString(3, curso.getDescripcion());
            pstmt.setInt(4, curso.getIdCurso());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean delete(int id) {
        String sql = "DELETE FROM curso WHERE ID_CURSO = ?";
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar cursos de un profesor específico
    public List<Curso> listarPorProfesor(int idUsuarioProfesor) {
        List<Curso> cursos = new ArrayList<>();
        // Este SQL une: Usuario -> Profesor -> Curso_Profesor -> Curso
        String sql = "SELECT c.ID_CURSO, c.CODIGO_CURSO, c.NOMBRE_CURSO, c.DESCRIPCION " +
                    "FROM curso c " +
                    "INNER JOIN curso_profesor cp ON c.ID_CURSO = cp.ID_CURSO " +
                    "INNER JOIN profesor p ON cp.ID_PROFESOR = p.id_profesor " +
                    "WHERE p.id_usuario = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuarioProfesor);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Curso c = new Curso();
                    c.setIdCurso(rs.getInt("ID_CURSO"));
                    c.setCodigoCurso(rs.getString("CODIGO_CURSO"));
                    c.setNombreCurso(rs.getString("NOMBRE_CURSO"));
                    c.setDescripcion(rs.getString("DESCRIPCION"));
                    cursos.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }    
// --- NUEVO PARA ALUMNO: Listar cursos donde estoy inscrito ---
    public List<Curso> listarPorAlumno(int idUsuarioAlumno) {
        List<Curso> cursos = new ArrayList<>();
        // Unimos: Curso -> Alumno_Curso -> Alumno -> Usuario
        String sql = "SELECT c.* FROM curso c " +
                     "INNER JOIN alumno_curso ac ON c.id_curso = ac.id_curso " +
                     "INNER JOIN alumno a ON ac.id_alumno = a.id_alumno " +
                     "WHERE a.id_usuario = ? " +
                     "ORDER BY c.nombre_curso ASC";
        
        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idUsuarioAlumno);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Curso c = new Curso();
                    c.setIdCurso(rs.getInt("id_curso"));
                    c.setCodigoCurso(rs.getString("codigo_curso"));
                    c.setNombreCurso(rs.getString("nombre_curso"));
                    c.setDescripcion(rs.getString("descripcion"));
                    cursos.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}