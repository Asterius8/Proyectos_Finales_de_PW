<?php
    session_start();
    if($_SESSION['valida'])
        header('Location: formulario_altas.php');
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Login y Registro</title>
    <link rel="stylesheet" href="../../../css/style.css"> 
    <style>
        .error-message {
            color: red;
            font-size: 0.9em;
            display: none;
        }
    </style>
</head>
<body>

<header>
    <nav>
        <div class="logo"><img src="../../../images/tutoria.png" alt="Logo Tutorías" height="200px"></div>
        <div class="header-content">
            <h1>Bienvenido, Inicia Sesion Para Continuar</h1>
        </div>
        <div class="nav-links">
            <button type="button" onclick="showLogin()">Login</button>
            <button type="button" onclick="showRegister()">Registro</button>
        </div>
    </nav>
</header>

<div id="login" class="form-container">
    <div class="header-content">
        <h2>Login</h2>
    </div>
    <form action="../controllers/validar_usuario.php" method="POST"> 
        <label for="usuario">Usuario:</label>
        <input type="text" class="form-control" id="caja_usuario" placeholder="Ingrese el usuario" name="caja_usuario" required>
        <label for="password">Contraseña:</label>
        <input type="password" class="form-control" id="caja_password" placeholder="Ingrese la contraseña" name="caja_password" required>
        <button type="submit">Iniciar Sesión</button>
    
        <div id="error-message" class="error-message">Comprueba tus datos</div>
    </form>
</div>

<div id="register" class="form-container" style="display: none;">
    <div class="header-content">
        <h2>Registro</h2>
    </div>
    <form action="../controllers/registrar_usuario.php" method="POST"> <!-- Formulario de registro -->
        <label for="name">Nombre:</label>
        <input type="text" id="caja_usuario" name="caja_usuario" placeholder="Introduce tu nombre" required>
        <label for="password">Contraseña:</label>
        <input type="password" id="caja_password1" name="caja_password" placeholder="Introduce tu contraseña" required>
        <label for="confirm-password">Confirmar Contraseña:</label>
        <input type="password" id="caja_password2" name="confirm_caja_password" placeholder="Confirma tu contraseña" required>
        <button type="submit">Registrarse</button>
        
        <div id="error-message2" class="error-message">Ya existe ese usuario</div>
        <div id="error-message3" class="error-message">Las contraseñas no concuerdan</div>
    </form>
</div>

<footer>
    <p>&copy; 2024 TecNM. Todos los derechos reservados.</p>
</footer>

<script>
    function showLogin() {
        document.getElementById('login').style.display = 'block';
        document.getElementById('register').style.display = 'none';
    }

    function showRegister() {
        document.getElementById('register').style.display = 'block';
        document.getElementById('login').style.display = 'none';
    }

    if (window.location.search.includes('error=1')) {
        document.getElementById('error-message').style.display = 'block';
        showLogin(); 
    }
    if (window.location.search.includes('error=2')) {
        document.getElementById('error-message2').style.display = 'block';
        showRegister(); 
    }
    if (window.location.search.includes('error=3')) {
        document.getElementById('error-message3').style.display = 'block';
        showRegister(); 
    }
</script>

</body>
</html>
