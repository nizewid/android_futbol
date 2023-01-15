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
    public boolean registrarJugador(JugadorModel jugador) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbConstants.COLUMN_NOMBRE_JUGADOR, jugador.getNombre());
        cv.put(DbConstants.COLUMN_EDAD, jugador.getEdad());
        cv.put(DbConstants.COLUMN_PICTURE, jugador.getPicture());
        cv.put(DbConstants.COLUMN_ID_EQUIPO, jugador.getId_equipo());
        cv.put(DbConstants.COLUMN_LESIONADO, jugador.isLesionado());

        long insert = db.insert(DbConstants.TABLA_JUGADORES, "", cv);
        db.close();
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean eliminarJugador(JugadorModel jugador) {
        boolean accion;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + DbConstants.TABLA_JUGADORES + " where " + DbConstants.COLUMN_ID_JUGADOR + " =" + jugador.getId();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()==false) {
            accion = false;
        } else {
            accion = true;
        }
        cursor.close();
        db.close();
        return accion;
    }

    @Override
    public int actualizarJugador(JugadorModel jugador) {
        int actualizado;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valoresNuevos = new ContentValues();
        valoresNuevos.put(DbConstants.COLUMN_NOMBRE_JUGADOR, jugador.getNombre());
        valoresNuevos.put(DbConstants.COLUMN_EDAD, jugador.getEdad());
        valoresNuevos.put(DbConstants.COLUMN_PICTURE, jugador.getPicture());
        valoresNuevos.put(DbConstants.COLUMN_LESIONADO, jugador.isLesionado());
        valoresNuevos.put(DbConstants.COLUMN_ID_EQUIPO, jugador.getId_equipo());

        String[] argumentosParaActualizar = {String.valueOf(jugador.getId())};

        actualizado = db.update(DbConstants.TABLA_JUGADORES, valoresNuevos, DbConstants.COLUMN_ID_EQUIPO + " = ?", argumentosParaActualizar);
        db.close();
        return actualizado;
    }

    @Override
    public ArrayList<JugadorModel> listarJugadores() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<JugadorModel> arrayJugadores = new ArrayList<>();
        String query = "SELECT * FROM " + DbConstants.TABLA_JUGADORES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idJugador = cursor.getInt(0);
                String nombre = cursor.getString(1);
                int edadJugador = cursor.getInt(2);
                String avatar = cursor.getString(3);
                boolean lesinado = cursor.getInt(4) == 1 ? true : false;
                int idEquipo = cursor.getInt(5);
                arrayJugadores.add(new JugadorModel(idJugador, nombre, edadJugador, avatar, lesinado, idEquipo));
            } while (cursor.moveToNext());
        } else {
        }
        cursor.close();
        db.close();

        return arrayJugadores;
    }
}
