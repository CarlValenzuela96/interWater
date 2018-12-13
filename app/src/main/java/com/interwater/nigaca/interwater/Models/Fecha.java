package com.interwater.nigaca.interwater.Models;

public class Fecha {

    private int id_fecha;
    private int dia;
    private int mes;
    private int year;

    public Fecha(int id_fecha, int dia, int mes, int year) {
        this.id_fecha = id_fecha;
        this.dia = dia;
        this.mes = mes;
        this.year = year;
    }

    @Override
    public String toString() {
        return dia + "-" + mes + "-" + year;
    }

    public Fecha(int dia, int mes, int year) {
        this.dia = dia;
        this.mes = mes;
        this.year = year;
    }

    public int getId_fecha() {
        return id_fecha;
    }

    public void setId_fecha(int id_fecha) {
        this.id_fecha = id_fecha;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
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

    /***********************************************************
     * SQL-LITE USE | VARIABLES ASSOCIATED WHIT FECHA ENTITY
     ***********************************************************/
    // Table name
    public static final String TABLE_NAME = "fecha";
    // Columns name
    public static final String COLUMN_ID = "id_fecha";
    public static final String COLUMN_DIA = "dia";
    public static final String COLUMN_MES = "mes";
    public static final String COLUMN_YEAR = "year";
    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DIA + " INTEGER,"
                    + COLUMN_MES + " INTEGER,"
                    + COLUMN_YEAR + " INTEGER"
                    + ")";

}
