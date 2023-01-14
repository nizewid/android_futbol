package com.example.futbolproyecto.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.futbolproyecto.R;
import com.example.futbolproyecto.adaptadores.JugadorAdapter;
import com.example.futbolproyecto.dao.DAOJugadorImpl;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;


public class FragmentListJugadores extends Fragment {

    ListView lv_jugadores;
    ArrayList<JugadorModel> jugadoresArray;
    JugadorAdapter jugadorAdapter;


    public FragmentListJugadores() {
        // Required empty public constructor
    }

    public static FragmentListJugadores newInstance(String param1, String param2) {
        FragmentListJugadores fragment = new FragmentListJugadores();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_jugadores, container, false);
        DAOJugadorImpl daoJugador = new DAOJugadorImpl(this.getContext());
        jugadoresArray = daoJugador.listarEquipos();
        lv_jugadores = v.findViewById(R.id.lv_listaJugadores);
        jugadorAdapter = new JugadorAdapter(this.getActivity(),jugadoresArray);

        lv_jugadores.setAdapter(jugadorAdapter);



        return v;
    }
}