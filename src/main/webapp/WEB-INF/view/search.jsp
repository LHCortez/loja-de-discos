<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate>

    <div class="container">
        <div class="row w-100 mt-3 gap-3">
            <div class="col rounded p-4" id="busca-avancada">
                <h3 class="titulo-busca fs-5">CATEGORIAS</h3>
            </div>
            <div class="col-md-9 col-sm rounded p-3" id="resultado">
                <h2 class="titulo pb-3">Resultado</h2>
                <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-3 g-4">
                    <c:forEach items="${produtos}" var="produto">
                        <div class="col p-4 capa-card">
                            <a href="#" class="">
                                <div class="card">
                                    <img src="${produto.capa}" class="card-img-top"
                                         alt="...">
                                    <div class="card-body pb-1">
                                        <h5 class="card-title">${produto.nome} - ${produto.tipoProduto}</h5>
                                        <p class="card-text">${produto.banda.nome}</p>
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