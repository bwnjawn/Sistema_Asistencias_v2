package com.asistencia.model;

public class Asistencia {
    private int idAsistencia;
    private int idAlumnoCurso; // Asegúrate que este nombre coincida con tu versión anterior
    private String fecha;      // O java.sql.Date, según como lo tengas
    private String estado;     // Presente/Ausente
    
    // --- NUEVO CAMPO ---
    private String bloque; 

    public Asistencia() {}

    // Constructor completo actualizado
    public Asistencia(int idAsistencia, int idAlumnoCurso, String fecha, String estado, String bloque) {
        this.idAsistencia = idAsistencia;
        this.idAlumnoCurso = idAlumnoCurso;
        this.fecha = fecha;
        this.estado = estado;
        this.bloque = bloque;
    }

    // Constructor sin ID (para insertar) actualizado
    public Asistencia(int idAlumnoCurso, String fecha, String estado, String bloque) {
        this.idAlumnoCurso = idAlumnoCurso;
        this.fecha = fecha;
        this.estado = estado;
        this.bloque = bloque;
    }

    // Getters y Setters existentes...
    public int getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(int idAsistencia) { this.idAsistencia = idAsistencia; }

    public int getIdAlumnoCurso() { return idAlumnoCurso; }
    public void setIdAlumnoCurso(int idAlumnoCurso) { this.idAlumnoCurso = idAlumnoCurso; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // --- NUEVOS GETTER Y SETTER PARA BLOQUE ---
    public String getBloque() { return bloque; }
    public void setBloque(String bloque) { this.bloque = bloque; }
}