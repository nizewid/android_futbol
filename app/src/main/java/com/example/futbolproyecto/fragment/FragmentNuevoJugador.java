package com.example.futbolproyecto.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.futbolproyecto.R;
import com.example.futbolproyecto.dao.DAOEquipoImpl;
import com.example.futbolproyecto.dao.DAOJugadorImpl;
import com.example.futbolproyecto.model.EquipoModel;
import com.example.futbolproyecto.model.JugadorModel;

import java.util.ArrayList;
import java.util.List;


public class FragmentNuevoJugador extends Fragment {

    int[] avatar_resource_id = {R.drawable.jugador1, R.drawable.jugador2, R.drawable.jugador3, R.drawable.jugador4, R.drawable.jugador5, R.drawable.jugador6};
    private JugadorModel jugador;
    private int id_jugador_actualizar;
    private ImageView img_avatar;
    private EditText et_nombreJugador;
    private EditText et_edadJugador;
    private Switch sw_lesionado;
    private Spinner equiposSpinner, avatarSpinner;
    private Button btn_accionJugador;
    private final String[] arrayAvatares = new String[]{"1", "2", "3", "4", "5", "6"};
    private ArrayList<EquipoModel> arrayEquipos;
    private String nombre;
    private int edad;
    private String picture;
    private boolean lesion;
    private int id_equipo;

    public FragmentNuevoJugador() {
        // Required empty public constructor
    }


    public static FragmentNuevoJugador newInstance() {
        FragmentNuevoJugador fragment = new FragmentNuevoJugador();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id_jugador_actualizar = getArguments().getInt("id");
            nombre = getArguments().getString("nombre");
            edad = getArguments().getInt("edad");
            picture = getArguments().getString("picture");
            lesion = getArguments().getBoolean("lesionado");
            id_equipo = getArguments().getInt("id_equipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.jugador_form, container, false);
        DAOEquipoImpl daoEquipo = new DAOEquipoImpl(view.getContext());
        List<String> listadoEquipos = new ArrayList<>();
        arrayEquipos = daoEquipo.listarEquipos();

        for (int i = 0; i <= arrayEquipos.size() - 1; i++) {
            listadoEquipos.add(arrayEquipos.get(i).getNombre());
        }


        //Asignacion de capas;
        equiposSpinner = view.findViewById(R.id.sp_equipo);
        avatarSpinner = view.findViewById(R.id.sp_avatar);
        btn_accionJugador = view.findViewById(R.id.btn_accionJugador);
        et_nombreJugador = view.findViewById(R.id.et_nombreJugador);
        et_edadJugador = view.findViewById(R.id.et_edadJugador);
        sw_lesionado = view.findViewById(R.id.sw_lesionado);
        img_avatar = view.findViewById(R.id.img_avatar);
        ArrayAdapter<String> equiposAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, listadoEquipos);
        ArrayAdapter<String> avatarAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, arrayAvatares);
        equiposSpinner.setAdapter(equiposAdapter);
        avatarSpinner.setAdapter(avatarAdapter);

        if (this.getArguments() != null) {

            et_nombreJugador.setText(nombre);
            et_edadJugador.setText(String.valueOf(edad));
            btn_accionJugador.setText("Actualizar");
            img_avatar.setImageResource(avatar_resource_id[Integer.valueOf(picture) - 1]);
            sw_lesionado.setChecked(lesion);
            equiposSpinner.setSelection(id_equipo - 1);
        }

        btn_accionJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jugador = null;
                boolean exito = false;


                String nombre = et_nombreJugador.getText().toString();
                int edad = Integer.parseInt(et_edadJugador.getText().toString());
                String avatar = avatarSpinner.getSelectedItem().toString();
                boolean lesionado = sw_lesionado.isChecked();
                int equipo_id = equiposSpinner.getSelectedItemPosition();

                String mensaje = comprobarCampos();

                if (btn_accionJugador.getText().toString().equalsIgnoreCase("Agregar") && mensaje.equalsIgnoreCase("OK")) {
                    DAOJugadorImpl daoJugador = new DAOJugadorImpl(view.getContext());
                    try {
                        exito = daoJugador.registrarJugador(new JugadorModel(nombre, edad, avatar, lesionado, equipo_id + 1));
                        if (exito == true) {
                            cerrarTeclado();
                            mostrarFragmentJugadores();
                            Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), mensaje, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());
                    }
                }
                if (btn_accionJugador.getText().toString().equalsIgnoreCase("Actualizar") && mensaje.equalsIgnoreCase("OK")) {
                    DAOJugadorImpl daoJugador = new DAOJugadorImpl(view.getContext());
                    int actualizados;
                    jugador = new JugadorModel(id_jugador_actualizar, nombre, edad, avatar, lesionado, equipo_id + 1);
                    actualizados = daoJugador.actualizarJugador(jugador);

                    mostrarFragmentJugadores();
                    Toast.makeText(view.getContext(), "Filas afectadas: " + actualizados, Toast.LENGTH_SHORT).show();
                }

                // Toast.makeText(view.getContext(), String.valueOf(exito), Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }

    private void mostrarFragmentJugadores() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentListJugadores listado = new FragmentListJugadores();
        manager.beginTransaction()
                .remove(this)
                .replace(R.id.fragment_container_view, listado)
                .addToBackStack(null)
                .commit();
    }

    public void cerrarTeclado() {

        View v = getActivity().getCurrentFocus();

        if (v != null) {

            InputMethodManager imm = (InputMethodManager) (getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    private String comprobarCampos() {
        String mensaje = "OK";
        return mensaje;
    }

}