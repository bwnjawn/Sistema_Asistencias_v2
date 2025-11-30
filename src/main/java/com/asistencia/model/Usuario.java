package com.asistencia.model;

/**
 * Representa un usuario del sistema (Admin, Profesor o Alumno).
 * Mapea la tabla 'usuario' de la base de datos.
 */
public class Usuario {
    private int idUsuario;
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String password; // Representa el hash o la clave
    private int idRol;       // 1=Admin, 2=Profesor, 3=Alumno

    // Constructor Vacío (Necesario para frameworks y buenas prácticas)
    public Usuario() {
    }

    // Constructor Completo (Para leer desde la BD)
    public Usuario(int idUsuario, String rut, String nombre, String apellido, String email, String password, int idRol) {
        this.idUsuario = idUsuario;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }

    // Constructor sin ID (Para crear nuevos usuarios antes de insertar en BD)
    public Usuario(String rut, String nombre, String apellido, String email, String password, int idRol) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.idRol = idRol;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getIdRol() { return idRol; }
    public void setIdRol(int idRol) { this.idRol = idRol; }

    @Override
    public String toString() {
        return "Usuario [id=" + idUsuario + ", nombre=" + nombre + ", rol=" + idRol + "]";
    }
}