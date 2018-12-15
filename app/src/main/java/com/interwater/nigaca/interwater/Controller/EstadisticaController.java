package com.interwater.nigaca.interwater.Controller;

import android.content.Context;

import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.Models.Estadistica;

import java.util.ArrayList;

public class EstadisticaController {

    Context context;
    DatabaseHelper dbh;

    public EstadisticaController(Context context) {
        this.context = context;
    }


    public int totalAbastecido(int mes, int year){
        DatabaseHelper dbh = new DatabaseHelper(this.context);
        ArrayList<Estadistica> estadisticas = dbh.estadisticaMensual(mes,year);

        int total =0;
        for(int i=0;i<estadisticas.size();i++){
            total = total + estadisticas.get(i).getAgua_entregada();
        }

        return total;
    }

    public int personasAbastecidas(int mes, int year){
        DatabaseHelper dbh = new DatabaseHelper(this.context);
        return dbh.estadisticaMensual(mes,year).size();
    }

    public int totalAbastecidoComunidad(Comunidad c, int mes, int year){
        DatabaseHelper dbh = new DatabaseHelper(this.context);
        ArrayList<Estadistica> estadisticas = dbh.estadisticaMensualComunidad(mes, year, c.getId_comunidad());
        int total =0;
        for(int i=0;i<estadisticas.size();i++){
            total = total + estadisticas.get(i).getAgua_entregada();
        }

        return total;
    }

    public int personasAbastecidasComunidad(Comunidad c, int mes, int year){
        DatabaseHelper dbh = new DatabaseHelper(this.context);
        return dbh.estadisticaMensualComunidad(mes,year,c.getId_comunidad()).size();
    }

    public int mesCorresponde(String mes){
        int mesSelect = 0;
        switch (mes){
            case "Enero":
                mesSelect = 1;break;
            case "Febrero":
                mesSelect = 2;break;
            case "Marzo":
                mesSelect = 3;break;
            case "Abril":
                mesSelect = 4;break;
            case "Mayo":
                mesSelect = 5;break;
            case "Junio":
                mesSelect = 6;break;
            case "Julio":
                mesSelect = 7;break;
            case "Agosto":
                mesSelect = 8;break;
            case "Septiembre":
                mesSelect = 9;break;
            case "Octubre":
                mesSelect = 10;break;
            case "Noviembre":
                mesSelect = 11;break;
            case "Diciembre":
                mesSelect = 12;break;
        }
        return mesSelect;
    }

}
