package com.interwater.nigaca.interwater.Models;

public class Comunidad {

    private int id_comunidad;
    private int cant_personas;
    private String nombre_comunidad;

    public Comunidad(int id_comunidad, int cant_personas, String nombre_comunidad) {
        this.id_comunidad = id_comunidad;
        this.cant_personas = cant_personas;
        this.nombre_comunidad = nombre_comunidad;
    }

    public int getId_comunidad() {
        return id_comunidad;
    }

    public void setId_comunidad(int id_comunidad) {
        this.id_comunidad = id_comunidad;
    }

    public int getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(int cant_personas) {
        this.cant_personas = cant_personas;
    }

    public String getNombre_comunidad() {
        return nombre_comunidad;
    }

    public void setNombre_comunidad(String nombre_comunidad) {
        this.nombre_comunidad = nombre_comunidad;
    }


    /***********************************************************
     * SQL-LITE USE | VARIABLES ASSOCIATED WHIT COMUNIDAD ENTITY
     ***********************************************************/
    // Table name
    public static final String TABLE_NAME = "comunidad";
    // Columns name
    public static final String COLUMN_ID = "id_comunidad";
    public static final String COLUMN_CANT_PERSONAS = "cant_personas";
    public static final String COLUMN_NOMBRE = "nombre_comunidad";

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CANT_PERSONAS + " INTEGER,"
                    + COLUMN_NOMBRE + " VARCHAR(255),"
                    + ")";
}
