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

<div align="center"><h3>Listado de ALUMNOS</h3></div>

<div id="tabla-alumnos">
    <?php
    include('../controllers/controller_alumno.php');
    $alumnoDAO = new AlumnoDAO();
    $datos = $alumnoDAO->mostrarAlumnos('');
    $alumnos = [];

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
                    <th>Acciones</th>
                </tr>
              </thead>
              <tbody>';

        while ($fila = mysqli_fetch_assoc($datos)) {
            $alumnos[] = $fila;
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
                    <td class='actions'>
                        <a href='#' onclick='mostrarFormulario(%s)'>Editar</a>
                        <a href='../controllers/procesar_bajas.php?nc=%s'>Eliminar</a>
                    </td>
                </tr>",
                $fila['num_control'],
                $fila['nombre_alumno'],
                $fila['paterno'],
                $fila['materno'],
                $fila['fecha_nac'],
                $fila['edad'],
                $fila['num_telefono'],
                $fila['semestre'],
                $fila['carrera'],
                $fila['num_control'], // Para las funciones de los botones
                $fila['num_control'], 
            );
        }

        echo '</tbody></table>';
    } else {
        echo "<p style='text-align: center; color: #776dfc;'>Tabla vacía</p>";
    }
    ?>
</div>

<div class="form-container" id="formulario-editar">
    <h2>Editar Alumno</h2>
    <form action="../controllers/procesar_edicion.php" method="POST" class="row g-3">
        <div class="col-12">
            <label for="edit_nombre">No. Control:</label>
            <input type="text" class="form-control" id="caja_num_control" name="caja_num_control" readonly>
        </div>

        <div class="col-12">
            <label for="caja_nombre">Nombre:</label>
            <input type="text" class="form-control" id="caja_nombre" name="caja_nombre" placeholder="Ingrese solo letras" onkeypress="validarSoloLetras(event)" required>
        </div>

        <div class="col-12">
            <label for="caja_primer_ap">Primer Apellido:</label>
            <input type="text" class="form-control" id="caja_primer_ap" name="caja_primer_ap" placeholder="Ingrese solo letras" onkeypress="validarSoloLetras(event)" required>
        </div>

        <div class="col-12">
            <label for="caja_segundo_ap">Segundo Apellido:</label>
            <input type="text" class="form-control" id="caja_segundo_ap" name="caja_segundo_ap" placeholder="Ingrese solo letras" onkeypress="validarSoloLetras(event)" required>
        </div>

        <div class="col-12">
            <label for="fecha_nacimiento">Fecha Nacimiento:</label>
            <input type="date" class="form-control" id="fecha_nacimiento" name="fecha_nacimiento" onchange="calcularEdad()" required>
        </div>

        <div class="col-12">
            <label for="edad">Edad:</label>
            <input type="text" class="form-control" id="edad" name="edad" placeholder="Edad calculada automáticamente" readonly required>
        </div>
        
        <div class="col-12">
            <label for="num_telefono">Numero de Telefono:</label>
            <input type="text" class="form-control" id="num_telefono" name="num_telefono" placeholder="Ingrese solo números" onkeypress="validarSoloNumeros(event)" required>
        </div>

        <div class="col-12">
            <label for="semestre">Semestre:</label>
            <select class="form-control" id="semestre" name="semestre" required>
                <option value="" disabled selected>Seleccione un semestre</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
            </select>
        </div>

        <div class="col-12">
            <label for="carrera">carrera:</label>
            <select class="form-control" id="carrera" name="carrera" required>
                <option value="" disabled selected>Seleccione una Carrera</option>
                <option value="ISC">ISC</option>
                <option value="IIA">IIA</option>
                <option value="IM">IM</option>
                <option value="LAE">LAE</option>
                <option value="LCP">LCP</option>
            </select>
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary" >Guardar Cambios</button>
        </div>
        <div class="col-12">
            <button type="button" class="btn btn-secondary" onclick="ocultarFormulario()">Cancelar</button>
        </div>
    </form>
</div>


<script>
    const alumnos = <?php echo json_encode($alumnos); ?>;

    function mostrarFormulario(numControl) {
        document.getElementById('tabla-alumnos').style.display = 'none';
        document.getElementById('formulario-editar').style.display = 'block';

        const alumno = alumnos.find(alumno => alumno.num_control === numControl.toString());

        if (alumno) {
            document.getElementById('caja_num_control').value = alumno.num_control;
            document.getElementById('caja_nombre').value = alumno.nombre_alumno;
            document.getElementById('caja_primer_ap').value = alumno.paterno;
            document.getElementById('caja_segundo_ap').value = alumno.materno;
            document.getElementById('fecha_nacimiento').value = alumno.fecha_nac;
            document.getElementById('edad').value = alumno.edad;
            document.getElementById('num_telefono').value = alumno.num_telefono;
            document.getElementById('semestre').value = alumno.semestre;
            document.getElementById('carrera').value = alumno.carrera;
        }
    }

    function ocultarFormulario() {
        document.getElementById('formulario-editar').style.display = 'none';
        document.getElementById('tabla-alumnos').style.display = 'block';
    }
</script>
</script>

</body>
</html>
