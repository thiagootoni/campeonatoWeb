<%-- 
    Document   : index
    Created on : 09/04/2020, 21:04:01
    Author     : Thiago
--%>

<%@page import="model.dao.impl.TimeDao"%>
<%@page import="model.DTO.TimeDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html lang="pt-br">

    <head>
        <title>The Ultimate Championship</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="assets/css/mainLogin.css" />

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <scrishow shpt src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>

<body>

    <!-- Header -->
    <header id="header">
        <a href="#banner" class="logo"><strong>The Ultimate Championship</strong></a>
        <nav>
            <a href="#menu">Login</a>
        </nav>
    </header>

    <!-- Nav -->
    <nav id="menu">
        <ul class="links">
            <!-- colocar um form de login aqui -->
            <form action="central" method="post">
                <input type="hidden" name="ac" value="logarUsuario"/>
                <li><label for="iptLogin">Login</label></li>
                <li><input type="text" placeholder="login" name="iptLogin" required></li>
                <li><label for="iptSenha">Senha</label></li>
                <li><input type="password" placeholder="senha" name="iptSenha" required></li><br>
                <li style="text-align: center;"><input type="submit" value="Entrar" name="Entrar"></li>
            </form>
        </ul>
    </nav>

    <!-- Banner -->
    <section id="banner" style="background-color: #009688">
        <form action ="central" method="post">
            <div class="inner">
                <h1>Este é o The Ultimate Championship! <br />
                    Conquiste o reinado do torneio!</h1>
                <!-- buscar se há campeonato aberto e quantas vagas disponíveis -->

                <c:if test="${not empty requestScope.nomeCampeonato}">
                    <h3> ${requestScope.nomeCampeonato} rolando! 
                    <c:if test ="${requestScope.timesdisponiveis.size() >0 && requestScope.timesdisponiveis.size() ==1}">
                         Resta apenas 1 vaga!</h3><br>
                    </c:if>
                    <c:if test="${requestScope.timesdisponiveis.size() > 1}">
                        <h3>Restam ${requestScope.timesdisponiveis.size()} vagas</h3><br>
                    </c:if>
                    <c:if test="${requestScope.timesdisponiveis.size() == 0 || requestScope.timesdisponiveis == null}">
                        <h3>Não há vagas disponíveis fera</h3><br>
                    </c:if>
                </c:if>
                        
                <ul class="actions">
                    <li><a href="#formularioCadastro" class="button alt scrolly big">Cadastrar</a></li>
                </ul>
                <c:if test="${requestScope.erro != null}">
                    <a href="#" class="button alt scrolly"> ${requestScope.erro} </a>
                </c:if>
            </div>
        </form>
    </section>

    <!-- Cadastro -->

    <div id="formularioCadastro">
        <!-- Default form register -->
        <form class="text-center border border-light p-5" action="central" method="post">
            <input type="hidden" name="ac" value="saveUser">
            <p class="h4 mb-4">Cadastre-se</p>

            <!-- Nome -->
            <input type="text" id="defaultRegisterFormFirstName" class="form-control" placeholder="Nome"
                   name="iptNome"><br>

            <!-- Login -->
            <input type="text" id="defaultRegisterFormFirstName" class="form-control mb-4" placeholder="Login"
                   name="iptLogin">

            <!-- Senha -->
            <input type="password" id="defaultRegisterFormPassword" class="form-control" placeholder="Senha"
                   aria-describedby="defaultRegisterFormPasswordHelpBlock" name="iptSenha">

            <!-- Repetir Senha --> <br>                        
            <label for="time"> Time: </label>
            <select id="time" name="slcTime">
                <c:if test="${requestScope.timesdisponiveis.size() != 0}">
                    <c:forEach var="time" items="${requestScope.timesdisponiveis}"> 
                        <option value="${time.id}">${time.nome}</option>
                    </c:forEach>
                </c:if> 

                <!--<option value="barcelona"> Barcelona </option>
                    <option value="realmadrid" > Real Madrid </option> 
                    <option value="juventus" > Juventus </option> 
                    <option value="gremio" selected> Gremio </option> -->                            
            </select>

            <!-- Botão Cadastrar -->
            <button class="btn btn-info my-4 btn-block" style="background-color:  #009688" type="submit">Cadastrar</button>
            <hr>
            <!-- Termos de Serviço-->
            <p>Clicando em <em>Cadastrar</em> você concrda com nossos <a href="" target="_blank">termos de
                    serviço</a>

        </form>
    </div>

    <!-- Footer -->
    <footer id="footer">
        <div class="copyright">
            <h3>Follow Us</h3>
            <ul class="icons">
                <li>Thiago Otoni &nbsp;<a href="https://github.com/thiagootoni" class="icon fa-github"><span
                            class="label">Github</span></a></li>
                <li>Hugo Alexandre &nbsp;<a href="https://github.com/hugoalexandre20" class="icon fa-github"><span
                            class="label">Github</span></a></li>
            </ul>
        </div>
    </footer>

    <!-- Scripts -->
    <script src="assets/js/login/jquery.min.js"></script>
    <script src="assets/js/login/jquery.scrolly.min.js"></script>
    <script src="assets/js/login/skel.min.js"></script>
    <script src="assets/js/login/util.js"></script>
    <script src="assets/js/login/main.js"></script>

</body>

</html>
