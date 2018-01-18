<%-- 
    Document   : newjsp
    Created on : 23/01/2017, 14:48:40
    Author     : laudson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="ESTILO-CSS/index.css" rel="stylesheet" type="text/css"/>
        <link href="ESTILO-CSS/estruturaSistema.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="topo">
        </div>
        <div id="logar">
            <h1>Autenticação</h1>
            <form action="ConsultaUsuario" method="POST">
                <label for="nomeUsuario">Nome usuario</label>
                <input type="text" name="nomeUsuario" id="nomeUsuario"/>
                <label for="senha">Senha</label>
                <input type="password" name="senha" id="senha"/>
                <input type="submit" value="Autenticar" />
            </form>
        </div>
    </body>
</html>
