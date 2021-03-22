<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<tags:pageTemplate paginatitulo="">

    <section id="carrossel-grid" class="container-xl">
        <div class="row">
            <div class="col-md-9 p-0">
                <div id="carousel" class="carousel slide" data-bs-ride="carousel"
                     data-bs-interval="4000">
                    <ol class="carousel-indicators">
                        <li data-bs-target="#carousel" data-bs-slide-to="0" class="active"></li>
                        <li data-bs-target="#carousel" data-bs-slide-to="1"></li>
                        <li data-bs-target="#carousel" data-bs-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <a href="${pageContext.request.contextPath}/product/14">
                                <img src="${pageContext.request.contextPath}/img/carrossel-paradiselost-obsidian.png"
                                    class="d-block w-100 carrossel-imagem" alt="...">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${pageContext.request.contextPath}/product/12">
                                <img src="${pageContext.request.contextPath}/img/carrossel-enslaved-utgard.png"
                                        class="d-block w-100 carrossel-imagem" alt="...">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${pageContext.request.contextPath}/product/15">
                                <img src="${pageContext.request.contextPath}/img/carrossel-sepultura-quadra.png"
                                     class="d-block w-100 carrossel-imagem" alt="...">
                            </a>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carousel" role="button"
                       data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Anterior</span>
                    </a>
                    <a class="carousel-control-next" href="#carousel" role="button"
                       data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Próximo</span>
                    </a>
                </div>
            </div>
            <div class="col-md-3 p-0 text-center">
                <a href="${pageContext.request.contextPath}/product/16"><img class="imagem-promocao"
                                 src="${pageContext.request.contextPath}/img/pequeno-moonspell.png"
                                 alt=""></a>
                <a href="${pageContext.request.contextPath}/product/17"><img class="imagem-promocao"
                                 src="${pageContext.request.contextPath}/img/pequeno-paradiselost.png"
                                 alt=""></a>
                <a href="${pageContext.request.contextPath}/product/2"><img class="imagem-promocao"
                                 src="${pageContext.request.contextPath}/img/pequeno-mercyfulfate.png"
                                 alt=""></a>
            </div>
        </div>
    </section>
    <section id="destaques" class="bg-light p-3 mt-5 rounded">
        <h2 class="titulo pb-3">Destaques</h2>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-5 g-4">
            <c:forEach items="${page.content}" var="produto">
                <div class="col p-3 capa-card">
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


        <nav aria-label="Páginas">
            <ul class="pagination justify-content-center">
                <c:choose>
                    <c:when test="${hasPrevious}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/?page=${currentPage-2}">Anterior</a>
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
                            <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/?page=${count-1}">${count}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/?page=${count-1}">${count}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:choose>
                    <c:when test="${hasNext}">
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/?page=${currentPage}">Próximo</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Próximo</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>


    </section>

</tags:pageTemplate>