package com.asterius.app_web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import controlador.AnalizadorJSON;

public class ActivityConsultas extends AppCompatActivity {

    private EditText num_control, nombre, paterno, materno, fecha_nac, edad, num_telefono;
    RadioButton num_controlr, nombrer, paternor, maternor, fecha_nacr, edadr, num_telefonor, semestrer, carrerar;
    private Spinner semestre, carrera;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<String> datosvacios = new ArrayList<String> ();
    ArrayList<String> datos = new ArrayList<String> ();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        num_control = findViewById(R.id.txt_num_control_u);
        nombre = findViewById(R.id.txt_nombre_u);
        paterno = findViewById(R.id.txt_paterno_u);
        materno = findViewById(R.id.txt_materno_u);
        fecha_nac = findViewById(R.id.txt_fecha_nac_u);
        edad = findViewById(R.id.txt_edad_u);
        num_telefono = findViewById(R.id.txt_num_telefono_u);
        semestre = findViewById(R.id.spn_semestre_u);
        carrera = findViewById(R.id.spn_carrera_u);

        num_controlr = findViewById(R.id.rbn_num_control);
        nombrer = findViewById(R.id.rbn_nombre);
        paternor = findViewById(R.id.rbn_paterno);
        maternor = findViewById(R.id.rbn_materno);
        fecha_nacr = findViewById(R.id.rbn_fecha_nac);
        edadr = findViewById(R.id.rbn_edad);
        num_telefonor = findViewById(R.id.rbn_num_telefono);
        semestrer = findViewById(R.id.rbn_semestre);
        carrerar = findViewById(R.id.rbn_carrera);

        String [] semestres = {"Seleccione una opcion...","1","2","3","4","5","6","7","8","9","10","11","12"};
        String [] carreras = {"Seleccione una opcion...","ISC", "IM", "IIA", "CP", "LA"};

        for (int i = 0; i < 9; i++) {
            datosvacios.add("");
        }

        ArrayAdapter<String> adapterSemestres = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semestres);
        semestre.setAdapter(adapterSemestres);

        ArrayAdapter <String> adapterCarreras = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carreras);
        carrera.setAdapter(adapterCarreras);

        recyclerView = findViewById(R.id.lista_alumnos);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void consultaFiltro(View view){

        if(num_controlr.isChecked()){

            datos.add(String.valueOf((num_control.getText())));
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            consultarBD(datos);


        }else if(nombrer.isChecked()){

            datos.add("");
            datos.add(String.valueOf(nombre.getText()));
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            consultarBD(datos);

        }else if(paternor.isChecked()){

            datos.add("");
            datos.add("");
            datos.add(String.valueOf(paterno.getText()));
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            consultarBD(datos);

        }else if(maternor.isChecked()){

            datos.add("");
            datos.add("");
            datos.add("");
            datos.add(String.valueOf(materno.getText()));
            datos.add("S");
            datos.add("");
            datos.add("");
            datos.add("");
            datos.add("");
            consultarBD(datos);

        }else if(fecha_nacr.isChecked()){
            String fecha_nacD;

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

                            datos.add("");datos.add("");datos.add("");datos.add("");datos.add(String.valueOf(fecha_nac.getText()));datos.add("");datos.add("");datos.add("");datos.add("");
                            Log.i("MSJ", String.valueOf(datos.size()));
                            consultarBD(datos);

                        } else {
                            Toast.makeText(this,"Sea tan amable de ingresar la fecha en formato (aaaa-mm-dd)", Toast.LENGTH_LONG).show();

                        }

                    } else {
                        Toast.makeText(this,"Sea tan amable de ingresar la fecha en formato (aaaa-mm-dd)", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(this,"Sea tan amable de ingresar la fecha en formato (aaaa-mm-dd)", Toast.LENGTH_LONG).show();

                }



            }



        }else if(edadr.isChecked()){

            datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add(String.valueOf(edad));datos.add("");datos.add("");datos.add("");
            Log.i("MSJ", String.valueOf(datos.size()));
            consultarBD(datos);

        }else if(num_telefonor.isChecked()){

        }else if(semestrer.isChecked()){

            datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add(semestre.getSelectedItem().toString());datos.add("");
            Log.i("MSJ", String.valueOf(datos.size()));
            consultarBD(datos);


        }else if(carrerar.isChecked()){

            datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add(carrera.getSelectedItem().toString());
            Log.i("MSJ", String.valueOf(datos.size()));
            consultarBD(datos);

        }else {

            datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");datos.add("");
            Log.i("MSJ", String.valueOf(datos.size()));
            consultarBD(datos);

        }

        Toast.makeText(this,"Se encontraron estos resultados", Toast.LENGTH_LONG).show();

        datos.clear();

    }

    public void consultarBD(ArrayList<String> datosx){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        Network network = cm.getActiveNetwork();

        if(network != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null){

            String url = "http://10.0.2.2:80/Semestre_Ago_Dic_2024/App_ABCC_Escuela/api_rest_android_escuela/api_mysql_consultas.php";

            String metodo = "POST";

            AnalizadorJSON analizadorJSON = new AnalizadorJSON();

            new Thread(new Runnable() {
                @Override
                public void run() {

                    JSONObject jsonObject = analizadorJSON.peticionHTTP(url, metodo, datosx);

                    try {

                        String res = jsonObject.getString("consulta");

                        if(res.equals("exito")){

                            JSONArray arreglo = jsonObject.getJSONArray("alumnos");//arreglo de json obtenido con los alumnos que concuerdan con la busqueda
                            Log.i("COSo", String.valueOf(arreglo.length()));
                            ArrayList<String> arrayList = new ArrayList<>();

                            String[] split = new String[9 * arreglo.length()] ;

                            for (int i = 0; i < arreglo.length(); i++) {

                                split = (arreglo.getString(i).toString()).split(",");

                                for(int j = 0; j < split.length; j++){

                                    int posicion = split[j].indexOf(':') + 1;
                                    split[j] = split[j].substring(posicion);
                                    split[j] = split[j].replaceAll("\"", "");
                                    split[j] = split[j].replace("}", "");

                                }

                                arrayList.add(Arrays.toString(split));

                            }



                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    adapter = new CustomAdapter2(arrayList);
                                    recyclerView.setAdapter(adapter);

                                    Toast.makeText(getBaseContext(), "Alumno modificado correctamente", Toast.LENGTH_LONG).show();

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

    public void menajedeRBN(View view){

        if (num_controlr.isChecked()) {

            nombre.setEnabled(false);
            paterno.setEnabled(false);
            materno.setEnabled(false);
            fecha_nac.setEnabled(false);
            edad.setEnabled(false);
            num_telefono.setEnabled(false);

        } else if (nombrer.isChecked()) {
            num_control.setEnabled(false);
            paterno.setEnabled(false);
            materno.setEnabled(false);
            fecha_nac.setEnabled(false);
            edad.setEnabled(false);
            num_telefono.setEnabled(false);

        } else if (paternor.isChecked()) {
            num_control.setEnabled(false);
            nombre.setEnabled(false);
            materno.setEnabled(false);
            fecha_nac.setEnabled(false);
            edad.setEnabled(false);
            num_telefono.setEnabled(false);

        } else if (maternor.isChecked()) {
            num_control.setEnabled(false);
            nombre.setEnabled(false);
            paterno.setEnabled(false);
            fecha_nac.setEnabled(false);
            edad.setEnabled(false);
            num_telefono.setEnabled(false);

        } else if (fecha_nacr.isChecked()) {
            num_control.setEnabled(false);
            nombre.setEnabled(false);
            paterno.setEnabled(false);
            materno.setEnabled(false);
            edad.setEnabled(false);
            num_telefono.setEnabled(false);

        } else if (edadr.isChecked()) {
            num_control.setEnabled(false);
            nombre.setEnabled(false);
            paterno.setEnabled(false);
            materno.setEnabled(false);
            fecha_nac.setEnabled(false);
            num_telefono.setEnabled(false);

        } else if (num_telefonor.isChecked()) {
            num_control.setEnabled(false);
            nombre.setEnabled(false);
            paterno.setEnabled(false);
            materno.setEnabled(false);
            fecha_nac.setEnabled(false);
            edad.setEnabled(false);

        } else if (semestrer.isChecked()) {
            // El RadioButton "semestrer" está seleccionado

        } else if (carrerar.isChecked()) {
            // El RadioButton "carrerar" está seleccionado

        } else {
            num_control.setEnabled(false);
            nombre.setEnabled(false);
            paterno.setEnabled(false);
            materno.setEnabled(false);
            fecha_nac.setEnabled(false);
            edad.setEnabled(false);
            num_telefono.setEnabled(false);

        }

    }


}

class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder>{

    private ArrayList<String> localDataSet;


    //Clase INTERNA
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;


        public ViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.textView_recycler);
        }

        public TextView getTextView(){
            return textView;
        }


    }

    public CustomAdapter2(ArrayList<String> dataset){
        localDataSet = dataset;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_recyclerview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.getTextView().setText( localDataSet.get(position).toString() );

    }

    @Override
    public int getItemCount() {

        return localDataSet.size();

    }

}//CustomAdapter
