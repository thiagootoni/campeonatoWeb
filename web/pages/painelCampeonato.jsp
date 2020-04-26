
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card" style="width: 72rem;">
    <div class="card-header">
        Novo Campeonato
    </div>
    <div class="card-body">
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
    </div>
</div>
