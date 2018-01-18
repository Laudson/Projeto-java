<%-- 
    Document   : teste
    Created on : 30/11/2016, 00:58:39
    Author     : laudson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="SCRIPT-JQUERY/jquery-3.1.0.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var max = $("#texto").text();
                $("#texto").text(max.substring(0, 9) + "......");
                
                var id = getParam('id');
                $("#junior").text(id);
            });
            
        </script>
    </head>
    <body>
<!--        <p id="texto">Laudson Teodoro Junior</p>
    <c:set var="string1" value="Laudson Teodoro Junior"/>
    <c:set var="string2" value="${fn:substring(string1, 0, 10)}....." />

    <p>Final sub string : ${string2}</p>
    
    <p id="junior"></p>-->
    
    
    <a href="download.do?id=${file.id}">Baixar</a>

</body>
</html>
