<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate paginatitulo="Busca -">

    <div class="container">
        <div class="row w-100 mt-3 gap-3">
            <div class="col rounded p-4" id="busca-avancada">
                <h3 class="titulo-busca fs-5">CATEGORIAS</h3>
                <ul class="list-group list-group-flush mb-5">
                    <c:forEach items="${categorias}" var="categoria">
                        <li class="list-group-item">
                            <a href="${s:mvcUrl('BC#buscarPorCategoriaOuGenero').arg(0, categoria).build()}">${categoria}</a>
                        </li>
                    </c:forEach>
                </ul>
                <h3 class="titulo-busca fs-5">GÊNEROS</h3>
                <ul class="list-group list-group-flush mb-5">
                    <c:forEach items="${generos}" var="genero">
                        <li class="list-group-item">
                            <a href="${s:mvcUrl('BC#buscarPorCategoriaOuGenero').arg(0, genero).build()}">${genero}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-9 col-sm rounded p-3" id="resultado">
                <h2 class="titulo pb-3">Resultado</h2>
                <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-3 g-4">
                    <c:forEach items="${produtos}" var="produto">
                        <div class="col p-4 capa-card">
                            <a href="${s:mvcUrl('DC#detalhe').arg(0, produto.id).build()}" class="">
                                <div class="card">
                                    <img src="${pageContext.request.contextPath}${produto.capa}" class="card-img-top"
                                         alt="...">
                                    <div class="card-body pb-1">
                                        <h5 class="card-title">${produto.nome} - ${produto.tipo}</h5>
                                        <c:choose>
                                            <c:when test="${produto.tipo.equals('Livro')}">
                                                <p class="card-text">${produto.autor}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="card-text">${produto.banda.nome}</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="card-footer border-light pt-0 pb-0">
                                        <small class="text-muted">R$ ${produto.preco}</small>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>

</tags:pageTemplate>