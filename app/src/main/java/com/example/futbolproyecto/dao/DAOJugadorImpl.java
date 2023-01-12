package com.example.futbolproyecto.dao;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.futbolproyecto.db.DatabaseOpenHelper;
import com.example.futbolproyecto.interfaces.JugadorDAO;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;

public class DAOJugadorImpl extends DatabaseOpenHelper implements JugadorDAO {
    public DAOJugadorImpl(@Nullable Context context) {
        super(context);
    }

    @Override
    public void registrarJugador(JugadorModel jugador) throws Exception {

    }

    @Override
    public void eliminarJugador(JugadorModel jugador) throws Exception {

    }

    @Override
    public void actualizarJugador(JugadorModel jugador) throws Exception {

    }

    @Override
    public ArrayList<JugadorModel> listarEquipos() throws Exception {
        return null;
    }
}
