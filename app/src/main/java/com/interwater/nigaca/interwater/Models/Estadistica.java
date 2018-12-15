package com.interwater.nigaca.interwater.Models;

public class Estadistica {
    int id_suministro;
    int agua_entregada;
    int mes;
    int year;
    int id_comunidad;

    public Estadistica(int id_suministro, int agua_entregada, int mes, int year, int id_comunidad) {
        this.id_suministro = id_suministro;
        this.agua_entregada = agua_entregada;
        this.mes = mes;
        this.year = year;
        this.id_comunidad = id_comunidad;
    }

    public int getId_suministro() {
        return id_suministro;
    }

    public void setId_suministro(int id_suministro) {
        this.id_suministro = id_suministro;
    }

    public int getAgua_entregada() {
        return agua_entregada;
    }

    public void setAgua_entregada(int agua_entregada) {
        this.agua_entregada = agua_entregada;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId_comunidad() {
        return id_comunidad;
    }

    public void setId_comunidad(int id_comunidad) {
        this.id_comunidad = id_comunidad;
    }
}
