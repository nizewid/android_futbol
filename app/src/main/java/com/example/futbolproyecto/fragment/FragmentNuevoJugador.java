package com.example.futbolproyecto.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.futbolproyecto.R;
import com.example.futbolproyecto.dao.DAOEquipoImpl;
import com.example.futbolproyecto.dao.DAOJugadorImpl;
import com.example.futbolproyecto.model.JugadorModel;


public class FragmentNuevoJugador extends Fragment {

    private JugadorModel jugador;
    private int id_jugador_actualizar;
    private EditText et_nombreJugador;
    private EditText et_edadJugador;
    private Switch sw_lesionado;
    private Spinner equiposSpinner,avatarSpinner;
    private Button btn_accionJugador;
    private String[] arrayAvatares = new String[]{"1","2","3","4","5"};
    private String[] arrayEquipos = new String[]{"Barcelona","Sevilla","Cadiz","Sporting"};

    public FragmentNuevoJugador() {
        // Required empty public constructor
    }


    public static FragmentNuevoJugador newInstance(String param1, String param2) {
        FragmentNuevoJugador fragment = new FragmentNuevoJugador();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.jugador_form,container,false);

        //Asignacion de capas;
        equiposSpinner = view.findViewById(R.id.sp_equipo);
        avatarSpinner = view.findViewById(R.id.sp_avatar);
        btn_accionJugador = view.findViewById(R.id.btn_accionJugador);
        et_nombreJugador = view.findViewById(R.id.et_nombreJugador);
        et_edadJugador = view.findViewById(R.id.et_edadJugador);
        sw_lesionado = view.findViewById(R.id.sw_lesionado);

        if(this.getArguments()!=null){
            btn_accionJugador.setText("Actualizar");
        }

        ArrayAdapter<String> equiposAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,arrayEquipos);

        ArrayAdapter<String> avatarAdapter = new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_item,arrayAvatares);

        equiposSpinner.setAdapter(equiposAdapter);
        avatarSpinner.setAdapter(avatarAdapter);

        btn_accionJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugador = null;


                boolean exito = false;
                DAOJugadorImpl daoJugador = new DAOJugadorImpl(view.getContext());
                try{
                    exito = daoJugador.registrarJugador(new JugadorModel(et_nombreJugador.getText().toString(),Integer.parseInt(et_edadJugador.getText().toString()),avatarSpinner.getSelectedItem().toString(),sw_lesionado.isActivated(),18));
                }catch(Exception e){
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(view.getContext(), String.valueOf(exito), Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}