package com.example.futbolproyecto.interfaces;

import com.example.futbolproyecto.model.EquipoModel;

import java.util.ArrayList;

public interface EquiposDAO {

    public boolean registrarEquipo(EquipoModel equipo);

    public boolean eliminarEquipo(EquipoModel equipo);

    public int actualizarEquipo(EquipoModel equipo);

    public ArrayList<EquipoModel> listarEquipos();
}
