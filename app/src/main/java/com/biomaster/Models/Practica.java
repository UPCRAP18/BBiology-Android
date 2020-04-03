package com.biomaster.Models;

public class Practica {
    private int ID, Practica_Realizar;
    private String ID_Prof;
    private String Fecha;
    private String Hora_Inicio;
    private String Hora_Fin;
    private String Practica;
    private String Ruta;
    private boolean
            Asist_Aux,
            Asist_Chico_Serv,
            Hubo_Practica,
            Muestras_Fija,
            Bata,
            Materiales_Completos,
            Puntualidad_Alumno,
            Puntualidad_Profesor,
            Manual;

    public Practica() { }

    public Practica(int ID, int practica_Realizar, String ID_Prof,
                    String fecha, String hora_Inicio, String hora_Fin,
                    boolean asist_Aux, boolean asist_Chico_Serv, boolean hubo_Practica,
                    boolean muestras_Fija, boolean bata, boolean materiales_Completos,
                    boolean puntualidad_Alumno, boolean puntualidad_Profesor, boolean manual) {
        this.ID = ID;
        this.Practica_Realizar = practica_Realizar;
        this.ID_Prof = ID_Prof;
        this.Fecha = fecha;
        this.Hora_Inicio = hora_Inicio;
        this.Hora_Fin = hora_Fin;
        this.Asist_Aux = asist_Aux;
        this.Asist_Chico_Serv = asist_Chico_Serv;
        this.Hubo_Practica = hubo_Practica;
        this.Muestras_Fija = muestras_Fija;
        this.Bata = bata;
        this.Materiales_Completos = materiales_Completos;
        this.Puntualidad_Alumno = puntualidad_Alumno;
        this.Puntualidad_Profesor = puntualidad_Profesor;
        this.Manual = manual;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPractica_Realizar() {
        return Practica_Realizar;
    }

    public void setPractica_Realizar(int practica_Realizar) {
        Practica_Realizar = practica_Realizar;
    }

    public String getID_Prof() {
        return ID_Prof;
    }

    public void setID_Prof(String ID_Prof) {
        this.ID_Prof = ID_Prof;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora_Inicio() {
        return Hora_Inicio;
    }

    public void setHora_Inicio(String hora_Inicio) {
        Hora_Inicio = hora_Inicio;
    }

    public String getHora_Fin() {
        return Hora_Fin;
    }

    public void setHora_Fin(String hora_Fin) {
        Hora_Fin = hora_Fin;
    }

    public boolean isAsist_Aux() {
        return Asist_Aux;
    }

    public void setAsist_Aux(boolean asist_Aux) {
        Asist_Aux = asist_Aux;
    }

    public boolean isAsist_Chico_Serv() {
        return Asist_Chico_Serv;
    }

    public void setAsist_Chico_Serv(boolean asist_Chico_Serv) {
        Asist_Chico_Serv = asist_Chico_Serv;
    }

    public boolean isHubo_Practica() {
        return Hubo_Practica;
    }

    public void setHubo_Practica(boolean hubo_Practica) {
        Hubo_Practica = hubo_Practica;
    }

    public boolean isMuestras_Fija() {
        return Muestras_Fija;
    }

    public void setMuestras_Fija(boolean muestras_Fija) {
        Muestras_Fija = muestras_Fija;
    }

    public boolean isBata() {
        return Bata;
    }

    public void setBata(boolean bata) {
        Bata = bata;
    }

    public boolean isMateriales_Completos() {
        return Materiales_Completos;
    }

    public void setMateriales_Completos(boolean materiales_Completos) {
        Materiales_Completos = materiales_Completos;
    }

    public boolean isPuntualidad_Alumno() {
        return Puntualidad_Alumno;
    }

    public void setPuntualidad_Alumno(boolean puntualidad_Alumno) {
        Puntualidad_Alumno = puntualidad_Alumno;
    }

    public boolean isPuntualidad_Profesor() {
        return Puntualidad_Profesor;
    }

    public void setPuntualidad_Profesor(boolean puntualidad_Profesor) {
        Puntualidad_Profesor = puntualidad_Profesor;
    }

    public boolean isManual() {
        return Manual;
    }

    public void setManual(boolean manual) {
        Manual = manual;
    }

    public String getPractica() {
        return Practica;
    }

    public void setPractica(String practica) {
        Practica = practica;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String ruta) {
        Ruta = ruta;
    }

}
