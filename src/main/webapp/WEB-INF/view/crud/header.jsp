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
                        <a class="nav-link" href="${s:mvcUrl('CPC#productList').build()}">Listar Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${s:mvcUrl('CPC#albumForm').build()}">Cadastrar Produto</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${s:mvcUrl('CBC#bandList').build()}">Listar Bandas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${s:mvcUrl('CBC#bandForm').build()}">Cadastrar Banda</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${s:mvcUrl('GC#index').build()}">Estatísticas</a>
                    </li>
                </ul>
                <span class="navbar-text">
                        <a class="nav-link" href="${pageContext.request.contextPath}/user/logout">
                            Olá, <sec:authentication property="principal.usuarioName" /> (sair)
                        </a>
                    </span>
            </div>
        </div>
    </nav>
</header>

