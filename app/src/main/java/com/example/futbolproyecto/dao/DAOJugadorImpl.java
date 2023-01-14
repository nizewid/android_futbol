package com.example.futbolproyecto.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.futbolproyecto.db.DatabaseOpenHelper;
import com.example.futbolproyecto.db.DbConstants;
import com.example.futbolproyecto.interfaces.JugadorDAO;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;

public class DAOJugadorImpl extends DatabaseOpenHelper implements JugadorDAO {

    public DAOJugadorImpl(@Nullable Context context) {
        super(context);
    }

    @Override
    public boolean registrarJugador(JugadorModel jugador){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbConstants.COLUMN_NOMBRE_JUGADOR,jugador.getNombre());
        cv.put(DbConstants.COLUMN_EDAD,jugador.getEdad());
        cv.put(DbConstants.COLUMN_PICTURE,jugador.getPicture());
        cv.put(DbConstants.COLUMN_ID_EQUIPO,jugador.getId_equipo());
        cv.put(DbConstants.COLUMN_LESIONADO,jugador.isLesionado());

        long insert = db.insert(DbConstants.TABLA_JUGADORES, "", cv);
        db.close();
        if (insert==-1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void eliminarJugador(JugadorModel jugador)  {

    }

    @Override
    public void actualizarJugador(JugadorModel jugador)  {

    }

    @Override
    public ArrayList<JugadorModel> listarEquipos()  {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<JugadorModel> arrayJugadores = new ArrayList<>();
        String query = "SELECT * FROM "+DbConstants.TABLA_JUGADORES;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                int idJugador = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int edadJugador = cursor.getInt(2);
                String avatar = cursor.getString(3);
                boolean lesinado = cursor.getInt(4) ==1? true:false;
                int idEquipo = cursor.getInt(5);
                arrayJugadores.add(new JugadorModel(idJugador,nombre,edadJugador,avatar,lesinado,idEquipo));
            }while(cursor.moveToNext());
        }else{
        }
        cursor.close();
        db.close();

        return arrayJugadores;
    }
}
