package com.example.futbolproyecto.interfaces;

import com.example.futbolproyecto.model.EquipoModel;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;

public interface JugadorDAO {

    boolean registrarJugador(JugadorModel jugador);

    boolean eliminarJugador(JugadorModel jugador);

    int actualizarJugador(JugadorModel jugador);

    ArrayList<JugadorModel> listarJugadores();
}
