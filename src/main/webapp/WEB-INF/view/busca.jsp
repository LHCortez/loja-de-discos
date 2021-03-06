<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="param" tagdir="/WEB-INF/tags" %>

<tags:pageTemplate paginatitulo="Busca -">

    <div class="container-xl">
        <div class="row w-100 mt-3 flex-sm-row-reverse">

            <div class="col-sm-12 col-md-9 rounded p-3" id="resultado">

                <h2 class="titulo pb-3">
                    <c:choose>
                        <c:when test="${buscaString == null}">
                            Busca
                        </c:when>
                        <c:otherwise>
                            Resultado "${buscaString}"
                        </c:otherwise>
                    </c:choose>
                </h2>

                <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-3 g-4">
                    <c:forEach items="${page.content}" var="produto">
                        <div class="col p-4 capa-card">
                            <a href="${s:mvcUrl('HC#detalhe').arg(0, produto.id).build()}" class="">
                                <div class="card">
                                    <img src="${pageContext.request.contextPath}${produto.capa}" class="card-img-top"
                                         alt="...">
                                    <div class="card-body pb-1">
                                        <h3 class="card-title fs-5">${produto.nome} - ${produto.tipo}
                                            <c:choose>
                                                <c:when test="${produto.tipo.equals('Camiseta')}">
                                                    (${produto.tamanho})
                                                </c:when>
                                            </c:choose>
                                        </h3>
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

                <nav aria-label="P�ginas">
                    <ul class="pagination flex-wrap justify-content-center mt-5">
                        <c:choose>
                            <c:when test="${hasPrevious}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="<param:appendParam name='page' value='${currentPage-2}'/>">
                                        Anterior
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Anterior</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="count" begin="1" end="${totalPages}">
                            <c:choose>
                                <c:when test="${count.equals(currentPage)}">
                                    <li class="page-item active">
                                        <a class="page-link"
                                           href="<param:appendParam name='page' value='${count-1}'/>">
                                                ${count}
                                        </a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="<param:appendParam name='page' value='${count-1}'/>">
                                                ${count}
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:choose>
                            <c:when test="${hasNext}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="<param:appendParam name='page' value='${currentPage}' />">
                                        Pr�ximo
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item disabled">
                                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Pr�ximo</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>

            </div>

            <div class="col-md-3 rounded p-4" id="busca-avancada">
                <h3 class="titulo-busca fs-5">CATEGORIAS</h3>
                <ul class="list-group list-group-flush mb-5">
                    <c:forEach items="${categorias}" var="categoria">
                        <li class="list-group-item">
                            <a href="${s:mvcUrl('BC#buscaPorCategoriaOuGenero').arg(0, categoria).build()}">${categoria}</a>
                        </li>
                    </c:forEach>
                </ul>
                <h3 class="titulo-busca fs-5">G�NEROS</h3>
                <ul class="list-group list-group-flush mb-5">
                    <c:forEach items="${generos}" var="genero">
                        <li class="list-group-item text-nowrap">
                            <a href="${s:mvcUrl('BC#buscaPorCategoriaOuGenero').arg(0, genero).build()}">${genero}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>


        </div>
    </div>

</tags:pageTemplate>