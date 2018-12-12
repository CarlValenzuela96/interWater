package com.interwater.nigaca.interwater.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.interwater.nigaca.interwater.Models.Comunidad;
import com.interwater.nigaca.interwater.Models.Fecha;
import com.interwater.nigaca.interwater.Models.Persona;
import com.interwater.nigaca.interwater.Models.Suministro;

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
        values.put(Persona.COLUMN_ID_COM,persona.getComunidad().getId_comunidad());

        //validar si la persona esta o no.
        long id = db.insert(Persona.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long insertSuministro(Suministro suministro){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Suministro.COLUMN_CORRESPONDIENTE,suministro.getAgua_corresponde());
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

    public Comunidad getComunidad(int id_comunidad) {
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

    public Persona getPersona(int rut){
        SQLiteDatabase db = this.getReadableDatabase();
        Persona persona = null;

        Cursor cursor = db.query(Persona.TABLE_NAME,
                new String[]{Persona.COLUMN_ID,Persona.COLUMN_RUT,Persona.COLUMN_NOMBRE,Persona.COLUMN_PAT_APELLIDO,
                        Persona.COLUMN_MAT_APELLIDO,Persona.COLUMN_ID_COM},
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
                    getComunidad(cursor.getInt(cursor.getColumnIndex(Persona.COLUMN_ID_COM)))
            );
        }
        // close the db connection
        cursor.close();

        return persona;
    }

    public Suministro getSuministro(int id_suministro){
        SQLiteDatabase db = this.getReadableDatabase();
        Suministro suministro = null;

        Cursor cursor = db.query(Persona.TABLE_NAME,
                new String[]{Suministro.COLUMN_ID,Suministro.COLUMN_CORRESPONDIENTE,Suministro.COLUMN_ENTREGADO,
                        Suministro.COLUMN_ID_FECHA,Suministro.COLUMN_ID_PERSONA},
                Persona.COLUMN_ID + "=?",
                new String[]{String.valueOf(id_suministro)}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();

            // prepare note object
            suministro = new Suministro(
                    cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_CORRESPONDIENTE)),
                    cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ENTREGADO)),
                    getFecha(cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID_FECHA))),
                    getPersona(cursor.getInt(cursor.getColumnIndex(Suministro.COLUMN_ID_PERSONA)))
            );
        }
        // close the db connection
        cursor.close();

        return suministro;
    }

}
