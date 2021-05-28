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
        <script src="http://code.jquery.com/jquery-latest.js">

        </script>
        <script>
                $(document).ready(function() {
                        $('#submit').click(function(event) {
                                var nombreVar = $('#txtComando').val();
                                // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
                                $.post('cmdComand', {
                                        nombre : nombreVar
                                }, function(responseText) {
                                        $('#txtResultado').append(responseText);
                                });
                        });
                });
        </script>
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <label for="txtComando">Ingresar comando</label><br>
            <input name="txtComando" id="txtComando" type="text" placeholder="Ingrese el comando..." required>
            <input type="button" id="submit" value="ejecutar">
        </form>
        <div id="txtResultado" style="width: 800px; height: 500px; border: 1px solid black; background: aquamarine; overflow-y: scroll;"></div>
    </body>
</html>
