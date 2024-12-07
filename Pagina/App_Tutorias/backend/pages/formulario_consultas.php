<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Alumnos</title>
    <style>
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            color: #041d05;
        }

        th {
            background-color: #776dfc;
            color: white;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f1f1f1;
        }
        
        .actions a {
            background-color: #000;
            color: #fff;
            padding: 8px 12px;
            margin: 0 5px;
            border-radius: 20px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .actions a:hover {
            background-color: #00fbff;
            color: #000;
        }

        h3 {
            display: inline-block;
            padding: 8px 15px;
            border: 2px solid #776dfc; /* Color morado */
            border-radius: 20px; /* Borde redondeado */
            background-color: #000; /* Fondo negro */
            color: #776dfc; /* Color del texto morado */
            font-weight: bold;
        }
        .form-container { display: none; } /* Ocultar formulario inicialmente */
    </style>
</head>
<body>

<?php require_once('menu_principal.php'); ?>

<div align="center">
<h3>Listado de ALUMNOS</h3>
</div>

<div id="busqueda-alumnos">
    <form action="#" method="GET">
        <table>
            <thead>
                <tr>
                    <th>Num. Control</th>
                    <th>Nombre</th>
                    <th>Primer Ap</th>
                    <th>Segundo Ap</th>
                    <th>Fecha Nac.</th>
                    <th>Edad</th>
                    <th>Num. Telefono</th>
                    <th>Semestre</th>
                    <th>Carrera</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input type="text" name="buscar_num_control" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_nombre" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_primer_ap" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_segundo_ap" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_fecha_nac" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_edad" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_num_telefono" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_semestre" class="form-control" placeholder="Buscar..."></td>
                    <td><input type="text" name="buscar_carrera" class="form-control" placeholder="Buscar..."></td>
                    <td><button type="submit" class="btn btn-primary">Buscar</button></td>
                </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="tabla-alumnos">
    <?php
    include('../controllers/controller_alumno.php');
    $alumnoDAO = new AlumnoDAO();

    $filtro = "";
    if ($_GET) {
        $filtro = " WHERE 1=1";
        if (!empty($_GET['buscar_num_control'])) {
            $filtro .= " AND num_control LIKE '%".$_GET['buscar_num_control']."%'";
        }
        if (!empty($_GET['buscar_nombre'])) {
            $filtro .= " AND nombre_alumno LIKE '%".$_GET['buscar_nombre']."%'";
        }
        if (!empty($_GET['buscar_primer_ap'])) {
            $filtro .= " AND paterno LIKE '%".$_GET['buscar_primer_ap']."%'";
        }
        if (!empty($_GET['buscar_segundo_ap'])) {
            $filtro .= " AND materno LIKE '%".$_GET['buscar_segundo_ap']."%'";
        }
        if (!empty($_GET['buscar_fecha_nac'])) {
            $filtro .= " AND fecha_nac LIKE '%".$_GET['buscar_fecha_nac']."%'";
        }
        if (!empty($_GET['buscar_edad'])) {
            $filtro .= " AND edad LIKE '%".$_GET['buscar_edad']."%'";
        }
        if (!empty($_GET['buscar_num_telefono'])) {
            $filtro .= " AND num_telefono LIKE '%".$_GET['buscar_num_telefono']."%'";
        }
        if (!empty($_GET['buscar_semestre'])) {
            $filtro .= " AND semestre LIKE '%".$_GET['buscar_semestre']."%'";
        }
        if (!empty($_GET['buscar_carrera'])) {
            $filtro .= " AND carrera LIKE '%".$_GET['buscar_carrera']."%'";
        }
    }

    $datos = $alumnoDAO->mostrarAlumnos($filtro);
    if (mysqli_num_rows($datos) > 0) {
        echo '<table>';
        echo '<thead>
                <tr>
                    <th>Num. Control</th>
                    <th>Nombre</th>
                    <th>Primer Ap</th>
                    <th>Segundo Ap</th>
                    <th>Fecha Nac.</th>
                    <th>Edad</th>
                    <th>Num. Telefono</th>
                    <th>Semestre</th>
                    <th>Carrera</th>
                </tr>
              </thead>
              <tbody>';

        while ($fila = mysqli_fetch_assoc($datos)) {
            printf(
                "<tr>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                    <td>%s</td>
                </tr>",
                $fila['num_control'],
                $fila['nombre_alumno'],
                $fila['paterno'],
                $fila['materno'],
                $fila['fecha_nac'],
                $fila['edad'],
                $fila['num_telefono'],
                $fila['semestre'],
                $fila['carrera']
            );
        }

        echo '</tbody></table>';
    } else {
        echo "<p style='text-align: center; color: #776dfc;'>No se encontraron resultados</p>";
    }
    ?>
</div>

</body>
</html>
