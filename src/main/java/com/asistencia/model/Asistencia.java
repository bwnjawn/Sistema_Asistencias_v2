package com.asistencia.model;

import java.sql.Date;

public class Asistencia {
    private int idAsistencia;
    private int idCurso;
    private int idAlumno;
    private Date fecha;
    private boolean presente;
    
    // Campo auxiliar para mostrar el nombre del curso en los reportes (No está en la tabla asistencia)
    private String nombreCursoAux;

    public Asistencia() { }

    public Asistencia(int idCurso, int idAlumno, Date fecha, boolean presente) {
        this.idCurso = idCurso;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.presente = presente;
    }

    public int getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(int idAsistencia) { this.idAsistencia = idAsistencia; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public boolean isPresente() { return presente; }
    public void setPresente(boolean presente) { this.presente = presente; }

    public String getNombreCursoAux() { return nombreCursoAux; }
    public void setNombreCursoAux(String nombreCursoAux) { this.nombreCursoAux = nombreCursoAux; }
}