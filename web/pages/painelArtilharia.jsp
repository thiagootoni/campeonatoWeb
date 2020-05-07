<%-- 
    Document   : painelArtilharia
    Created on : 05/05/2020, 14:59:57
    Author     : hugo.alexandre
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Artilharia</title>

    </head>
    <body>
        <div class="card" style="width: 72rem;">
            <div class="card-header" >
                Tabela de Artilharia
            </div>
            <div class="card-body">
                <table class="table table-hover table-sm">
                    <thead>
                        <tr>
                            <th scope="col">Nome do Jogador</th>
                            <th scope="col">Time</th>
                            <th scope="col">Gols Feitos</th>
                        </tr>
                    </thead> 
                    <tbody>
                        <c:forEach var="jogador" items="${jogadoresGoleadores}">
                            <tr>
                                <td> ${jogador.nome}</td>

                                <td> ${jogador.time.nome}</td>

                                <td> ${jogador.golsFeitos.size()}</td>
                            </tr>
                        </c:forEach>    
                    </tbody>

                </table>

            </div>


            <div class="alert-warning" center >

                
            </div>
            <div style="min-height: 300px;">
            </div>
    </body>
</html>
