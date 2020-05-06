<%-- 
    Document   : historico
    Created on : 05/05/2020, 21:31:32
    Author     : Thiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <div class="card">
        <div class="card-header">
            <h5> Histórico </h5>
        </div>
        <c:if test="${requestScope.mensagem != null}">
            <div class="card-body">
                <div class="alert alert-warning">${requestScope.mensagem}</div>
            </div> 
        </c:if>

        <div class="card-body" style="min-height: 500px">
            <div class="table table-hover table-sm">
                <table>
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                            <th scope="col">players</th>
                            <th scope="col">Campeão</th>
                            <th scope="col">Time</th>
                            <th scope="col">Artilheiro</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="campeonato" items="${requestScope.campeonatos}" varStatus="n">
                            <tr>
                                <td>${n.count}</td>
                                <td>${campeonato.nome}</td>
                                <td>${campeonato.qtdUsuarios}</td>
                                <td>${campeonato.campeao.nome}</td>
                                <td>${campeonato.artilheiro.time.nome}</td>
                                <td>${campeonato.artilheiro.nome}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
