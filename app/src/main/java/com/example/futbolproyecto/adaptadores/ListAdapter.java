package com.example.futbolproyecto.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.futbolproyecto.R;
import com.example.futbolproyecto.model.EquipoModel;

import java.util.List;

public class ListAdapter extends ArrayAdapter<EquipoModel> {

    private List<EquipoModel> mLista;
    private Context mContext;
    private int resoruceLayout;


    public ListAdapter(@NonNull Context context, int resource, @NonNull List<EquipoModel> equiposList) {
        super(context, resource, equiposList);
        this.mLista=equiposList;
        this.mContext= context;
        this.resoruceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_row,null);
            EquipoModel equipo = mLista.get(position);

            ImageView imagen = view.findViewById(R.id.row_img_view);

            TextView textoId = view.findViewById(R.id.row_txt_Id);
            textoId.setText(equipo.getId_equipo());

            TextView textoNombre = view.findViewById(R.id.row_txt_nombre);
            textoNombre.setText(equipo.getNombre());

            TextView textoPresidente = view.findViewById(R.id.row_txt_presidente);
            textoPresidente.setText(equipo.getPresidente());


        }
        return null;
    }


}
