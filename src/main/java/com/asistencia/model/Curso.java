package com.asistencia.model;

public class Curso {
    private int idCurso;
    private String codigoCurso;  // Antes 'codigo'
    private String nombreCurso;  // Antes 'nombre'
    private String descripcion;  // Nuevo campo

    // Constructor vacío
    public Curso() { }

    // Constructor completo
    public Curso(int idCurso, String codigoCurso, String nombreCurso, String descripcion) {
        this.idCurso = idCurso;
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.descripcion = descripcion;
    }

    // Constructor sin ID (para insertar)
    public Curso(String codigoCurso, String nombreCurso, String descripcion) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getCodigoCurso() { return codigoCurso; }
    public void setCodigoCurso(String codigoCurso) { this.codigoCurso = codigoCurso; }

    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}