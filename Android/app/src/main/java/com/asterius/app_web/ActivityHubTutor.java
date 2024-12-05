package com.asterius.app_web;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityHubTutor extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub_tutor);

    }

    public void abrirAltas(View v){//Metodo para abrir la ActivityAltas

        Intent i = new Intent(this, ActivityAltas.class);
        startActivity(i);

    }

    public void abrirBajas(View v){//Metodo para abrir la ActivityAltas

        Intent i = new Intent(this, ActivityBajas.class);
        startActivity(i);

    }

    public void abrirCambios(View v){//Metodo para abrir la ActivityAltas

        Intent i = new Intent(this, ActivityCambios.class);
        startActivity(i);

    }

    public void abrirConsultas(View v){//Metodo para abrir la ActivityAltas

        Intent i = new Intent(this, ActivityConsultas.class);
        startActivity(i);

    }
}
