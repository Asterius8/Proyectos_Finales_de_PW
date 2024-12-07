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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import controlador.AnalizadorJSON;

public class ActivityCambios extends AppCompatActivity implements CustomAdapter.OnAlumnoClickListener{
    private EditText num_control, nombre, paterno, materno, fecha_nac, edad, num_telefono;
    private Spinner semestre, carrera;
    String num_controlD, nombreD, paternoD, maternoD, fecha_nacD, telefonoD, semestreD, carreraD;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<String> datos = new ArrayList<String> ();
    ArrayList<String> datosvacios = new ArrayList<String> ();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        num_control = findViewById(R.id.txt_num_control_m);
        nombre = findViewById(R.id.txt_nombre_m);
        paterno = findViewById(R.id.txt_paterno_m);
        materno = findViewById(R.id.txt_materno_m);
        fecha_nac = findViewById(R.id.txt_fecha_nac_m);
        edad = findViewById(R.id.txt_edad_m);
        num_telefono = findViewById(R.id.txt_num_telefono_m);
        semestre = findViewById(R.id.spn_semestre_m);
        carrera = findViewById(R.id.spn_carrera_m);

        String [] semestres = {"Seleccione una opcion...","1","2","3","4","5","6","7","8","9","10","11","12"};
        String [] carreras = {"Seleccione una opcion...","ISC", "IM", "IIA", "CP", "LA"};

        for (int i = 0; i < 9; i++) {
            datosvacios.add("");
        }

        ArrayAdapter<String> adapterSemestres = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semestres);
        semestre.setAdapter(adapterSemestres);

        ArrayAdapter <String> adapterCarreras = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carreras);
        carrera.setAdapter(adapterCarreras);

        this.consultarBD(datosvacios);

        recyclerView = findViewById(R.id.lista_alumnos);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void modificarAlumno(View v){

        if(verificarCampos(fecha_nacD)){

            datos.add( num_control.getText().toString() );
            datos.add( String.valueOf(nombre.getText()) );
            datos.add( String.valueOf(paterno.getText()) ) ;
            datos.add( String.valueOf(materno.getText()) );
            datos.add( String.valueOf(fecha_nac.getText()) );
            datos.add( edad.getText().toString() );
            datos.add( String.valueOf(num_telefono.getText()) );
            datos.add( semestre.getSelectedItem().toString() );
            datos.add( String.valueOf(carrera.getSelectedItem()));

            //verificar si el WIFI esta prendido
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            //Verificar que este conectado a una red
            Network network = cm.getActiveNetwork();

            if(network != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null){

                String url = "http://10.0.2.2:80/Semestre_Ago_Dic_2024/App_ABCC_Escuela/api_rest_android_escuela/api_mysql_cambios.php";

                String metodo = "POST";

                AnalizadorJSON analizadorJSON = new AnalizadorJSON();

                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        JSONObject jsonObject = analizadorJSON.peticionHTTP(url, metodo, datos);

                        Log.i("MSJ ->","Modificacion Correcta");

                        try {

                            String res = jsonObject.getString("consulta");

                            if(res.equals("exito")){

                                Log.i("MSJ ->","Modificacion Correcta");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(getBaseContext(), "Alumno modificado correctamente", Toast.LENGTH_LONG).show();
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
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

    public void consultarBD(ArrayList<String>  datos){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        Network network = cm.getActiveNetwork();

        if(network != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null){

            String url = "http://10.0.2.2:80/Semestre_Ago_Dic_2024/App_ABCC_Escuela/api_rest_android_escuela/api_mysql_consultas.php";

            String metodo = "POST";

            AnalizadorJSON analizadorJSON = new AnalizadorJSON();

            new Thread(new Runnable() {
                @Override
                public void run() {

                    JSONObject jsonObject = analizadorJSON.peticionHTTP(url, metodo, datosvacios);

                    try {

                        String res = jsonObject.getString("consulta");

                        if(res.equals("exito")){

                            JSONArray arreglo = jsonObject.getJSONArray("alumnos");//arreglo de json obtenido con los alumnos que concuerdan con la busqueda

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

                            Log.i("COSo", arrayList.get(0));

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    adapter = new CustomAdapter(arrayList, ActivityCambios.this);
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


    public void alumnoSeleccionado(String cadena){

        String[] datosCargados;
        cadena = cadena.substring(1);
        cadena = cadena.replaceAll(" ","");
        datosCargados = cadena.replaceAll("]", "").split(",");

        num_control.setText(datosCargados[0]);
        nombre.setText(datosCargados[1]);
        paterno.setText(datosCargados[2]);
        materno.setText(datosCargados[3]);
        fecha_nac.setText(datosCargados[4]);
        edad.setText(datosCargados[5]);
        num_telefono.setText(datosCargados[6]);

        switch (datosCargados[7]){

            case "1":

                semestre.setSelection(1);

                break;

            case "2":

                semestre.setSelection(2);

                break;
            case "3":

                semestre.setSelection(3);

                break;
            case "4":

                semestre.setSelection(4);

                break;
            case "5":

                semestre.setSelection(5);

                break;
            case "6":

                semestre.setSelection(6);

                break;
            case "7":

                semestre.setSelection(7);

                break;
            case "8":

                semestre.setSelection(8);

                break;
            case "9":

                semestre.setSelection(9);

            break;

            case "10":

                semestre.setSelection(10);

                break;
            case "11":

                semestre.setSelection(11);

                break;
            case "12":

                semestre.setSelection(12);

                break;

        }

        switch (datosCargados[8]){

            case "ISC":

                carrera.setSelection(1);

            break;

            case "IM":

                carrera.setSelection(2);

                break;

            case "IIA":

                carrera.setSelection(3);

                break;

            case "CP":

                carrera.setSelection(4);

                break;

            case "LA":

                carrera.setSelection(5);

                break;


        }
    }

    public void onAlumnoClick(String cadena) {

        alumnoSeleccionado(cadena);

    }
}

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<String> localDataSet;
    private OnAlumnoClickListener listener;

    //Clase INTERNA
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private OnAlumnoClickListener listener;

        public ViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.textView_recycler);
        }

        public TextView getTextView(){
            return textView;
        }


    }

    public interface OnAlumnoClickListener {
        void onAlumnoClick(String cadena);
    }

    public CustomAdapter(ArrayList<String> dataset, OnAlumnoClickListener listener){
        localDataSet = dataset;
        this.listener = listener;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAlumnoClick(localDataSet.get(position).toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return localDataSet.size();

    }

}//CustomAdapter
