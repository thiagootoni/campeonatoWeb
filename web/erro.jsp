<%-- 
    Document   : erro
    Created on : 25/04/2020, 19:09:27
    Author     : Thiago
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <h1>
            <%
                String erro = (String) request.getAttribute("erro");
                out.println(erro);
                out.println("<br>");
            %>   
        </h1>
        <a href="central?ac=login">Logar</a>;
    </body>
</html>
