package controlador;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AnalizadorJSON {
    private InputStream is = null;// Para leer la respuesta del servidor.
    private OutputStream os = null;// Para enviar datos al servidor.
    private JSONObject jsonObject = null; // Almacena la respuesta del servidor en formato JSON.
    private HttpURLConnection conexion = null;// Gestiona la conexión HTTP.
    private URL url;// Representa la URL a donde se encuentra el API.

    //Codigo para la PETICION HTTP
    public JSONObject peticionHTTP(String direccionURL, String metodo, ArrayList<String> datos){

        //--------- PETICION ALTAS enviar al servidor (request)----------------
        //Ejemplo de Cadena JSON -->   {"nc":"01", "n": "Luke", "pa":"Sky", "sa":"walker","fn":"1992-10-21","e":"50", "nt":"4991003247","s":"9", "c":"ISC"}

        //Formamos la cadena que mandaremos al servidor a travez de JSON
        String cadenaJSON = "{ " +
                "\"nc\":\"" + datos.get(0) +
                "\", \"n\":\"" + datos.get(1) +
                "\", \"pa\":\"" + datos.get(2) +
                "\", \"sa\":\"" + datos.get(3) +
                "\", \"fn\":\"" + datos.get(4) +
                "\", \"e\":\"" + datos.get(5) +
                "\", \"nt\":\"" + datos.get(6) +
                "\", \"s\":\"" + datos.get(7) +
                "\", \"c\":\"" + datos.get(8) +
                "\"}";

        try {

            url = new URL(direccionURL);
            conexion = (HttpURLConnection) url.openConnection();

            //activamos el envio a traves HTTP
            conexion.setDoOutput(true);

            //indicar el metodo de envio POST
            conexion.setRequestMethod(metodo);

            //indiar el tamanio prestablecido o fijo de la cadena a enviar
            conexion.setFixedLengthStreamingMode(cadenaJSON.length());

            //establecer formato de peticion
            conexion.setRequestProperty("Content-Type", "application/x-www.form-urlencoded");

            //Preparar el envio de la PETICION
            os = new BufferedOutputStream(conexion.getOutputStream());
            os.write(cadenaJSON.getBytes());// Escribe el JSON en el cuerpo de la petición.
            os.flush();// Asegura que todos los datos se envíen.
            os.close();// Cierra el flujo de salida.

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        // -------------------- RECIBIR RESPUESTA DEL SERVIDOR (response) ---------------
        try {

            is = new BufferedInputStream(conexion.getInputStream());//Guardamos la respuesta
            BufferedReader br = new BufferedReader(new InputStreamReader(is));//Creamos un buffer de lectura

            StringBuilder cadena = new StringBuilder();

            String fila = null;
            while( (fila = br.readLine()) != null ){
                cadena.append(fila+"\n");
            }

            is.close();
            jsonObject = new JSONObject(String.valueOf(cadena));//Creamos un objeto JSON con la respuesta del servidor

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }//metodo
}//class