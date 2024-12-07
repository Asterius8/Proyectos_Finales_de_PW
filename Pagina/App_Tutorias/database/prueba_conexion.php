<?php
    include_once('conexion_bd.php');

    echo "Coso";

    $conexion = new ConexionBDUsuarios();
    $con = $conexion->getConexion();

    var_dump($con);
?>