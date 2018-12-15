package com.interwater.nigaca.interwater.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.Models.Estadistica;
import com.interwater.nigaca.interwater.Models.Fecha;
import com.interwater.nigaca.interwater.Models.Persona;
import com.interwater.nigaca.interwater.Models.Suministro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "interwater_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Fecha.CREATE_TABLE);
        db.execSQL(Comunidad.CREATE_TABLE);
        db.execSQL(Persona.CREATE_TABLE);
        db.execSQL(Suministro.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertFecha(Fecha fecha){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Fecha.COLUMN_DIA,fecha.getDia());
        values.put(Fecha.COLUMN_MES,fecha.getMes());
        values.put(Fecha.COLUMN_YEAR,fecha.getYear());

        long id = db.insert(Fecha.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long insertComunidad(Comunidad comunidad){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Comunidad.COLUMN_CANT_PERSONAS,comunidad.getCant_personas());
        values.put(Comunidad.COLUMN_NOMBRE,comunidad.getNombre_comunidad());

        long id = db.insert(Comunidad.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long insertPersona(Persona persona){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Persona.COLUMN_RUT,persona.getRut());
        values.put(Persona.COLUMN_NOMBRE,persona.getNombre_persona());
        values.put(Persona.COLUMN_PAT_APELLIDO,persona.getApellido_paterno());
        values.put(Persona.COLUMN_MAT_APELLIDO,persona.getApellido_materno());
        values.put(Persona.COLUMN_A_CORRESPONDE,persona.getAgua_corresponde());
        values.put(Persona.COLUMN_ID_COM,persona.getComunidad().getId_comunidad());

        //validar si la persona esta o no.
        long id = db.insert(Persona.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long insertSuministro(Suministro suministro){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Suministro.COLUMN_ENTREGADO,suministro.getAgua_entregada());
        values.put(Suministro.COLUMN_ID_FECHA,suministro.getFecha().getId_fecha());
        values.put(Suministro.COLUMN_ID_PERSONA,suministro.getPersona().getId_persona());

        long id = db.insert(Suministro.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Fecha getFecha(int id_fecha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Fecha fecha = null;

        Cursor cursor = db.query(Fecha.TABLE_NAME,
                new String[]{Fecha.COLUMN_ID,Fecha.COLUMN_DIA,Fecha.COLUMN_MES,Fecha.COLUMN_YEAR},
                Fecha.COLUMN_ID + "=?",
                new String[]{String.valueOf(id_fecha)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            // prepare note object
            fecha = new Fecha(
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_DIA)),
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_MES)),
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_YEAR))
            );
        }
        // close the db connection
        cursor.close();

        return fecha;
    }

    public Fecha getFechaActual(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        int year = Integer.valueOf(dateFormat.format(date).split("-")[0]);
        int mes =  Integer.valueOf(dateFormat.format(date).split("-")[1]);
        int dia = Integer.valueOf(dateFormat.format(date).split("-")[2]);

        SQLiteDatabase db = this.getReadableDatabase();
        Fecha fecha = null;
        Cursor cursor = db.rawQuery("select * from fecha where dia = '" + dia + "' and mes = '"+mes+"' and year = '"+year+"'", null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            // prepare note object
            fecha = new Fecha(
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_DIA)),
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_MES)),
                    cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_YEAR))
            );
        }
        // close the db connection
        cursor.close();

        return fecha;
    }

    public Comunidad getComunidad(long id_comunidad) {
        SQLiteDatabase db = this.getReadableDatabase();
        Comunidad comunidad = null;

        Cursor cursor = db.query(Comunidad.TABLE_NAME,
                new String[]{Comunidad.COLUMN_ID,Comunidad.COLUMN_CANT_PERSONAS,Comunidad.COLUMN_NOMBRE},
                Comunidad.COLUMN_ID + "=?",
                new String[]{String.valueOf(id_comunidad)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            // prepare note object
            comunidad = new Comunidad(
                    cursor.getInt(cursor.getColumnIndex(Comunidad.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Comunidad.COLUMN_CANT_PERSONAS)),
                    cursor.getString(cursor.getColumnIndex(Comunidad.COLUMN_NOMBRE))
            );
        }
        // close the db connection
        cursor.close();

        return comunidad;
    }

    public ArrayList<Comunidad> getAllComunidades(){
        SQLiteDatabase db = this.getReadableDatabase();
        Comunidad comunidad = null;
        ArrayList<Comunidad> comunidades= new ArrayList<>();

        Cursor  cursor = db.rawQuery("select * from "+Comunidad.TABLE_NAME,null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                comunidad = new Comunidad(
                        cursor.getInt(cursor.getColumnIndex(Comunidad.COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(Comunidad.COLUMN_CANT_PERSONAS)),
                        cursor.getString(cursor.getColumnIndex(Comunidad.COLUMN_NOMBRE))
                );

                comunidades.add(comunidad);
                cursor.moveToNext();
            }
        }
        // close the db connection
        cursor.close();

        return comunidades;

    }

    public Persona getPersona(int rut){
        SQLiteDatabase db = this.getReadableDatabase();
        Persona persona = null;

        Cursor cursor = db.query(Persona.TABLE_NAME,
                new String[]{Persona.COLUMN_ID,Persona.COLUMN_RUT,Persona.COLUMN_NOMBRE,Persona.COLUMN_PAT_APELLIDO,
                        Persona.COLUMN_MAT_APELLIDO,Persona.COLUMN_A_CORRESPONDE,Persona.COLUMN_ID_COM},
                Persona.COLUMN_RUT + "=?",
                new String[]{String.valueOf(rut)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            // prepare note object
            persona = new Persona(
                    cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_RUT)),
                    cursor.getString(cursor.getColumnIndex(Persona.COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(Persona.COLUMN_PAT_APELLIDO)),
                    cursor.getString(cursor.getColumnIndex(Persona.COLUMN_MAT_APELLIDO)),
                    cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_A_CORRESPONDE)),
                    getComunidad(cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_ID_COM)))
            );
        }
        // close the db connection
        cursor.close();

        return persona;
    }

    public ArrayList<Persona> getPersonasComunidad(long idComunidad){
        SQLiteDatabase db = this.getReadableDatabase();
        Persona persona = null;
        ArrayList<Persona> personas = new ArrayList<>();
        Cursor cursor = db.query(Persona.TABLE_NAME,
                new String[]{Persona.COLUMN_ID,Persona.COLUMN_RUT,Persona.COLUMN_NOMBRE,Persona.COLUMN_PAT_APELLIDO,
                        Persona.COLUMN_MAT_APELLIDO,Persona.COLUMN_A_CORRESPONDE,Persona.COLUMN_ID_COM},
                Persona.COLUMN_ID_COM + "=?",
                new String[]{String.valueOf(idComunidad)}, null, null, null, null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                persona = new Persona(
                        cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_RUT)),
                        cursor.getString(cursor.getColumnIndex(Persona.COLUMN_NOMBRE)),
                        cursor.getString(cursor.getColumnIndex(Persona.COLUMN_PAT_APELLIDO)),
                        cursor.getString(cursor.getColumnIndex(Persona.COLUMN_MAT_APELLIDO)),
                        cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_A_CORRESPONDE)),
                        getComunidad(cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_ID_COM)))
                );

                personas.add(persona);
                cursor.moveToNext();
            }
        }
        // close the db connection
        cursor.close();

        return personas;
    }

    public Suministro getSuministro(int id_suministro){
        SQLiteDatabase db = this.getReadableDatabase();
        Suministro suministro = null;

        Cursor cursor = db.query(Persona.TABLE_NAME,
                new String[]{Suministro.COLUMN_ID,Suministro.COLUMN_ENTREGADO,
                        Suministro.COLUMN_ID_FECHA,Suministro.COLUMN_ID_PERSONA},
                Persona.COLUMN_ID + "=?",
                new String[]{String.valueOf(id_suministro)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            // prepare note object
            suministro = new Suministro(
                    cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ENTREGADO)),
                    getFecha(cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID_FECHA))),
                    getPersona(cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID_PERSONA)))
            );
        }
        // close the db connection
        cursor.close();

        return suministro;
    }

    public ArrayList<Suministro> getSuministroDiario(){
        Fecha fechaSum = getFechaActual();

        SQLiteDatabase db = this.getReadableDatabase();
        Suministro suministro = null;
        ArrayList<Suministro> suministros= new ArrayList<>();

        Cursor  cursor = db.rawQuery("select * from "+Suministro.TABLE_NAME+" where id_fecha = '"+fechaSum.getId_fecha()+"'",null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                suministro = new Suministro(
                        cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ENTREGADO)),
                        getFecha(cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID_FECHA))),
                        getPersona(cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID_PERSONA)))
                );

                suministros.add(suministro);
                cursor.moveToNext();
            }
        }
        // close the db connection
        cursor.close();
        return suministros;
    }

    public boolean checkDataBase(String Database_path) {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(Database_path, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            Log.e("Error", "No existe la base de datos " + e.getMessage());
        }
        return checkDB != null;
    }

    public boolean isTableExists(String nombreTabla) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isExist = false;
        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + nombreTabla + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        return isExist;
    }

    public boolean isDateExist(int dia, int mes, int year){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isExist = false;
        Cursor cursor = db.rawQuery("select * from fecha where dia = '" + dia + "' and mes = '"+mes+"' and year = '"+year+"'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        return isExist;
    }

    public boolean isExistReport(int mes, int year){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isExist = false;
        Cursor cursor = db.rawQuery( "select suministro.id_suministro, suministro.agua_entregada, fecha.mes, fecha.year, comunidad.id_comunidad from suministro "
                +"inner join fecha on suministro.id_fecha = fecha.id_fecha "
                +"inner join persona on suministro.id_persona = persona.id_persona "
                +"inner join comunidad on persona.id_comunidad = comunidad.id_comunidad "
                +"where fecha.mes ='" +mes+ "' and fecha.year = '"+year+"'",null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        return isExist;
    }

    public ArrayList<Estadistica> estadisticaMensual(int mes, int year){
        SQLiteDatabase db = this.getReadableDatabase();

        Estadistica estadistica = null;
        ArrayList<Estadistica> estadisticas= new ArrayList<>();

        Cursor cursor = db.rawQuery( "select suministro.id_suministro, suministro.agua_entregada, fecha.mes, fecha.year, comunidad.id_comunidad from suministro "
                +"inner join fecha on suministro.id_fecha = fecha.id_fecha "
                +"inner join persona on suministro.id_persona = persona.id_persona "
                +"inner join comunidad on persona.id_comunidad = comunidad.id_comunidad "
                +"where fecha.mes ='" +mes+ "' and fecha.year = '"+year+"'",null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                estadistica = new Estadistica(
                        cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ENTREGADO)),
                        cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_MES)),
                        cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_YEAR)),
                        cursor.getInt(cursor.getColumnIndex(Comunidad.COLUMN_ID))
                );


                estadisticas.add(estadistica);
                cursor.moveToNext();
            }
        }
        // close the db connection
        cursor.close();
        return estadisticas;
    }

    public ArrayList<Estadistica> estadisticaMensualComunidad(int mes, int year, int idComunidad){
        SQLiteDatabase db = this.getReadableDatabase();

        Estadistica estadistica = null;
        ArrayList<Estadistica> estadisticas= new ArrayList<>();

        Cursor cursor = db.rawQuery( "select suministro.id_suministro, suministro.agua_entregada, fecha.mes, fecha.year, comunidad.id_comunidad from suministro "
                +"inner join fecha on suministro.id_fecha = fecha.id_fecha "
                +"inner join persona on suministro.id_persona = persona.id_persona "
                +"inner join comunidad on persona.id_comunidad = comunidad.id_comunidad "
                +"where fecha.mes ='" +mes+ "' and fecha.year = '"+year+"' and  comunidad.id_comunidad ='"+idComunidad+"'",null);


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                estadistica = new Estadistica(
                        cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID)),
                        cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ENTREGADO)),
                        cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_MES)),
                        cursor.getInt(cursor.getColumnIndex(Fecha.COLUMN_YEAR)),
                        cursor.getInt(cursor.getColumnIndex(Comunidad.COLUMN_ID))
                );


                estadisticas.add(estadistica);
                cursor.moveToNext();
            }
        }
        // close the db connection
        cursor.close();
        return estadisticas;
    }
}
