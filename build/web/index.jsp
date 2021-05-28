<%-- 
    Document   : index
    Created on : 27/05/2021, 23:14:24
    Author     : henry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://code.jquery.com/jquery-latest.js"> </script>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

    <title>IDE Arduino WEB</title>
</head>

<body>

    <main>
        <div class="logo">
            <div>Arduino</div>
            <img class="img-logo" src="./logo arduino.png" alt="logo">
        </div>
        <form>
            <div>
                <div class="titulo rectangulo">Placas</div>
                <div class="contenido">
                    <p>Seleccione la placa que va a utilizar</p>
                    <div class="lista" id="list_placas"> </div>
                    <div class="display-rigth">
                        <button class="btn" id="actualizar" type="button">Actualizar</button>
                    </div>
                    <!-- <button class="btn" id="detectar" type="button">Detectar</button> -->
                </div>
            </div>
            <div class="titulo rectangulo">Código</div>
            <div class="contenido">
                <p>Ingrese la ruta del código</p>
                <input type="text" name="ruta" id="input-ruta" class="input-text rectangulo"
                    placeholder="Ejemplo: C:\Carpeta\Arduino-CLI\MyFirstSketch.ino" readonly>
                <div class="display-rigth">
                    <input id="archivo" name="archivo" class="btn" type="file" />
                </div>
            </div>
            <br>


            <label for="txtComando">Ingresar comando</label><br>
            <input name="txtComando" id="txtComando" type="text" placeholder="Ingrese el comando..." required>
            <div class="display-center botones">
                <input class="btn btn-compilar" type="button" id="submit" value="Compilar" />
                <button class="btn btn-enviar" type="submit">Enviar a placa</button>
            </div>
        </form>

        <div id="txtResultado"></div>
    </main>
    <script>
        // $(document).ready(function () {
        //     $('#submit').click(function (event) {
        //         var nombreVar = $('#txtComando').val();
        //         // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        //         $.post('cmdComand', {
        //             nombre: nombreVar
        //         }, function (responseText) {
        //             $('#txtResultado').append(responseText);
        //         });
        //     });
        // });

        $(document).ready(function () {
            $('#submit').click(function (event) {
                var puerto_placa = $('input:radio[name=placa]:checked').parent().find(".nom_placa")
                    .text()
                    .split(' ')[1];
                alert(puerto_placa);
                var direc_placa = "Hola";
                // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
                $.post('compilar', {
                    puerto: puerto_placa,
                    directorio: direc_placa
                }, function (responseText) {
                    $('#txtResultado').text("");
                    $('#txtResultado').append(responseText);
                });
            });
        });

        $(document).ready(function () {
            $('#actualizar').click(function (event) {
                $('#list_placas').text("");
                $.post('obt_arduino', {}, function (responseText) {
                    $('#list_placas').append(responseText);
                });
            });
        });

        // $(document).ready(function () {
        //     $("#detectar").click(function () {
        //         alert($('input:radio[name=placa]:checked').parent().find(".nom_placa").text().split(
        //             ' ')[1]);
        //     });
        // });

        document.getElementById('archivo').onchange = function () {
            $("#input-ruta").val(document.getElementById('archivo').files[0]);
            var path = (window.URL || window.webkitURL).createObjectURL(document.getElementById('archivo').files[
            0]);
            console.log('path', path);
        };
    </script>
</body>

</html>