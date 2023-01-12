package com.example.futbolproyecto.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.futbolproyecto.GestionEquipos;
import com.example.futbolproyecto.R;
import com.example.futbolproyecto.dao.DAOEquipoImpl;
import com.example.futbolproyecto.model.EquipoModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNuevo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNuevo extends Fragment {
    EquipoModel equipo;
    EditText et_nombreEquipo;
    EditText et_patrocinador;
    EditText et_localidad;
    EditText et_presidente;
    Switch sw_activo;
    Button btn_altaEquipo;

    String nombre;
    String patrocinador;
    String presidente;
    String localidad;
    boolean activo;

    public FragmentNuevo() {
        // Required empty public constructor
    }

    public static FragmentNuevo newInstance() {
        FragmentNuevo fragment = new FragmentNuevo();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            //Capturando los datos del bundle;
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

        btn_altaEquipo = view.findViewById(R.id.btn_altaEquipo);
        et_nombreEquipo = view.findViewById(R.id.et_nombreEquipo);
        et_patrocinador = view.findViewById(R.id.et_patrocinador);
        et_presidente = view.findViewById(R.id.et_presidente);
        et_localidad = view.findViewById(R.id.et_localidad);
        sw_activo = view.findViewById(R.id.sw_activo);

        if(this.getArguments()!=null){
            et_nombreEquipo.setText(nombre);
            et_patrocinador.setText(patrocinador);
            et_presidente.setText(presidente);
            et_localidad.setText(localidad);
            sw_activo.setChecked(activo);
            btn_altaEquipo.setText("Editar");
        }





        btn_altaEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAOEquipoImpl daoEquipo = new DAOEquipoImpl(view.getContext());
                equipo = null;
                try {
                    equipo = new EquipoModel(-1, et_nombreEquipo.getText().toString(), et_patrocinador.getText().toString(), et_presidente.getText().toString(), et_localidad.getText().toString(), sw_activo.isChecked());
                    boolean registrado = daoEquipo.registrarEquipo(equipo);
                    cerrarTeclado();
                    // caso correcto cambiamos el fragment container por la lista
                    if (registrado == true) {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        EquiposListFragment listado = new EquiposListFragment();
                        manager.beginTransaction()
                                .replace(R.id.fragment_container_view, listado)
                                .addToBackStack(null)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .commit();
                    }
                    // indicamos que fue registrado con un toast
                    Toast.makeText(v.getContext(), equipo.toString() + registrado, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });
        return view;
    }

    public void cerrarTeclado() {

        View v = getActivity().getCurrentFocus();

        if (v != null) {

            InputMethodManager imm = (InputMethodManager) (getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}