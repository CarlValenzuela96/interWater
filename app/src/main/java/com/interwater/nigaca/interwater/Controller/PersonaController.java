package com.interwater.nigaca.interwater.Controller;

import android.content.Context;

import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Persona;

import java.util.ArrayList;

public class PersonaController {
    DatabaseHelper dbh;

    Context context;
    public PersonaController(Context context) {
        this.context = context;
    }

    public void addPersonasToComunidad(ArrayList<Persona> personas){
        this.dbh = new DatabaseHelper(this.context);
        for (int i=0;i<personas.size();i++){
            this.dbh.insertPersona(personas.get(i));
        }
    }

    public ArrayList<Persona> getAllPersonasOfComunidad(long id_com){
        this.dbh = new DatabaseHelper(this.context);
        return this.dbh.getPersonasComunidad(id_com);
    }

}
