package com.example.futbolproyecto.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.futbolproyecto.R;
import com.example.futbolproyecto.dao.DAOEquipoImpl;
import com.example.futbolproyecto.model.EquipoModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentListEquipos extends Fragment {
    SearchView searchView;
    ListView lv_listaEquipos;
    List<EquipoModel> listadoEquipos;
    List<EquipoModel> listaFiltrada;


    public FragmentListEquipos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listadoEquipos = new ArrayList<>();
        listaFiltrada = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //obtener la vista
        View view = inflater.inflate(R.layout.fragment_equipos_list, container, false);
        //asignando la variable al objeto de la UI
        lv_listaEquipos = view.findViewById(R.id.lv_equiposList);
        searchView = view.findViewById(R.id.txt_buscar);
        //obteniendo el objeto de acceso a la bd
        DAOEquipoImpl daoEquipo = new DAOEquipoImpl(view.getContext());
        //asignando al array los datos
        listadoEquipos = daoEquipo.listarEquipos();
        listaFiltrada = listadoEquipos;
        //creando el adaptador para la interfaz
        ArrayAdapter equiposArrayAdapter = new ArrayAdapter<EquipoModel>(view.getContext(), android.R.layout.simple_list_item_1, listadoEquipos);
        //
        lv_listaEquipos.setAdapter(equiposArrayAdapter);

//Click simple para editar
        lv_listaEquipos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //para pasar los parametros utilizamos el objeto Bundle;
                Bundle bundle = new Bundle();
                bundle.putInt("id", listadoEquipos.get(position).getId_equipo());
                bundle.putString("nombre", listadoEquipos.get(position).getNombre());
                bundle.putString("patrocinador", listadoEquipos.get(position).getPatrocinador());
                bundle.putString("presidente", listadoEquipos.get(position).getPresidente());
                bundle.putString("localidad", listadoEquipos.get(position).getLocalidad());
                bundle.putBoolean("activo", listadoEquipos.get(position).isActivo());

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentNuevoEquipo fn = new FragmentNuevoEquipo();
                //Al crear el nuevo fragment necesitamos pasarle los parametros con SetArguments
                fn.setArguments(bundle);
                manager.beginTransaction()
                        .replace(R.id.fragment_container_view, fn)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

            }
        });
//Click largo para eliminar
        lv_listaEquipos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EquipoModel equipoAeLiminar = listadoEquipos.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setMessage("Estas seguro de eliminar el equipo" + equipoAeLiminar.getNombre() + "con ID " + equipoAeLiminar.getId_equipo()).setCancelable(false)
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            //EN CASO DE QUE SEA POSITIVO
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DAOEquipoImpl daoEquipo = new DAOEquipoImpl(view.getContext());
                                daoEquipo.eliminarEquipo(listadoEquipos.get(position));

                                Toast.makeText(view.getContext(), " Se elimino correctamente ".concat(equipoAeLiminar.getNombre()), Toast.LENGTH_SHORT).show();
                            }

                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = dialog.create();
                alert.setTitle("Eliminar equipo:");
                alert.show();
                return false;
            }
        });
        return view;
    }


}