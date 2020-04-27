
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card" style="width: 72rem;">
    <div class="card-header">
        Novo Campeonato
    </div>
    <div class="card-body" id="formCadCampeonato">
        <c:if test="${requestScope.temCampeonatoAberto == false}">
            <form action="central" method="post">
                <input type="hidden" name="ac" value="saveNewCampeonato">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="nomeCampeonato">Nome</label>
                        <input class="form-control" type="text" name="nomeCampeonato" id="nomeCampeonato" placeholder="nome do campeonato">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="numeroPlayers">Nº de Players</label>
                        <input class="form-control" type="number" id="numeroPlayers" name="numeroPlayers" min="2" max="10"/>
                        <small class="form-text text-muted">Escolha de 2 a 10 players</small>
                    </div>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </div>
            </form> 
        </c:if>
        <c:if test="${requestScope.temCampeonatoAberto == true}">
            <div class="alert-warning"> Já há um campeonato em aberto! Finalize-o para criar outro! </div>
        </c:if>
    </div>  
</div>
<div class="card" style="width: 72rem; margin-top: 30px;">
    <div class="card-header">
        Meus Campeonatos
    </div>
    <div class="card-body">
        <table class="table table-hover table-sm ">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Players</th>
                    <th scope="col">Status</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${requestScope.campsPainelDto != null} ">
                    <c:forEach var="campeonato" items="${requestScope.campsPainelDto}" varStatus="n">
                        <tr>
                            <th scope="row"$>{n.count}</th>                           
                            <td>${campeonato.nome}</td>                           
                            <td>${campeonato.qtdParticipantes}</td>                           
                            <td>${campeonato.status}</td>                           
                            <td>
                                <c:if test="${campeonato.status != 'Em aberto'}">
                                    <a class="btn btn-outline-danger btn-sm" href="central?ac=finalizarCampeonato&id=${campeonato.id}">Finalizar</a>
                                </c:if>
                                <a class="btn btn-outline-danger btn-sm" href="central?ac=excluirCampeonato&id=${campeonato.id}">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</div>