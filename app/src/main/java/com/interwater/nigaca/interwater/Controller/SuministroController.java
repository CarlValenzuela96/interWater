package com.interwater.nigaca.interwater.Controller;

import android.content.Context;

import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Suministro;

import java.util.ArrayList;

public class SuministroController {

    public int META_DIARIA = 20000;
    public DatabaseHelper dbh;
    public Context context;

    public SuministroController(Context context) {
        this.context = context;
    }

    public void addSuministro(Suministro suministro){
        this.dbh = new DatabaseHelper(this.context);
        this.dbh.insertSuministro(suministro);
    }

    public int suministroDiario(){
        this.dbh = new DatabaseHelper(this.context);
        ArrayList<Suministro> sumDiario = this.dbh.getSuministroDiario();
        int suministro = 0;
        for (int i=0;i<sumDiario.size();i++){
            suministro = suministro + sumDiario.get(i).getAgua_entregada();
        }
        return suministro;
    }

    public int familiasSuministradasDiario(){
        this.dbh = new DatabaseHelper(this.context);
        ArrayList<Suministro> sumDiario = this.dbh.getSuministroDiario();
        return sumDiario.size();
    }

    public int suministroFaltanteDiario(){
        int falta = META_DIARIA - suministroDiario();
        return falta;
    }


}
