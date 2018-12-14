package com.interwater.nigaca.interwater.Controller;

import android.content.Context;

import com.interwater.nigaca.interwater.Database.DatabaseHelper;
import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.Models.Persona;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ComunidadController {


    private final Context context;
    DatabaseHelper dbh;
    public ComunidadController(Context context) {
        this.context = context;
    }

    public long agregarComunidad(Comunidad comunidad){
        this.dbh = new DatabaseHelper(this.context);
        long id = dbh.insertComunidad(comunidad);
        return id;
    }

    public ArrayList<Comunidad> getAllComunidades(){
        this.dbh = new DatabaseHelper(this.context);
        return dbh.getAllComunidades();
    }

    public Comunidad getComunidad(long idCom){
        this.dbh = new DatabaseHelper(this.context);
        return this.dbh.getComunidad(idCom);
    }

    public ArrayList<Persona> leerArchivo(Comunidad c,int path){
        ArrayList<Persona> personas = new ArrayList<>();

        InputStream inputStream = this.context.getResources().openRawResource(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try{
            int i = inputStream.read();
            while(i !=-1){
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }


        String arr []=byteArrayOutputStream.toString().split("\n");
        for (int i = 0 ; i<arr.length;i++){
            String datos [] = arr[i].split(";");
            Persona p =  new Persona(Integer.parseInt(datos[4].trim()),datos[0],datos[1],datos[2],Integer.parseInt(datos[3].trim()),c);
            personas.add(p);
        }

        return personas;
    }

    public void leerArchivoExterno(){

    }

}
