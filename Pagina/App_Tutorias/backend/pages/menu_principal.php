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
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../../../css/style.css"> 
</head>
<body>

<nav class="navbar navbar-expand-lg" style="background: linear-gradient(to bottom, rgb(0, 0, 255), #776dfc);">
  <div class="container-fluid">
    <a class="navbar-brand" href="menu_principal.php" style="background-color: #00fbff; color: #000000; padding: 8px 15px; border-radius: 20px; font-weight: bold; transition: background-color 0.3s ease;">ALUMNOS</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0 nav-links">
        <li class="nav-item">
          <a class="nav-link active" href="formulario_altas.php" style="background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 20px; font-weight: bold; transition: background-color 0.3s ease;">AGREGAR</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="bajas_cambios.php" style="background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 20px; font-weight: bold; transition: background-color 0.3s ease;">ELIMINAR/MODIFICAR</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="formulario_consultas.php" tabindex="-1" aria-disabled="true" style="background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 20px; font-weight: bold; transition: background-color 0.3s ease;">CONSULTAS</a>
        </li>
        <form class="d-flex" action = "../../backend/controllers/cerrar_sesion.php" method="POST">
        </ul>
        <a class="navbar-brand" style="color: white; font-weight: bold;">Bienvenido <?php echo $_SESSION['usuario']?></a>
        <button class="btn" type="submit" style="background-color: #000000; color: #ffffff; padding: 10px 15px; border-radius: 20px; font-weight: bold; transition: background-color 0.3s ease;">Cerrar Sesion</button>
      </form>
      
      
    </div>
  </div>
</nav>

</body>
</html>