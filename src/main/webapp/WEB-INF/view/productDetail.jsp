<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate>

    <section id="produto-detalhe" class="my-3">

        <div class="container">
            <div class="row gap-2">
                <div class="col-md-10 col-xs-12 bg-light rounded p-4">
                    <div class="row">
                        <div class="col-sm-4 col-xs-12">
                            <img src="${pageContext.request.contextPath}${produto.capa}" class="imagem-detalhe" alt="...">
                        </div>
                        <div class="col-sm-8 col-xs-12 px-4 container">
                            <c:choose>
                                <c:when test="${produto.tipo.equals('Livro')}">
                                    <h2 class="titulo">${produto.autor.toUpperCase()}</h2>
                                    <h3>${produto.nome}  (${produto.tipo})</h3>
                                    <p class="text-muted mb-0">Banda: ${produto.banda.nome}</p>
                                </c:when>
                                <c:otherwise>
                                    <h2 class="titulo">${produto.banda.nome.toUpperCase()}</h2>
                                    <h3>${produto.nome}  (${produto.tipo})</h3>
                                </c:otherwise>
                            </c:choose>
                            <p class="text-muted mb-0">Lançamento: ${produto.lancamento}</p>
                            <p class="text-muted mb-0">Gênero: ${produto.banda.genero.descricao}</p>
                            <h2 class="texto-cor-especial fs-1 my-3">R$ ${produto.preco}</h2>
                            <form:form action="${s:mvcUrl('CC#add').arg(0, produto.id).build()}" method="post">
                                <button class="btn btn-dark w-100 botao-adicionar-carrinho mt-2 mb-4 fs-5"
                                        type="submit">
                                    <i class="fas fa-cart-plus"></i>
                                    Adicionar ao carrinho
                                </button>
                            </form:form>
                            <p>${produto.descricao}</p>
                        </div>
                    </div>
                </div>
                <div class="col rounded p-2" id="sugestoes">
                    <h3 class="fs-6 titulo">Confira também esses produtos similares!</h3>
                    <div class="sugestoes-card-group">
                        <c:forEach items="${produtos}" var="produto">
                            <div class="col p-2 capa-card sugestoes-card">
                                <a href="${s:mvcUrl('DC#detalhe').arg(0, produto.id).build()}" class="">
                                    <div class="card sugestoes-card">
                                        <img src="${pageContext.request.contextPath}${produto.capa}"
                                             class="card-img-top imagem-sugestoes" alt="...">
                                        <div class="card-body pb-1">
                                            <h5 class="card-title texto-sugestoes">${produto.nome} -
                                                    ${produto.tipo} - ${produto.banda.nome} -
                                                R$ ${produto.preco}</h5>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </section>

</tags:pageTemplate>