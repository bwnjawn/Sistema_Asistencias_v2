package com.asistencia.model;

public class Curso {
    private int idCurso;
    private String codigoCurso;
    private String nombreCurso;
    private String descripcion;
    
    // Campo extra para estadísticas (no va en la BD)
    private int porcentajeAsistencia; 

    // 1. Constructor Vacío (Obligatorio)
    public Curso() { }

    // 2. Constructor para INSERTAR (Sin ID)
    public Curso(String codigoCurso, String nombreCurso, String descripcion) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.descripcion = descripcion;
    }

    // 3. Constructor COMPLETO (Con ID) <-- ESTE ES EL QUE TE FALTABA
    public Curso(int idCurso, String codigoCurso, String nombreCurso, String descripcion) {
        this.idCurso = idCurso;
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.descripcion = descripcion;
    }
    
    // --- Getters y Setters ---
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getCodigoCurso() { return codigoCurso; }
    public void setCodigoCurso(String codigoCurso) { this.codigoCurso = codigoCurso; }

    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getPorcentajeAsistencia() { return porcentajeAsistencia; }
    public void setPorcentajeAsistencia(int porcentajeAsistencia) { this.porcentajeAsistencia = porcentajeAsistencia; }
}