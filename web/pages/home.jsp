<%-- 
    Document   : home.jsp
    Created on : 19/04/2020, 17:58:14
    Author     : Thiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h5> E aí ${sessionScope.user.nome}, blz? </h5>    
</div><br>
<div>
    <div class="alert-warning">${requestScope.mensagem}</div>
    <c:if test="${requestScope.campeonato.status.getNome() == 'Em aberto'}">
        <c:if test="${sessionScope.user.ehAdm == true}">
            <div class="card">
                <p class="alert-warning"> Campeonato em aberto, para iniciar vá até o menu campeonato e escolha a opção desejada</p>
            </div> 
        </c:if>
        <c:if test="${sessionScope.user.ehAdm == false}">
            <div class="card">
                <p class="alert-warning"> Calma parsa, já já o Adm libera os jogos! </p>
            </div> 
        </c:if>
    </c:if>

    <div class="card">
        <div class="card-header">
            <h5>Tabela</h5>
        </div>
        <div class="card-body">
            <!-- Imprimir a tabela com a pontuação -->
        </div>
    </div><br>
    <div class="card">
        <div class="card-header">
            <h5>Jogos</h5>
        </div>
        <div class="card-body">
            <form method="post" action="">
                <c:forEach var="jogo" items="${requestScope.campeonato.jogos}" varStatus="n">
                    <div class="card">
                        <input type="hidden" name ="idJogo" value="${jogo.id}">
                        <div class="card-header">
                            <h5>Jogo ${n.count}</h5>
                        </div>
                        <div class="card-body">
                            <div class="col-md-5">
                                <div class="col-md-9">
                                    <label for="desafiante${n.count}">${jogo.desafiante.nome}</label>
                                </div>
                                <div class="col-md-3">
                                    <input type="number" name="placarDesafiante" id="desafiante${n.count}" min="0">
                                </div>
                            </div>
                            <div class="col-md-2" style="text-align: center">
                                X
                            </div>
                            <div class="col-md-5">
                                <div class="col-md-3">
                                    <input type="number" name="placarDesafiante" id="desafiante${n.count}" min="0">
                                </div>
                                <div class="col-md-9">
                                    <label for="desafiado${n.count}">${jogo.desafiado.nome}</label>
                                </div>
                            </div>
                        </div>                    
                    </div>
                </c:forEach>
            </form>
        </div>
    </div>
</div>
