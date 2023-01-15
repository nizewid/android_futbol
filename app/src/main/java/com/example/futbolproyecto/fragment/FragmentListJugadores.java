package com.example.futbolproyecto.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        //Inflando la vista
        View v = inflater.inflate(R.layout.fragment_list_jugadores, container, false);
        //Asignando elementos de la vista;
        lv_jugadores = v.findViewById(R.id.lv_listaJugadores);


        DAOJugadorImpl daoJugador = new DAOJugadorImpl(this.getContext());
        jugadoresArray = daoJugador.listarJugadores();

        //Construyendo el adaptador que se va a cargar en la ListView
        jugadorAdapter = new JugadorAdapter(this.getActivity(), jugadoresArray);
        lv_jugadores.setAdapter(jugadorAdapter);

        lv_jugadores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lv_jugadores.setSelection(position);
                //para pasar los parametros utilizamos el objeto Bundle;
                Bundle bundle = new Bundle();
                bundle.putInt("id", jugadoresArray.get(position).getId());
                bundle.putString("nombre", jugadoresArray.get(position).getNombre());
                bundle.putInt("edad", jugadoresArray.get(position).getEdad());
                bundle.putString("picture", jugadoresArray.get(position).getPicture());
                bundle.putBoolean("lesionado", jugadoresArray.get(position).isLesionado());
                bundle.putInt("id_equipo", jugadoresArray.get(position).getId_equipo());

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentNuevoJugador fn = new FragmentNuevoJugador();
                //Al crear el nuevo fragment necesitamos pasarle los parametros con SetArguments
                fn.setArguments(bundle);
                manager.beginTransaction()
                        .replace(R.id.fragment_container_view, fn)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }
        });
        lv_jugadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                JugadorModel jugadorAeliminar = jugadoresArray.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setMessage("Estas seguro de eliminar el Jugador:" + jugadorAeliminar.getNombre() + " con ID " + jugadorAeliminar.getId_equipo()).setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DAOJugadorImpl daoEquipo = new DAOJugadorImpl(view.getContext());
                                daoEquipo.eliminarJugador(jugadoresArray.get(position));

                                Toast.makeText(view.getContext(), " Se elimino correctamente ".concat(jugadorAeliminar.getNombre()), Toast.LENGTH_SHORT).show();
                            }

                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = dialog.create();
                alert.setTitle("Eliminar Jugador:");
                alert.show();
                return false;
            }
        });

        return v;
    }
}