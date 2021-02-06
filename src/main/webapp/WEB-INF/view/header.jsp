<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<jsp:useBean id="carrinhoCompras" scope="session" class="com.luiz.lhcdiscos.models.CarrinhoCompras"/>

<header>
    <section id="banner">
        <nav class="navbar navbar-light">
            <div id="container-banner" class="container-xl d-flex justify-content-center justify-content-lg-between">
                <!-- Logo gerado em: https://www.brandcrowd.com/maker/logo/barbarian-devil-esports-clan-19187#popup-colorpalette -->
                <a class="navbar-brand"><img class="logo" src="${pageContext.request.contextPath}/img/logo-transparent.png" alt=""></a>
                <form action="${pageContext.request.contextPath}/search" method="get" id="form-busca" class="d-flex">
                    <input name="s" class="form-control me-2" type="search" placeholder="Pesquisar..." aria-label="Search">
                    <button class="btn botao-cor-especial" type="submit"><i class="fas fa-search"></i></button>
                </form>
                <ul class="nav" id="entrar-e-carrinho-link">
                    <li class="nav-item">
                        <a class="nav-link texto-cor-especial" href="#">ENTRAR</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link texto-cor-especial" href="${s:mvcUrl('CC#carrinho').build()}">
                            CARRINHO (${carrinhoCompras.quantidadeTotal})
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </section>

    <section id="estilos" class="navbar-expand-sm navbar-dark">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav-estilos"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <nav class="collapse navbar-collapse nav-fill" id="nav-estilos">
            <ul class="nav nav-pills w-100">
                <li class="nav-item">
                    <a class="nav-link rounded-0 active" aria-current="page" href="${s:mvcUrl('HC#home').build()}">HOME</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="#">PROMOÇÕES</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link rounded-0" href="#">LANÇAMENTOS</a>
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