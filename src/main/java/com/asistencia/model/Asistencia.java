package com.asistencia.model;

import java.util.Date;

public class Asistencia {
    private int idAsistencia;
    private int idCurso;
    private int idAlumno;
    private Date fecha;
    private String estado; // "Presente" o "Ausente"
    
    // Campo extra solo para mostrar en la web (no se guarda en tabla asistencia)
    private String nombreCurso; 

    public Asistencia() {}

    // Getters y Setters
    public int getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(int idAsistencia) { this.idAsistencia = idAsistencia; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }
    
    // Método auxiliar para saber si está presente (útil para checkboxes)
    public boolean isPresente() {
        return "Presente".equalsIgnoreCase(this.estado);
    }
    public void setPresente(boolean presente) {
        this.estado = presente ? "Presente" : "Ausente";
    }
}