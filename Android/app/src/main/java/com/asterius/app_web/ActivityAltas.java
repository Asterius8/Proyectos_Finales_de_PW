package com.asterius.app_web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import controlador.AnalizadorJSON;

public class ActivityAltas extends AppCompatActivity {

    //Atributos --------------------------------------------------------------------------------------------------------------------------------------
    private EditText num_control, nombre, paterno, materno, fecha_nac, num_telefono;
    private Spinner semestre, carrera;
    String num_controlD, nombreD, paternoD, maternoD, fecha_nacD, telefonoD, semestreD, carreraD;
    ArrayList<String> datos = new ArrayList<String> ();

    //Metodo OnCreate ---------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        num_control = findViewById(R.id.txt_num_control);
        nombre = findViewById(R.id.txt_nombre);
        paterno = findViewById(R.id.txt_paterno);
        materno = findViewById(R.id.txt_materno);
        fecha_nac = findViewById(R.id.txt_fecha_nac);
        num_telefono = findViewById(R.id.txt_num_telefono);
        semestre = findViewById(R.id.spn_semestre);
        carrera = findViewById(R.id.spn_carrera);

        String [] semestres = {"Seleccione una opcion...","1","2","3","4","5","6","7","8","9","10","11","12"};
        String [] carreras = {"Seleccione una opcion...","ISC", "IM", "IIA", "CP", "LA"};

        ArrayAdapter <String> adapterSemestres = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semestres);
        semestre.setAdapter(adapterSemestres);

        ArrayAdapter <String> adapterCarreras = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carreras);
        carrera.setAdapter(adapterCarreras);

    }

    //METODO VERIFICAR CONTENIDO DE CAMPOS--------------------------------------------------------------------------------------------------------------
    //IMPORTANTE!!! Falta comprobar que no exista el numero de control
    public boolean verificarCampos(String fecha_nacD){
        //Verifica que los campos no esten vacios
        if(!(num_control.getText().toString().isEmpty() && nombre.getText().toString().isEmpty() && paterno.getText().toString().isEmpty() && materno.getText().toString().isEmpty() && fecha_nac.getText().toString().isEmpty() && num_telefono.getText().toString().isEmpty() && semestre.getSelectedItemPosition()==0 && carrera.getSelectedItemPosition() == 0)){

            fecha_nacD = String.valueOf(fecha_nac.getText());
            byte contadorD = 0;

            for (int i = 0; i < fecha_nac.length(); i++) {

                if (fecha_nacD.charAt(i) == '-') {

                    contadorD++;

                }
            }

            if (contadorD == 2) {
                Log.i("MSJ", "giones confirmados");
                String[] fecha = new String[3];
                fecha = fecha_nacD.split("-");

                if (fecha[0].length() == 4) {
                    Log.i("MSJ", "cuatro digitos confirmados");
                    if (fecha[1].length() == 2 && Integer.parseInt(fecha[1]) >= 1 && Integer.parseInt(fecha[1]) <= 12) {
                        Log.i("MSJ", "digitos confirmados y limite de 1 a 12");

                        if (fecha[2].length() == 2 && Integer.parseInt(fecha[2]) >= 1 && Integer.parseInt(fecha[2]) <= 31) {
                            Log.i("MSJ", "digitos confirmados y limite de 1 a 31");

                            //if (BuscarNumControlIgualDAO(String.valueOf(num_control))) {

                            return  true;

                            //} else {

                                //JOptionPane.showMessageDialog(this, "Numero de control repetido");

                            //}

                        } else {
                            Toast.makeText(this,"Sea tan amable de ingresar la fecha en formato (aaaa-mm-dd)", Toast.LENGTH_LONG).show();
                            return false;
                        }

                    } else {
                        Toast.makeText(this,"Sea tan amable de ingresar la fecha en formato (aaaa-mm-dd)", Toast.LENGTH_LONG).show();
                        return false;
                    }

                } else {
                    Toast.makeText(this,"Sea tan amable de ingresar la fecha en formato (aaaa-mm-dd)", Toast.LENGTH_LONG).show();
                    return false;
                }

            }

        }

        return false;

    }

    //METODO para agregar -----------------------------------------------------------------------------------------------------------------------------
    public void altaAlumno(View view){

        if(verificarCampos(fecha_nacD)){

            datos.add( num_control.getText().toString() );
            datos.add( String.valueOf(nombre.getText()) );
            datos.add( String.valueOf(paterno.getText()) ) ;
            datos.add( String.valueOf(materno.getText()) );
            datos.add( String.valueOf(fecha_nac.getText()) );
            datos.add( "19" );
            datos.add( String.valueOf(num_telefono.getText()) );
            datos.add( semestre.getSelectedItem().toString() );
            datos.add( String.valueOf(carrera.getSelectedItem()));

            //verificar si el WIFI esta prendido
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            //Verificar que este conectado a una red
            Network network = cm.getActiveNetwork();

            if(network != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null){

                String url = "http://10.0.2.2:80/Semestre_Ago_Dic_2024/App_ABCC_Escuela/api_rest_android_escuela/api_mysql_altas.php";

                String metodo = "POST";

                AnalizadorJSON analizadorJSON = new AnalizadorJSON();

                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        JSONObject jsonObject = analizadorJSON.peticionHTTP(url, metodo, datos);
                        Log.i("MSJ ->","Insercion Correcta");
                        try {

                            String res = jsonObject.getString("alta");

                            if(res.equals("exito")){

                                Log.i("MSJ ->","Insercion Correcta");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(getBaseContext(), "Alumno agregado correctamente", Toast.LENGTH_LONG).show();

                                    }
                                });

                            }

                        } catch (JSONException e) {

                            throw new RuntimeException(e);

                        }

                    }
                }).start();

            }


            Toast.makeText(this, "Si esta correcto", Toast.LENGTH_LONG).show();

        }else {

            Toast.makeText(this, "No esta correcto", Toast.LENGTH_LONG).show();

        }



    }

}
