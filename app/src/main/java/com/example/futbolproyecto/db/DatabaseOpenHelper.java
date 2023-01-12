package com.example.futbolproyecto.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.futbolproyecto.db.DbConstants.*;

//Extender de la clase SQLliteOpenHelper
public class DatabaseOpenHelper extends SQLiteOpenHelper {


    //Seleccion de constructor de la clase
    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, DB_NOMBRE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecutamos las querys para crear las tablas en la BD
        db.execSQL(queryEquipos);
        db.execSQL(queryJugadores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
