package com.example.futbolproyecto.interfaces;

import com.example.futbolproyecto.model.EquipoModel;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;

public interface JugadorDAO {

    public void registrarJugador(JugadorModel jugador) throws Exception;

    public void eliminarJugador(JugadorModel jugador) throws Exception;

    public void actualizarJugador(JugadorModel jugador) throws Exception;

    public ArrayList<JugadorModel> listarEquipos() throws Exception;
}
