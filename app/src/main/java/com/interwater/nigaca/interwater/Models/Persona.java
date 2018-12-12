package com.interwater.nigaca.interwater.Models;

public class Persona {

    private int id_persona;
    private int rut;
    private String nombre_persona;
    private String apellido_paterno;
    private String apellido_materno;
    private Comunidad comunidad;

    public Persona(int id_persona, int rut, String nombre_persona, String apellido_paterno, String apellido_materno, Comunidad comunidad) {
        this.id_persona = id_persona;
        this.rut = rut;
        this.nombre_persona = nombre_persona;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.comunidad = comunidad;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    /***********************************************************
     * SQL-LITE USE | VARIABLES ASSOCIATED WHIT PERSONA ENTITY
     ***********************************************************/
    // Table name
    public static final String TABLE_NAME = "persona";
    // Columns name
    public static final String COLUMN_ID = "id_persona";
    public static final String COLUMN_RUT = "rut";
    public static final String COLUMN_NOMBRE = "nombre_persona";
    public static final String COLUMN_PAT_APELLIDO = "apellido_paterno";
    public static final String COLUMN_MAT_APELLIDO = "apellido_materno";
    public static final String COLUMN_ID_COM = "id_comunidad";
    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_RUT + " INTEGER,"
                    + COLUMN_NOMBRE + " VARCHAR(255),"
                    + COLUMN_PAT_APELLIDO + " VARCHAR(255),"
                    + COLUMN_MAT_APELLIDO + " VARCHAR(255),"
                    +" FOREIGN KEY ("+COLUMN_ID_COM+") REFERENCES "+Comunidad.TABLE_NAME+"("+Comunidad.COLUMN_ID+")"
                    + ")";
}
