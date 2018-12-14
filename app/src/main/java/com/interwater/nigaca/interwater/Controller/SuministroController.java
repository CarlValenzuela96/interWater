package com.interwater.nigaca.interwater.Controller;

import android.content.Context;

import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Suministro;

public class SuministroController {
    DatabaseHelper dbh;

    Context context;

    public SuministroController(Context context) {
        this.context = context;
    }

    public void addSuministro(Suministro suministro){
        this.dbh = new DatabaseHelper(this.context);
        this.dbh.insertSuministro(suministro);
    }


}
