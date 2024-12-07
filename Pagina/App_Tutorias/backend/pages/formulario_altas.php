<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Altas Alumnos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        function calcularEdad() {
            const fechaNacimiento = document.getElementById('fecha_nacimiento').value;
            const edadInput = document.getElementById('edad');
            if (fechaNacimiento) {
                const hoy = new Date();
                const nacimiento = new Date(fechaNacimiento);
                let edad = hoy.getFullYear() - nacimiento.getFullYear();
                const mes = hoy.getMonth() - nacimiento.getMonth();
                if (mes < 0 || (mes === 0 && hoy.getDate() < nacimiento.getDate())) {
                    edad--;
                }
                edadInput.value = edad;
            }
        }

        // Validar solo letras
        function validarSoloLetras(event) {
            const char = String.fromCharCode(event.keyCode || event.which);
            if (!/^[a-zA-Z\s]+$/.test(char)) {
                event.preventDefault();
            }
        }

        // Validar solo números
        function validarSoloNumeros(event) {
            const char = String.fromCharCode(event.keyCode || event.which);
            if (!/^\d+$/.test(char)) {
                event.preventDefault();
            }
        }
    </script>
</head>

<body>

    <?php require_once('menu_principal.php'); ?>

    <div class="form-container">
        <h2>Altas Alumnos</h2>

        <form action="../controllers/procesar_altas.php" method="POST" class="row g-3">
            <div class="col-12">
                <label for="caja_num_control">Número de Control:</label>
                <input type="text" class="form-control" id="caja_num_control" name="caja_num_control" placeholder="Ingrese solo números" onkeypress="validarSoloNumeros(event)" required>
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
                    <option value="LA">LA</option>
                    <option value="LCP">LCP</option>
                </select>
            </div>

            <div class="col-12">
                <button type="submit" class="btn btn-primary">Agregar</button>
            </div>
        </form>
    </div>

</body>
</html>
