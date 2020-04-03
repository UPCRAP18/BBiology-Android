package com.biomaster.Models;

public class Document {
    private int ID;
    private String Nombre, Path;

    public Document() {
    }

    public Document(int ID, String nombre, String path) {
        this.ID = ID;
        Nombre = nombre;
        Path = path;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
