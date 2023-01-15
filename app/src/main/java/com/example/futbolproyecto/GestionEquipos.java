package com.example.futbolproyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.futbolproyecto.fragment.FragmentListEquipos;
import com.example.futbolproyecto.fragment.FragmentNuevoEquipo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GestionEquipos extends AppCompatActivity {
    //elementos de la vista
    private Button btn_verEquipos;
    FloatingActionButton btn_agregarEquipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_equipos);

        //Referenciar botones a la vista
        btn_agregarEquipo = findViewById(R.id.btn_agregarJugador);
        btn_verEquipos = findViewById(R.id.btn_verJugadores);

        mostrarListado();


        btn_agregarEquipo.setOnClickListener(v -> {
            FragmentNuevoEquipo fragment = new FragmentNuevoEquipo();
            //Obtenemos la transaccion
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction
                    .replace(R.id.fragment_container_view, fragment)
                    .commit();

            //      Toast.makeText(GestionEquipos.this, "presionaste agregar", Toast.LENGTH_SHORT).show();
        });
        btn_verEquipos.setOnClickListener(v -> mostrarListado());


    }

    private void mostrarListado() {
        FragmentListEquipos equiposFragment = new FragmentListEquipos();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .replace(R.id.fragment_container_view, equiposFragment)
                .commit();
    }


}