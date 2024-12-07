<?php

include_once ('controller_alumno.php');

//1.-Obtener informacion de las cajas
$num_control = $_POST['caja_num_control'];
$nombre = $_POST['caja_nombre'];
$primer_ap = $_POST['caja_primer_ap'];
$segundo_ap = $_POST['caja_segundo_ap'];
$fecha_nac = $_POST['fecha_nacimiento'];
$edad = $_POST['edad'];
$num_tel = $_POST['num_telefono'];
$semestre = $_POST['semestre'];
$carrera = $_POST['carrera'];


//2.-Validar la informacion
$dato_correctos = false;

if (isset($num_control, $nombre, $primer_ap, $segundo_ap, $edad, $semestre, $carrera, $fecha_nac, $num_tel) && !empty($num_control) && !empty($nombre) && !empty($primer_ap) && !empty($segundo_ap) && !empty($edad) && !empty($fecha_nac) && !empty($num_tel) && !empty($semestre) && !empty($carrera) && is_numeric($num_control) && is_numeric($edad) && is_numeric($semestre) && is_numeric($num_tel)) {
    $dato_correctos=true;
}

//3.-mandarselos al controlador
if($dato_correctos){

    $alumnoDAO = new  AlumnoDAO();
    
    $res = $alumnoDAO -> agregarAlumno($num_control, $nombre, $primer_ap, $segundo_ap, $fecha_nac, $edad, $num_tel, $semestre, $carrera);

    if($res)
        header('location: ../pages/bajas_cambios.php');
    else

        echo"Mejor me dedico a las redes =(";

}

?>