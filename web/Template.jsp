<%-- 
    Document   : Template
    Created on : 19/04/2020, 17:12:08
    Author     : Thiago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <title>W3.CSS Template</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/mainW3.css">
    <link rel="stylesheet" href="assets/css/w3ThemeBlack.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--<link rel="stylesheet" href="assets/css/fontAwesomeMin.css">-->
    <style>
        html,
        body,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6 {
            font-family: "Roboto", sans-serif;
        }

        .w3-sidebar {
            z-index: 3;
            width: 250px;
            top: 43px;
            bottom: 0;
            height: inherit;
        }
    </style>

    <body>

        <!-- Navbar -->
        <div class="w3-top">
            <div class="w3-bar w3-theme w3-top w3-left-align w3-large">
                <a class="w3-bar-item w3-button w3-right w3-hide-large w3-hover-white w3-large w3-theme-l1"
                   href="javascript:void(0)" onclick="w3_open()"><i class="fa fa-bars"></i></a>
                <a href="#" class="w3-bar-item w3-button w3-theme-l1">The Ultimate Champion</a>
            </div>
        </div>

        <!-- Sidebar -->
        <nav class="w3-sidebar w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left" id="mySidebar">
            <a href="javascript:void(0)" onclick="w3_close()"
               class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu">
                <i class="fa fa-remove"></i>
            </a>
            <h4 class="w3-bar-item"><b>Menu</b></h4>
            <a class="w3-bar-item w3-button w3-hover-black" href="central?ac=home">Campeonato</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="central?ac=painelArtilharia">Artilharia</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="central?ac=historico">Histórico</a>

            <c:if test="${sessionScope.user.ehAdm == true}">
                <h4 class="w3-bar-item"><b>Menu Restrito</b></h4>
                <a class="w3-bar-item w3-button w3-hover-black" href="central?ac=painelCampeonato">+ Campeonato</a>
                <a class="w3-bar-item w3-button w3-hover-black" href="central?ac=painelTimes">+ Time</a>
                <a class="w3-bar-item w3-button w3-hover-black" href="central?ac=painelJogadores">+ Jogador</a>
            </c:if>
            <a class="w3-bar-item w3-button w3-hover-black" href="central?ac=logoutUsuario">Sair</a> 
        </nav>

        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu"
             id="myOverlay"></div>

        <!-- Main content: shift it to the right by 270 pixels when the sidebar is visible -->
        <div class="w3-main" style="margin-left:270px">

            <div class="w3-row w3-padding-64 conteinerPrincipal">

                <c:if test="${requestScope.erro ne null}">
                    <div class="alert alert-warning">${requestScope.erro}</div>                    
                </c:if>

                <!-- região injetável -->
                <c:catch var="ex">
                    <c:if test="${param.page == null}">
                        <jsp:include page="pages/home.jsp"/>
                    </c:if>
                    <c:if test="${param.page != null}">
                        <jsp:include page="pages/${param.page}.jsp"/>
                    </c:if> 
                </c:catch>
                <c:if test="${ex != null}">
                    <h2> ERROR: ${ex.message}</h2>
                </c:if>
            </div>

            <footer id="myFooter">
                <div class="w3-container w3-theme-l2 w3-padding-32">
                    <h4>Follow us <br>
                        <a href="https://github.com/thiagootoni" target="_blank">Thiago Otoni</a> e <a
                            href="https://github.com/hugoalexandre20" target="_blank">Hugo Alexandre</a>
                    </h4>
                </div>

                <div class="w3-container w3-theme-l1">
                    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
                </div>
            </footer>

            <!-- END MAIN -->
        </div>

        <script>
            // Get the Sidebar
            var mySidebar = document.getElementById("mySidebar");

            // Get the DIV with overlay effect
            var overlayBg = document.getElementById("myOverlay");

            // Toggle between showing and hiding the sidebar, and add overlay effect
            function w3_open() {
                if (mySidebar.style.display === 'block') {
                    mySidebar.style.display = 'none';
                    overlayBg.style.display = "none";
                } else {
                    mySidebar.style.display = 'block';
                    overlayBg.style.display = "block";
                }
            }

            // Close the sidebar with the close button
            function w3_close() {
                mySidebar.style.display = "none";
                overlayBg.style.display = "none";
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>

</html>
