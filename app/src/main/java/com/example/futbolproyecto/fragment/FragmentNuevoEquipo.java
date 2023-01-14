package com.example.futbolproyecto.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.futbolproyecto.R;
import com.example.futbolproyecto.dao.DAOEquipoImpl;
import com.example.futbolproyecto.model.EquipoModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNuevoEquipo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNuevoEquipo extends Fragment {
    private EquipoModel equipo;
    private int id_equipo_actualizar;
    private EditText et_nombreEquipo;
    private EditText et_patrocinador;
    private EditText et_localidad;
    private EditText et_presidente;
    private Switch sw_activo;
    private Button btn_accionEquipo;

    private String nombre;
    private String patrocinador;
    private String presidente;
    private String localidad;
    private boolean activo;

    public FragmentNuevoEquipo() {
        // Required empty public constructor
    }

    public static FragmentNuevoEquipo newInstance() {
        FragmentNuevoEquipo fragment = new FragmentNuevoEquipo();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Capturando los datos del bundle;
            id_equipo_actualizar = getArguments().getInt("id");
            nombre = getArguments().getString("nombre");
            patrocinador = getArguments().getString("patrocinador");
            presidente = getArguments().getString("presidente");
            localidad = getArguments().getString("localidad");
            activo = getArguments().getBoolean("activo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nuevo, container, false);

        DAOEquipoImpl daoEquipo = new DAOEquipoImpl(view.getContext());
        btn_accionEquipo = view.findViewById(R.id.btn_accionEquipo);
        //btn_modificar = view.findViewById(R.id.btn_modificar); <-- ***Reemplazado por btn_accion***
        et_nombreEquipo = view.findViewById(R.id.et_nombreEquipo);
        et_patrocinador = view.findViewById(R.id.et_patrocinador);
        et_presidente = view.findViewById(R.id.et_presidente);
        et_localidad = view.findViewById(R.id.et_localidad);
        sw_activo = view.findViewById(R.id.sw_activo);

        if (this.getArguments() != null) {
            et_nombreEquipo.setText(nombre);
            et_patrocinador.setText(patrocinador);
            et_presidente.setText(presidente);
            et_localidad.setText(localidad);
            sw_activo.setChecked(activo);
            btn_accionEquipo.setText("Actualizar");
        }


        btn_accionEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equipo = null;
                String mensaje = comprobarCampos();
                if (btn_accionEquipo.getText().toString().equalsIgnoreCase("Agregar") && mensaje.equals("OK")) {

                    try {
                        equipo = new EquipoModel(et_nombreEquipo.getText().toString(), et_patrocinador.getText().toString(), et_presidente.getText().toString(), et_localidad.getText().toString(), sw_activo.isChecked());
                        boolean registrado = daoEquipo.registrarEquipo(equipo);
                        cerrarTeclado();

                        if (registrado == true) {
                            // caso correcto indicamos mediante Toast que fue agregado con exito y cambiamos el fragment container por la lista
                            Toast.makeText(view.getContext(), mensaje, Toast.LENGTH_SHORT).show();
                            mostrarFragmentLista();
                        } else {
                            Toast.makeText(view.getContext(), mensaje, Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(view.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(e.getMessage());
                    }
                }
                if (btn_accionEquipo.getText().toString().equals("Actualizar") && mensaje.equals("OK")) {
                    int actualizados;
                    equipo = new EquipoModel(id_equipo_actualizar, et_nombreEquipo.getText().toString(), et_patrocinador.getText().toString(), et_presidente.getText().toString(), et_localidad.getText().toString(), sw_activo.isChecked());
                    actualizados = daoEquipo.actualizarEquipo(equipo);

                    mostrarFragmentLista();
                    Toast.makeText(view.getContext(), "Filas afectadas: " + String.valueOf(actualizados), Toast.LENGTH_SHORT).show();
                }
                cerrarTeclado();
                Toast.makeText(view.getContext(), mensaje, Toast.LENGTH_SHORT).show();


            }
        });
        return view;
    }

    private String comprobarCampos() {
        String mensaje = "";
        int nombre = 0;  // 1
        int patrocinador = 0; //2
        int presidente = 0; //3
        int localidad = 0; //4
        if (et_nombreEquipo.getText().length() <= 2) {
            nombre = 1;
            mensaje = "Nombre equipo no puede tener menos de 3 Caracteres";
            //et_nombreEquipo.setBackgroundColor();
            et_nombreEquipo.setHintTextColor(getResources().getColor(R.color.red));
            et_nombreEquipo.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        if (et_patrocinador.getText().length() <= 2) {
            patrocinador = 2;
            mensaje = "patrocinador no puede tener menos de 3 Caracteres";
            et_patrocinador.setBackgroundColor(getResources().getColor(R.color.red));
            et_patrocinador.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        if (et_presidente.getText().length() <= 2) {
            presidente = 3;
            mensaje = "Presidente no puede tener menos de 3 Caracteres";
            et_presidente.setBackgroundColor(getResources().getColor(R.color.red));
            et_presidente.setBackgroundColor(getResources().getColor(R.color.grey));
        }
        if (et_localidad.getText().length() <= 2) {
            localidad = 4;
            mensaje = "Localidad no puede tener menos de 3 Caracteres";
            et_localidad.setBackgroundColor(getResources().getColor(R.color.red));
            et_localidad.setBackgroundColor(getResources().getColor(R.color.grey));
        }

        if ((nombre == 0) && (patrocinador == 0) && (presidente == 0) && (localidad == 0)) {
            mensaje = "OK";
        }
        return mensaje;
    }

    public void cerrarTeclado() {

        View v = getActivity().getCurrentFocus();

        if (v != null) {

            InputMethodManager imm = (InputMethodManager) (getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void mostrarFragmentLista() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentListEquipos listado = new FragmentListEquipos();
        manager.beginTransaction()
                .remove(this)
                .replace(R.id.fragment_container_view, listado)
                .addToBackStack(null)
                .commit();
    }

}