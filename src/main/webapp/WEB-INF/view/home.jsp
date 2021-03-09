<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<tags:pageTemplate>

    <section id="carrossel-grid" class="container-xl">
        <div class="row">
            <div class="col-md-9 p-0">
                <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel"
                     data-bs-interval="4000">
                    <ol class="carousel-indicators">
                        <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"></li>
                        <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"></li>
                        <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="https://www.loudmagazine.net/wp-content/uploads/2019/03/darkthrone.jpg"
                                 class="d-block w-100 carrossel-imagem" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>ÇÇÇ First slide label</h5>
                                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img
                                    src="https://www.wearethepit.com/wp-content/uploads/2020/05/Behemoth_Grzegorz-Go%C5%82e%CC%A8biowski.jpg"
                                    class="d-block w-100 carrossel-imagem" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Second slide label</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img src="https://www.nuclearblast.de/en/data/bands/enslaved/news/_dsc2452.jpg"
                                 class="d-block w-100 carrossel-imagem" alt="...">
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Third slide label</h5>
                                <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                            </div>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button"
                       data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button"
                       data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </a>
                </div>
            </div>
            <div class="col-md-3 p-0">
                <a href="#"><img class="imagem-promocao"
                                 src="https://www.nuclearblast.de/en/data/imagedata/shop-startseite/button-leiste/kacheln/releases/harakiri-for-the-sky-kleine-kachel_1294x576px.jpg?x=275"
                                 alt=""></a>
                <a href="#"><img class="imagem-promocao"
                                 src="https://www.nuclearblast.de/en/data/imagedata/shop-startseite/button-leiste/kacheln/releases/magnum-kleine-kachel_1294x576px.jpg?x=275"
                                 alt=""></a>
                <a href="#"><img class="imagem-promocao"
                                 src="https://www.nuclearblast.de/en/data/imagedata/shop-startseite/button-leiste/kacheln/releases/frozen-soul-kleine-kachel_1294x576px.jpg?x=275"
                                 alt=""></a>
            </div>
        </div>
    </section>
    <section id="destaques" class="bg-light p-3 mt-5 rounded">
        <h2 class="titulo pb-3">Destaques</h2>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-5 g-4">
            <c:forEach items="${produtos}" var="produto">
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
    </section>

</tags:pageTemplate>