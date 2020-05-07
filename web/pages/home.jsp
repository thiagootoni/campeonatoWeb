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
    <!-- Div destinada a divulgação de mensagens -->

    <div class="alert-warning">${requestScope.mensagem}</div>
    <c:if test="${requestScope.campeonato != null}">
        <c:if test="${requestScope.campeonato.status.getNome() == 'Em aberto'}">
            <c:if test="${sessionScope.user.ehAdm == true}">
                <div class="alert alert-warning">
                    Campeonato em aberto, para iniciar vá até o menu campeonato e escolha a opção desejada
                </div> 
            </c:if>
            <c:if test="${sessionScope.user.ehAdm == false}">
                <div class="alert alert-warning">
                    Calma parsa, já já o Adm libera os jogos!
                </div> 
            </c:if>
        </c:if>
    </c:if>
    <c:if test="${requestScope.campeonato == null}">
        <div class="alert alert-warning">Não há campeonatos criados! Crie um no menu +Campeonato</div> 
    </c:if>


    <!-- Tabela do Ranking/Classificação -->

    <div class="card">
        <div class="card-header">
            <h5>Tabela &nbsp;&nbsp;&nbsp;&nbsp; 
                <c:if test="${sessionScope.user.ehAdm == true}">
                    <c:if test="${requestScope.artilheiro == null}">
                        <a href="central?ac=buscarArtilheiro" class="btn btn-primary"> Definir Artilhero</a>
                    </c:if>
                </c:if>
            </h5>
        </div>

        <div class="card-body">
            <!-- Imprimir a tabela com a pontuação -->
            <table class="table table-hover table-sm">
                <thead>
                    <tr>
                        <th>Posição</th>
                        <th>Nome</th>
                        <th>Pontos</th>
                        <th>Jogos</th>
                        <th>Vitorias</th>
                        <th>Derrotas</th>
                        <th>Empates</th>                        
                        <th>Gols Pro</th>
                        <th>Gols Contra</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="time" items="${requestScope.times}" varStatus="n">
                        <tr>
                            <td>${n.count}</td>
                            <td>${time.nome}</td>
                            <td>${time.pontos}</td>
                            <td>${time.numJogos}</td>
                            <td>${time.vitorias}</td>
                            <td>${time.derrotas}</td>
                            <td>${time.empates}</td>                            
                            <td>${time.golsAFavor}</td>
                            <td>${time.golsContra}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div><br>
    <div>
        <c:if test="${artilheiro != null}">
            <div class="alert alert-success">
                O nome da fera é ${requestScope.artilheiro.nome}, autor de incríveis 
                ${requestScope.artilheiro.golsFeitos.size()} gols garantindo assim os 5 pontos extras para o 
                ${requestScope.artilheiro.time.nome}.
            </div>    
        </c:if>
        <c:if test="${requestScope.artilheiro ==null && requestScope.naojogados !=null}"> 
            <div class="alert alert-warning">${naojogados}</div>    
        </c:if>
    </div>
    <div class="card">
        <div class="card-header">
            <h5>Jogos</h5>
        </div>

        <!-- Card COnteiner dos jogos -->

        <div class="card-body">

            <c:forEach var="jogo" items="${requestScope.campeonato.jogos}" varStatus="n">
                <form method="post" action="central">
                    <input type="hidden" name="ac" value="salvarAlterarResultadoJogo">
                    <div class="card">
                        <input type="hidden" name ="idJogo" value="${jogo.id}">

                        <!-- Header Jogo -->

                        <div class="card-header">
                            <h6>Jogo ${n.count} - Rodada ${jogo.rodada}
                                <c:if test="${jogo.foiJogado == true}">
                                    &nbsp;<a href="#" class="btn btn-outline-success">Jogo Finalizado</a>
                                </c:if>
                            </h6>
                        </div>                            

                        <!-- Placar jogo -->                        

                        <div class="card-body form-row">
                            <div class="col-md-5 form-row">
                                <div class="col-md-7">
                                    <input type="hidden" name="idDesafiante" value="${jogo.desafiante.id}">
                                    <label for="desafiante${n.count}">${jogo.desafiante.nome}</label>
                                </div>
                                <div class="col-md-5">
                                    <input class="form-control text-center" type="number" name="placarDesafiante" id="desafiante${n.count}" min="0" value="${jogo.golsDesafiante}"
                                           <c:if test="${sessionScope.user.ehAdm == false}">disabled</c:if>>
                                    </div> 
                                </div>
                                <div class="col-md-2">
                                    <span>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        X 
                                    </span>
                                </div>
                                <div class="col-md-5 form-row">
                                    <div class="col-md-5">
                                        <input class="form-control text-center" type="number" name="placarDesafiado" id="desafiado${n.count}" min="0" value="${jogo.golsDesafiado}"
                                           <c:if test="${sessionScope.user.ehAdm == false}">disabled</c:if>>
                                    </div>
                                    <div class="col-md-7 text-right">
                                        <input type="hidden" name="idDesafiado" value="${jogo.desafiado.id}">
                                    <label for="desafiado${n.count}">${jogo.desafiado.nome}</label>
                                </div>
                            </div>
                        </div>

                        <!-- gols -->

                        <div class="card-body"> 

                            <a class="btn btn-outline-secondary" data-toggle="collapse" href="#golsLista${n.count}" role="button"
                               role="button" aria-expanded="false" aria-controls="golsLista${n.count}">
                                Gols <i class="fa fa-external-link"></i>
                            </a>

                            <!-- Estrutura do Adm -->

                            <c:if test="${sessionScope.user.ehAdm == true}">
                                <div class="form-group collapse" id="golsLista${n.count}">
                                    <div>
                                        <hr><p>Marcadores:
                                            <c:if test="${not empty jogo.golsDoJogo}">
                                                <c:forEach var="gol" items="${jogo.golsDoJogo}">
                                                    ${gol.autor.nome} &nbsp;|
                                                </c:forEach>
                                            </c:if>
                                        </p>
                                    </div>
                                    <c:if test="${jogo.foiJogado == false}">
                                        <div class="form-row">
                                            <div class="col-md-6">
                                                <h6>${jogo.desafiante.nome}</h6><hr>
                                                <c:forEach var="jogador" items="${jogo.desafiante.jogadores}" varStatus="m">
                                                    <div class="form-row">
                                                        <div class="col-md-9">
                                                            <input type="hidden" name ="golsIdJogador" value="${jogador.id}">
                                                            <input type="hidden" name ="infoDentroFora" value="DESAFIANTE">
                                                            <label>${jogador.nome}</label>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <input class="form-control form-control-sm text-center" type="number" name ="golsQtdJogador" min="0" value="0" required <c:if test="${sessionScope.user.ehAdm == false}">disabled</c:if>> 
                                                            </div>
                                                        </div>
                                                </c:forEach>
                                            </div>
                                            <div class="col-md-6">
                                                <h6>${jogo.desafiado.nome}</h6><hr>
                                                <c:forEach var="jogador" items="${jogo.desafiado.jogadores}" varStatus="m">
                                                    <div class="form-row">
                                                        <div class="col-md-9">
                                                            <input type="hidden" name ="golsIdJogador" value="${jogador.id}">
                                                            <input type="hidden" name ="infoDentroFora" value="DESAFIADO">
                                                            <label>${jogador.nome}</label>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <input class="form-control form-control-sm text-center" type="number" name ="golsQtdJogador" min="0" value="0" required <c:if test="${sessionScope.user.ehAdm == false}">disabled</c:if>>
                                                            </div>
                                                        </div>                                        
                                                </c:forEach>
                                            </div>
                                        </div> 
                                    </c:if>
                                </div>
                            </c:if>

                            <!-- Estrutura Normal user -->
                            <c:if test="${sessionScope.user.ehAdm == false}">
                                <div class="form-row collapse" id="golsLista${n.count}">
                                    <hr><p>Marcadores:
                                        <c:if test="${not empty jogo.golsDoJogo}">
                                            <c:forEach var="gol" items="${jogo.golsDoJogo}">
                                                ${gol.autor.nome} &nbsp;|
                                            </c:forEach>
                                        </c:if>
                                    </p>
                                </div>
                            </c:if>
                        </div>

                        <!-- Botão de Salvar Form, apenas para adms -->

                        <c:if test="${sessionScope.user.ehAdm == true}">
                            <c:if test="${jogo.foiJogado == false}">
                                <div class="card-body">
                                    <input type="submit" class="btn btn-primary" value="salvar">
                                </div>     
                            </c:if>
                        </c:if>
                    </div><br>
                </form>                    
            </c:forEach>
        </div>
    </div>
</div>
