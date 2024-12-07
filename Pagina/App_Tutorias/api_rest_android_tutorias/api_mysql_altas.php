<?php

    echo"Coso 1 ";

    include_once('../database/conexion_bd.php');

    echo"Coso 2 ";

    $con = new ConexionBDTutorias();

    echo"Coso 3 ";

    $conexion = $con->getConexion();

    echo"Coso 4 ";

    //var_dump($conexion);

    //METODOS HTTP DE PETICION: POST, GET, PUT, PATCH, DELETE
    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        
        echo"Coso 5";
        //recibir la PETICION (REQUEST) con JSON a travez de HTTP
        $cadenaJSON = file_get_contents('php://input');//Extraer la cadena JSON de la peticion
        echo"Coso 6";
        if($cadenaJSON == false){

            echo "No hay cadena JSON";

        }else{
            
            $datos_alumno = json_decode($cadenaJSON, true); // se carga la cadena JSON que viene desde los datos de android

            //Asignamos los valores de la cadena JSON a las variables
            $nc = $datos_alumno['nc'];
            $n = $datos_alumno['n'];
            $pa = $datos_alumno['pa'];
            $sa= $datos_alumno['sa'];
            $fn = $datos_alumno['fn'];//RECIEN AGREGADO!!!
            $e= $datos_alumno['e'];
            $nt = $datos_alumno['nt'];
            $s= $datos_alumno['s'];
            $c= $datos_alumno['c'];

            //insercion directa del modelo DAO
            $sql = "INSERT INTO Alumnos VALUES ('$nc','$n','$pa' ,'$sa' ,'$fn', '$e', '$nt' ,'$s' ,'$c')";

            $res = mysqli_query($conexion, $sql);//Ejecucion de consulta en la conexion

            //configurar RESPUESTA JSON (RESPONSE)
            $respuesta = array();//Array que guardara si funciono o no la insercion

            if($res){//Si res es igual a true, significa que la insercion fue un exito

                $respuesta['alta'] = 'exito';
                
            }else{

                $respuesta['alta'] = 'error';
                    
            }

            $respuestaJSON = json_encode($respuesta); //Se codifica para JSON el arreglo

            echo $respuestaJSON;

        }

    }

    echo"Coso 7";

?>