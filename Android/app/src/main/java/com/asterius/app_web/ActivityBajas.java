package com.asterius.app_web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import controlador.AnalizadorJSON;

public class ActivityBajas extends AppCompatActivity {

    private EditText num_control;
    ArrayList<String> datos = new ArrayList<String> ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        num_control = findViewById(R.id.txt_num_control_b);

    }

    public void bajaAlumno(View view){

        datos.add(num_control.getText().toString());
        datos.add("");
        datos.add("");
        datos.add("");
        datos.add("");
        datos.add("");
        datos.add("");
        datos.add("");
        datos.add("");


        if(!(num_control.getText().toString().isEmpty())){

            //verificar si el WIFI esta prendido
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            //Verificar que este conectado a una red
            Network network = cm.getActiveNetwork();

            if(network != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null){

                String url = "http://10.0.2.2:80/Semestre_Ago_Dic_2024/App_ABCC_Escuela/api_rest_android_escuela/api_mysql_bajas.php";

                String metodo = "POST";

                AnalizadorJSON analizadorJSON = new AnalizadorJSON();

                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        JSONObject jsonObject = analizadorJSON.peticionHTTP(url, metodo, datos);

                        try {

                            String res = jsonObject.getString("mensaje");

                            if(res.equals("El registro fue eliminado.")){

                                Log.i("MSJ ->","Insercion Correcta");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(getBaseContext(), "Alumno eliminado correctamente", Toast.LENGTH_LONG).show();

                                    }
                                });

                            }

                        } catch (JSONException e) {

                            throw new RuntimeException(e);

                        }

                    }
                }).start();

            }

        }

    }

}
