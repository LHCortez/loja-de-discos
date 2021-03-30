<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:useBean id="carrinhoCompras" scope="session" class="com.luiz.lhcdiscos.models.CarrinhoCompras"/>

<header>
    <section id="banner container-xl">
        <div id="container-banner">
            <!-- Logo gerado em: https://www.brandcrowd.com/maker/logo/barbarian-devil-esports-clan-19187#popup-colorpalette -->
            <a href="${s:mvcUrl('HC#home').build()}">
                <img class="logo mx-auto d-block pt-2" src="${pageContext.request.contextPath}/img/logo-transparent.png" alt="">
            </a>

            <form action="${pageContext.request.contextPath}/search" method="get" id="form-busca"
                  class="d-flex mx-auto mt-3 mb-3">
                <input name="s" class="form-control me-2" type="search" placeholder="Pesquisar..." aria-label="Search">
                <button class="btn botao-busca" type="submit"><i class="fas fa-search"></i></button>
            </form>
        </div>

        <ul class="list-inline list-group list-group-horizontal-md d-flex justify-content-end mb-2 text-end" id="entrar-e-carrinho-link">
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/logout">
                        Olá, <sec:authentication property="principal.usuarioName" />  (sair)</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/user/orders">Meus Pedidos</a>
                </li>
            </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="${s:mvcUrl('UC#loginForm').build()}">ENTRAR</a>
                </li>
            </sec:authorize>

            <sec:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="${s:mvcUrl('CPC#productList').build()}">
                        Área do Administrador
                    </a>
                </li>
            </sec:authorize>

            <li class="nav-item">
                <a class="nav-link" href="${s:mvcUrl('CC#carrinho').build()}">
                    CARRINHO (${carrinhoCompras.quantidadeTotal})
                </a>
            </li>
        </ul>

    </section>


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
                    <a class="nav-link rounded-0" href="${s:mvcUrl('HC#lancamento').build()}">LANÇAMENTOS</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${s:mvcUrl('HC#disco').build()}">ÁLBUNS</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${s:mvcUrl('HC#merchandise').build()}">MERCHANDISE</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${s:mvcUrl('HC#livro').build()}">LIVROS</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${s:mvcUrl('BC#busca').build()}">BUSCA</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="${pageContext.request.contextPath}/contact">CONTATO</a>
                </li>
            </ul>
        </nav>
    </section>
</header>
