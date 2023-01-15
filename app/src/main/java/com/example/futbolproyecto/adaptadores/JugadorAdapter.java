package com.example.futbolproyecto.adaptadores;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futbolproyecto.R;
import com.example.futbolproyecto.dao.DAOEquipoImpl;
import com.example.futbolproyecto.dao.DAOJugadorImpl;
import com.example.futbolproyecto.model.EquipoModel;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;
import java.util.List;

public class JugadorAdapter extends BaseAdapter {
    DAOJugadorImpl daoJugador;
    Activity act_GestionJugador;
    ArrayList<JugadorModel> arrayJugadores;
    List<EquipoModel> listadoEquipos;
    List<String> equipoString;

    int avatar_resource_id[] = {R.drawable.jugador1, R.drawable.jugador2, R.drawable.jugador3, R.drawable.jugador4, R.drawable.jugador5, R.drawable.jugador6};

    public JugadorAdapter(Activity act_GestionJugador, ArrayList<JugadorModel> arrayJugadores) {
        this.act_GestionJugador = act_GestionJugador;
        this.arrayJugadores = arrayJugadores;
    }

    @Override
    public int getCount() {
        return arrayJugadores.size();
    }

    @Override
    public JugadorModel getItem(int position) {
        return arrayJugadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return arrayJugadores.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creo la vista
        View jugadorRow;
        // es necesario Inflarla. utilizo la activity y utilizo el servicio layout inflater es necesario castearlo
        LayoutInflater inflater = (LayoutInflater) act_GestionJugador.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Especificamos cual es layout que deseamos inflar
        jugadorRow = inflater.inflate(R.layout.jugador_row, parent, false);
        //Vinculando
        TextView id_jugador = jugadorRow.findViewById(R.id.tv_idJugador);
        TextView jugadorNombre = jugadorRow.findViewById(R.id.tv_Nombre);
        ImageView iv_icon = jugadorRow.findViewById(R.id.iv_avatar);
        TextView edad = jugadorRow.findViewById(R.id.tv_edad);
        TextView nombreEquipo = jugadorRow.findViewById(R.id.tv_equipo);
        Switch sw_lesionado = jugadorRow.findViewById(R.id.sw_lesion);
        //Array de nombre de los equipos;
        DAOEquipoImpl daoEquipo = new DAOEquipoImpl(act_GestionJugador.getApplicationContext());
        listadoEquipos = daoEquipo.listarEquipos();
        listaEquipos(listadoEquipos);


        try {
            //jugador Por fila
            JugadorModel j = this.getItem(position);
            id_jugador.setText(String.valueOf(j.getId()));
            jugadorNombre.setText(j.getNombre());
            edad.setText(String.valueOf(j.getEdad()));
            nombreEquipo.setText(equipoString.get(Integer.valueOf(j.getId_equipo())-1));
            sw_lesionado.setChecked(j.isLesionado());
            iv_icon.setImageResource(avatar_resource_id[Integer.parseInt(j.getPicture())]-1);

        } catch (Exception e) {
           // Toast.makeText(act_GestionJugador,e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return jugadorRow;
    }
    private List<String> listaEquipos(List<EquipoModel> arrayModel){
        equipoString = new ArrayList<>();
        for (int i = 0; i < arrayModel.size(); i++) {
            equipoString.add(listadoEquipos.get(i).getNombre());
        }
        return equipoString;
    }
}
