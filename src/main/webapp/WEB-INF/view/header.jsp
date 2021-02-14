<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:useBean id="carrinhoCompras" scope="session" class="com.luiz.lhcdiscos.models.CarrinhoCompras"/>

<header>
    <section id="banner">
        <nav class="navbar navbar-light">
            <div id="container-banner" class="container-xl d-flex justify-content-center justify-content-lg-between">
                <!-- Logo gerado em: https://www.brandcrowd.com/maker/logo/barbarian-devil-esports-clan-19187#popup-colorpalette -->
                <a class="navbar-brand"><img class="logo" src="${pageContext.request.contextPath}/img/logo-transparent.png" alt=""></a>

                <form action="${pageContext.request.contextPath}/search" method="get" id="form-busca" class="d-flex">
                    <input name="s" class="form-control me-2" type="search" placeholder="Pesquisar..." aria-label="Search">
                    <button class="btn botao-busca" type="submit"><i class="fas fa-search"></i></button>
                </form>

                <div class="d-flex">
                    <ul class="nav list-inline mx-auto justify-content-center" id="entrar-e-carrinho-link">
                        <sec:authorize access="!isAuthenticated()">
                            <li class="nav-item">
                                <a class="nav-link texto-cor-especial" href="${s:mvcUrl('UC#loginForm').build()}">ENTRAR</a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication property="principal" var="usuario" />
                           <li class="nav-item">
                               <a class="nav-link texto-cor-especial" href="${pageContext.request.contextPath}/user/logout">
                                    Ol�, ${usuario.nome} (SAIR)</a>
                           </li>
                        </sec:authorize>
                        <li class="nav-item">
                            <a class="nav-link texto-cor-especial" href="${s:mvcUrl('CC#carrinho').build()}">
                                CARRINHO (${carrinhoCompras.quantidadeTotal})
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
    </section>

<%--    <section class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog modal-dialog-centered">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title">Login</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    <form>--%>
<%--                        <div class="form-floating">--%>
<%--                            <input type="email" class="form-control mb-3" aria-describedby="emailHelp"--%>
<%--                                   aria-label="E-mail" id="floatingInput" placeholder="E-mail">--%>
<%--                            <label for="floatingInput">E-mail</label>--%>
<%--                        </div>--%>
<%--                        <div class="form-floating">--%>
<%--                            <input type="password" class="form-control mb-3" aria-label="Senha"--%>
<%--                                   id="floatingPassword" placeholder="Senha">--%>
<%--                            <label for="floatingInput">Senha</label>--%>
<%--                        </div>--%>
<%--                        <button type="submit" class="btn btn-primary w-100 mb-3">Entrar</button>--%>
<%--                    </form>--%>
<%--                    <div class="text-center text-muted delimiter mb-3">ou se logue usando uma rede social</div>--%>
<%--                    <div class="d-flex justify-content-center social-buttons gap-3">--%>
<%--                        <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip"--%>
<%--                                data-placement="top" title="Twitter">--%>
<%--                            <i class="fab fa-twitter"></i>--%>
<%--                        </button>--%>
<%--                        <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip"--%>
<%--                                data-placement="top" title="Facebook">--%>
<%--                            <i class="fab fa-facebook"></i>--%>
<%--                        </button>--%>
<%--                        <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip"--%>
<%--                                data-placement="top" title="Linkedin">--%>
<%--                            <i class="fab fa-linkedin"></i>--%>
<%--                        </button>--%>
<%--                        </div>--%>
<%--                </div>--%>
<%--                <div class="modal-footer justify-content-center">--%>
<%--                    <p>Ainda n�o � cadastrado? <a href="${s:mvcUrl('UC#createForm').build()}"--%>
<%--                                                  class="text-info">Clique aqui</a>.</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </section>--%>

    <section id="estilos" class="navbar-expand-sm navbar-dark">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav-estilos"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <nav class="collapse navbar-collapse nav-fill" id="nav-estilos">
            <ul class="nav nav-pills w-100">
                <li class="nav-item">
                    <a class="nav-link rounded-0" aria-current="page" href="${s:mvcUrl('HC#home').build()}">HOME</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="#">PROMO��ES</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="#">LAN�AMENTOS</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${s:mvcUrl('HC#disco').build()}">DISCOS</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${s:mvcUrl('HC#camiseta').build()}">CAMISETA</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${s:mvcUrl('BC#busca').build()}">BUSCA</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="#">CONTATO</a>
                </li>
            </ul>
        </nav>
    </section>
</header>
