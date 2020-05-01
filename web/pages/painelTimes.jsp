<%-- 
    Document   : painelTimes
    Created on : 30/04/2020, 02:56:45
    Author     : Thiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card" style="width: 72rem;">
    <div class="card-header">
        Novo Time
    </div>
    <div class="alert-warning">
        ${requestScope.mensagem}
    </div>
    <div class="card-body" id="formCadTime">
        <form action="central" method="post">
            <input type="hidden" name="ac" value="saveNewTime">
            <input type="hidden" name="id" value="${requestScope.time.id}">
            <div class="form-row">
                <label for="nomeTime">Nome</label>
                <input class="form-control" type="text" name="nomeTime" id="nomeTime" placeholder="nome do time" value="${requestScope.time.nome}">
            </div><br>
            <div class="form-row">               
                <c:if test="${requestScope.botaoAlterar == 'alterar'}">
                    <button type="submit" class="btn btn-primary">Alterar</button>
                    <input type="hidden" name="alterar" value="alterar">
                </c:if>
                <c:if test="${requestScope.botaoAlterar == null}">
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </c:if>
            </div>
        </form>
    </div>
</div>

<div class="card" style="width: 72rem; margin-top: 30px;">
    <div class="card-header">
        Meus Times
    </div>

    <div class="card-body">
        <table class="table table-hover table-sm ">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Nº de Joagdores</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="time" items="${requestScope.times}" varStatus="n">
                    <tr>
                        <td>${n.count}</td>
                        <td>${time.nome}</td>
                        <td>${time.qtdJogadores}</td>
                        <td>
                            <a class="btn btn-outline-danger btn-sm" href="central?ac=alterarTime&id=${time.id}">Alterar</a>
                            <a class="btn btn-outline-danger btn-sm" href="central?ac=excluirTime&id=${time.id}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>