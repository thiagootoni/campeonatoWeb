<%-- 
    Document   : painelJogadores
    Created on : 30/04/2020, 03:14:15
    Author     : Thiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="card" style="width: 72rem;">
    <div class="card-header">
        Novo Jogador
    </div>
    <div class="alert-warning" style="margin:3px;">
        ${requestScope.mensagem}
    </div>
    <div class="card-body" id="formCadJogador">
        <c:if test="${requestScope.times ne null}">
            <form action="central" method="post">
                <input type="hidden" name="ac" value="saveNewJogador">
                <input type="hidden" name="id" value="${requestScope.jogador.id}">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nomeJogador">Nome</label>
                        <input class="form-control" type="text" name="nomeJogador" id="nomeJogador" placeholder="nome do Jogador" value="${requestScope.jogador.nome}">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="timeJogador">Nome</label>
                        <select class="form-control" name="timeJogador">
                            <c:forEach var="time" items="${requestScope.times}">
                                <option value="${time.id}">${time.nome}</option>
                            </c:forEach>
                            
                        </select>
                    </div>
                </div>

                <div class="form-group"><br>
                    <c:if test="${requestScope.botao == 'alterar'}">
                        <button type="submit" class="btn btn-primary">Alterar</button>
                        <input type="hidden" name="alterar" value="alterar">
                    </c:if>
                    <c:if test="${requestScope.botao == null}">
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                    </c:if>
                </div>
            </form>
        </c:if>
    </div>
</div>
<div class="card" style="width: 72rem; margin-top: 30px;">
    <div class="card-header">
        Meus Jogadores
    </div>
    <div class="card-body">
        <table class="table table-hover table-sm ">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Time</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="jogador" items="${requestScope.jogadores}" varStatus="n">
                    <tr>
                        <td>${n.count}</td>
                        <td>${jogador.nome}</td>
                        <td>${jogador.clube}</td>
                        <td>
                            <a class="btn btn-outline-danger btn-sm" href="central?ac=excluirJogador&id=${jogador.id}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>