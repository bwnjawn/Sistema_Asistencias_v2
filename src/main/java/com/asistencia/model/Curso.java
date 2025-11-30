package com.asistencia.model;

public class Curso {
    private int idCurso;
    private String codigo;      // Ej: PGY4121
    private String nombre;      // Ej: Programación Java
    private int idProfesor;     // ID del usuario profesor asignado

    public Curso() { }

    public Curso(int idCurso, String codigo, String nombre, int idProfesor) {
        this.idCurso = idCurso;
        this.codigo = codigo;
        this.nombre = nombre;
        this.idProfesor = idProfesor;
    }

    public Curso(String codigo, String nombre, int idProfesor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idProfesor = idProfesor;
    }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getIdProfesor() { return idProfesor; }
    public void setIdProfesor(int idProfesor) { this.idProfesor = idProfesor; }
}