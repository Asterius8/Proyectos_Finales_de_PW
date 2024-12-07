<?php

    include_once('../../database/conexion_bd.php');

    //Data Access Objet (DAO)
    class AlumnoDAO{

        private $conexion;

        public function __construct(){

            $this->conexion = new ConexionBDTutorias();

        }

        // =========== METODOS ABCC (CRUD) =================

        //---------------- Metodo ALTAS ------------------
        //public function agregarAlumno($alumno)
        public function agregarAlumno($nc, $n ,$p ,$m ,$fn ,$e ,$nt, $s, $c){
            $sql = "INSERT INTO Alumnos VALUES ('$nc', '$n' ,'$p' ,'$m' ,'$fn' ,'$e' ,'$nt', '$s', '$c')";
            $res = mysqli_query($this->conexion->getConexion(), $sql);
            return $res;
        }

        //---------------- Metodo BAJAS ------------------
        public function eliminarAlumno($nc){
            $sql = "DELETE FROM Alumnos WHERE num_control = '$nc'";
            $res = mysqli_query($this -> conexion -> getConexion(), $sql);
            return $res;
        }

        //---------------- Metodo CAMBIOS ------------------
        public function cambiarAlumno($nc, $n ,$p ,$m ,$fn ,$e ,$nt, $s, $c){
            $sql = "UPDATE Alumnos SET nombre='$n', paterno = '$p', materno = '$m', fecha_nac = '$fn', edad = '$e', num_telefono = '$nt', semestre = '$s', carrera = '$c' WHERE num_control = '$nc'";
            $res = mysqli_query($this -> conexion -> getConexion(), $sql);
            return $res;
        }

        //---------------- Metodo CONSULTAS ------------------
        public function mostrarAlumnos($filtro){

            $sql = "SELECT * FROM Alumnos$filtro ORDER BY `Alumnos`.`num_control`";
            $res = mysqli_query($this -> conexion -> getConexion(), $sql);
            return $res;

        }

    }

?>