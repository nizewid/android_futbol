package com.example.futbolproyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.futbolproyecto.fragment.FragmentListEquipos;
import com.example.futbolproyecto.fragment.FragmentListJugadores;
import com.example.futbolproyecto.fragment.FragmentNuevoJugador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GestionJugador extends AppCompatActivity{
    private ListView lv_Jugadores;
    private Button btn_verJugadores;
    FloatingActionButton btn_agregarJugador;
    
@Override
protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gestion_jugador);
    
    btn_agregarJugador = findViewById(R.id.btn_agregarJugador);
    btn_verJugadores = findViewById(R.id.btn_verJugadores);
    
    btn_verJugadores.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(GestionJugador.this, "Has pulsado ver", Toast.LENGTH_SHORT).show();
        }
    });
    btn_agregarJugador.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentNuevoJugador jugadorFragment = new FragmentNuevoJugador();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_view,jugadorFragment);
            fragmentTransaction.commit();
            Toast.makeText(GestionJugador.this, "presionaste agregar", Toast.LENGTH_SHORT).show();
        }
    });
    btn_verJugadores.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentListJugadores jugadoresFragment = new FragmentListJugadores();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_view,jugadoresFragment);
            fragmentTransaction.commit();
            Toast.makeText(GestionJugador.this, "presionaste listar", Toast.LENGTH_SHORT).show();
        }
    });
}

}
