<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid container-xl">
            <a class="navbar-brand" href="${s:mvcUrl('HC#home').build()}">
                <img class="logo-crud" src="${pageContext.request.contextPath}/img/logo-transparent.png" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                    aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${s:mvcUrl('CC#productList').build()}">Listar Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Cadastrar Produto</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${s:mvcUrl('CC#bandList').build()}">Listar Bandas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${s:mvcUrl('CC#createBandForm').build()}">Cadastrar Banda</a>
                    </li>
                </ul>
                <span class="navbar-text">
                        <sec:authentication property="principal" var="usuario" />
                        <%-- TODO: ajustar o usu�rio depois de colocar a p�gina no spring security--%>
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/logout">
                            Ol�, $usuario.nome} (SAIR)
                        </a>
                    </span>
            </div>
        </div>
    </nav>
</header>

