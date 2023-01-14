package com.example.futbolproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_equipos,btn_jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciamos los elementos de la vista.
        btn_equipos=findViewById(R.id.btn_equipo);
        btn_jugador=findViewById(R.id.btn_jugador);

        btn_equipos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el intent con dos parametros el contexto actual, y la clase que queremos cargar ;)
                Intent intent = new Intent(MainActivity.this,GestionEquipos.class);
                startActivity(intent);
            }
        });
        btn_jugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GestionJugador.class);
                startActivity(intent);
            }
        });
    }

}