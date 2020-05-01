<%-- 
    Document   : home.jsp
    Created on : 19/04/2020, 17:58:14
    Author     : Thiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h3> E a� ${sessionScope.user.nome}, blz? </h3>
</div>
<div>
    <c:if test="${requestScope.campeonato.status.getNome() == 'Em aberto'}">
        <c:if test="${sessionScope.user.ehAdm == true}">
            <div class="card">
                <p class="alert-warning"> Campeonato em aberto, para iniciar v� at� o menu campeonato e escolha a op��o desejada</p>
            </div> 
        </c:if>
        <c:if test="${sessionScope.user.ehAdm == false}">
            <div class="card">
                <p class="alert-warning"> Calma parsa, j� j� o Adm libera os jogos! </p>
            </div> 
        </c:if>
    </c:if>
    <!-- Imprimir a tabela com a pontua��o -->
</div>
