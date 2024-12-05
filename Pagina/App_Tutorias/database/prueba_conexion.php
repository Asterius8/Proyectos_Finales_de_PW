<?php
    include_once('conexion_bd.php');
    $conexion = new ConexionBDEscuela();
    $con = $conexion->getConexion();
    var_dump($con);
?>