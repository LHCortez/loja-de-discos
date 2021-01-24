<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>LHC Discos</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel='stylesheet' type='text/css' media='screen' href='./css/style.css'>
    <script src="https://kit.fontawesome.com/0641055230.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous">
    </script>
</head>

<body>
<div id="background-image"></div>
<div class="container-xl">
    <header>
        <section id="banner">
            <nav class="navbar navbar-light">
                <div id="container-banner" class="container d-flex justify-content-center justify-content-lg-between">
                    <!-- Logo gerado em: https://www.brandcrowd.com/maker/logo/barbarian-devil-esports-clan-19187#popup-colorpalette -->
                    <a class="navbar-brand"><img class="logo" src="./img/logo-transparent.png" alt=""></a>
                    <form id="form-busca" class="d-flex">
                        <input class="form-control me-2" type="search" placeholder="Pesquisar..." aria-label="Search">
                        <button class="btn botao-cor-especial" type="submit"><i class="fas fa-search"></i></button>
                    </form>
                    <ul class="nav" id="entrar-e-carrinho-link">
                        <li class="nav-item">
                            <a class="nav-link texto-cor-especial" href="#">ENTRAR</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link texto-cor-especial" href="#">CARRINHO (0)</a>
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
                        <a class="nav-link rounded-0 active" aria-current="page" href="#">HOME</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">DEATH METAL</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">DOOM METAL</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">BLACK METAL</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">HEAVY METAL</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">HARD CORE</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">METAL CORE</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">THRASH METAL</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link rounded-0" href="#">OUTROS</a>
                    </li>
                </ul>
        </section>
        </nav>
    </header>
    <main>
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
                                    <h5>First slide label</h5>
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
                <c:forEach items="${discos}" var="disco">
                    <div class="col p-3 capa-card-link">
                        <a href="#" class="">
                            <div class="card border-light">
                                <img src="${disco.capa}" class="card-img-top"
                                     alt="...">
                                <div class="card-body pb-1">
                                    <h5 class="card-title">${disco.titulo}</h5>
                                    <p class="card-text">${disco.banda.nome}</p>
                                </div>
                                <div class="card-footer border-light pt-0 pb-0">
                                    <small class="text-muted">R$ ${disco.preco}</small>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </section>
    </main>
    <footer id="footer" class="footer mt-auto py-3">
        <div class="container">
            <div class="row">
                <div class="col-sm text-start">
                    <h5 class="titulo">One of three columns</h5>
                </div>
                <div class="col-sm text-center">
                    <img class="logo" src="./img/logo-transparent.png" alt="">
                </div>
                <div class="col-sm text-sm-start text-md-end ">
                    <h5 class="titulo">One of three columns</h5>
                </div>
            </div>
        </div>
    </footer>
</div>
</body>

</html>