<?php
    session_start();
    if(!$_SESSION['valida'])
    header('Location: login.php');
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../../css/style.css">
    <style>
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropbtn {
            background-color: #000000;
            color: #ffffff;
            padding: 10px 20px;
            font-weight: bold;
            border: none;
            border-radius: 20px;
            cursor: pointer;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
            border-radius: 5px;
        }
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {
            background-color: #ddd;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .dropdown:hover .dropbtn {
            background-color: #555;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg" style="background: linear-gradient(to bottom, rgb(0, 0, 255), #776dfc);">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <a href="menu_principal.php" class="btn" style="background-color: #000000; color: #ffffff; padding: 10px 20px; font-weight: bold; border-radius: 20px; margin-right: 15px;">Inicio</a>

            <div class="dropdown">
                <button class="dropbtn">Alumnos</button>
                <div class="dropdown-content">
                    <a href="formulario_altas.php">Agregar</a>
                    <a href="bajas_cambios.php">Eliminar/Modificar</a>
                    <a href="formulario_consultas.php">Consultas</a>
                </div>
            </div>

            <div class="dropdown ms-3">
                <button class="dropbtn">Docentes</button>
                <div class="dropdown-content">
                    <a href="formulario_altas_docentes.php">Agregar</a>
                    <a href="bajas_cambios_docentes.php">Eliminar/Modificar</a>
                    <a href="formulario_consultas_docentes.php">Consultas</a>
                </div>
            </div>

            <div class="dropdown ms-3">
                <button class="dropbtn">Reportes</button>
                <div class="dropdown-content">
                    <a href="formulario_altas_reportes.php">Agregar</a>
                    <a href="bajas_cambios_reportes.php">Eliminar/Modificar</a>
                    <a href="formulario_consultas_reportes.php">Consultas</a>
                </div>
            </div>
        </div>

        <div class="d-flex align-items-center ms-auto">
            <a class="navbar-brand" style="color: white; font-weight: bold;">Bienvenido <?php echo $_SESSION['usuario']?></a>
            <form class="ms-3 d-flex" action="../../backend/controllers/cerrar_sesion.php" method="POST">
                <button class="btn" type="submit" style="background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 20px; font-weight: bold; transition: background-color 0.3s ease;">Cerrar Sesión</button>
            </form>
        </div>
    </div>
</nav>

</body>
</html>
