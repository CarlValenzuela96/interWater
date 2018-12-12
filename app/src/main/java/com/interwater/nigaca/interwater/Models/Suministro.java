package com.interwater.nigaca.interwater.Models;

public class Suministro {

    private int id_suministro;
    private int agua_corresponde;
    private int agua_entregada;
    private Fecha fecha;
    private Persona persona;

    public Suministro(int id_suministro, int agua_corresponde, int agua_entregada, Fecha fecha, Persona persona) {
        this.id_suministro = id_suministro;
        this.agua_corresponde = agua_corresponde;
        this.agua_entregada = agua_entregada;
        this.fecha = fecha;
        this.persona = persona;
    }

    public int getId_suministro() {
        return id_suministro;
    }

    public void setId_suministro(int id_suministro) {
        this.id_suministro = id_suministro;
    }

    public int getAgua_corresponde() {
        return agua_corresponde;
    }

    public void setAgua_corresponde(int agua_corresponde) {
        this.agua_corresponde = agua_corresponde;
    }

    public int getAgua_entregada() {
        return agua_entregada;
    }

    public void setAgua_entregada(int agua_entregada) {
        this.agua_entregada = agua_entregada;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /***********************************************************
     * SQL-LITE USE | VARIABLES ASSOCIATED WHIT SUMINISTRO ENTITY
     ***********************************************************/
    // Table name
    public static final String TABLE_NAME = "suministro";
    // Columns name
    public static final String COLUMN_ID = "id_suministro";
    public static final String COLUMN_CORRESPONDIENTE = "agua_corresponde";
    public static final String COLUMN_ENTREGADO = "agua_entregada";
    public static final String COLUMN_ID_FECHA = "id_fecha";
    public static final String COLUMN_ID_PERSONA = "id_persona";
    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_CORRESPONDIENTE + " INTEGER,"
                    + COLUMN_ENTREGADO + " INTEGER,"
                    +" FOREIGN KEY ("+COLUMN_ID_FECHA+") REFERENCES "+Fecha.TABLE_NAME+"("+Fecha.COLUMN_ID+")"
                    +" FOREIGN KEY ("+COLUMN_ID_PERSONA+") REFERENCES "+Persona.TABLE_NAME+"("+Persona.COLUMN_ID+")"
                    + ")";
}
