package com.example.futbolproyecto.interfaces;

import com.example.futbolproyecto.model.EquipoModel;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;

public interface JugadorDAO {

    public boolean registrarJugador(JugadorModel jugador);

    public void eliminarJugador(JugadorModel jugador);

    public void actualizarJugador(JugadorModel jugador);

    public ArrayList<JugadorModel> listarEquipos();
}
