package com.biomaster.Models;

public class User {
    private String ID;
    private String Nombre;
    private String Apellido;
    private String Iniciales;
    private String Correo;
    private String Turno;
    private String Password;

    public User() { }

    public User(String ID, String nombre, String apellido, String iniciales, String correo, String turno, String password) {
        this.ID = ID;
        Nombre = nombre;
        Apellido = apellido;
        Iniciales = iniciales;
        Correo = correo;
        Turno = turno;
        Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getIniciales() {
        return Iniciales;
    }

    public void setIniciales(String iniciales) {
        Iniciales = iniciales;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTurno() {
        return Turno;
    }

    public void setTurno(String turno) {
        Turno = turno;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
