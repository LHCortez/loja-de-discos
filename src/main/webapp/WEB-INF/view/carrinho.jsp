<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="carrinhoCompras" scope="session" class="com.luiz.lhcdiscos.models.CarrinhoCompras"/>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate paginatitulo="Meu carrinho -">

    <section id="carrinho">
        <div class="container-xl bg-light p-3 pb-5 mt-3 rounded">
            <h2 class="titulo pb-3">Carrinho</h2>
            <c:choose>
                <c:when test="${carrinhoCompras.quantidadeTotal == 0}">
                    <p>O seu carrinho está vazio.</p>
                    <p><a href="${s:mvcUrl('BC#busca').build()}" class="texto-cor-especial">Clique aqui</a>
                        e encontre diversos produtos de seu interesse! </p>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">

                        <table id="carrinho-tabela" class="table table-striped table-borderless table-light table-sm" >
                            <thead>
                            <tr>
                                <th scope="col" class="col-1"></th>
                                <th scope="col" class="col-6">Produto</th>
                                <th scope="col" class="col-1 text-center">Quantidade</th>
                                <th scope="col" class="col-1 text-end">Subtotal</th>
                                <th scope="col" class="col-1"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${produtosNoCarrinho.keySet()}" var="produto">
                                <tr>
                                    <td data-title="">
                                        <a href="${s:mvcUrl('HC#detalhe').arg(0, produto.id).build()}">
                                            <img class="carrinho-imagem"
                                                 src="${pageContext.request.contextPath}${produto.capa}"
                                                 alt="...">
                                        </a>
                                    </td>
                                    <td data-title="Produto">
                                        <a href="${s:mvcUrl('HC#detalhe').arg(0, produto.id).build()}"
                                           class="texto-cor-especial fw-bold">${produto.banda.nome} - ${produto.nome} - ${produto.tipo}
                                            <c:choose>
                                                <c:when test="${produto.tipo.equals('Camiseta')}">
                                                    (${produto.tamanho})
                                                </c:when>
                                            </c:choose>
                                        </a>
                                        <p class="text-muted">Preço unitário: R$ ${produto.preco}</p>

                                    </td>
                                    <td data-title="Quantidade" class="text-center">
                                        <form:form role="form" action="${pageContext.request.contextPath}/carrinho/setquantidade"
                                                method="post">
                                            <input type="hidden" value="${produto.id}" name="id">
                                            <select class="form-select form-select-sm w-0" aria-label="Selecione quantidade"
                                                    name="qnt" onchange="this.form.submit()">
                                                <c:set var = "quantidade" scope = "page"
                                                       value = "${produtosNoCarrinho.get(produto)}"/>
                                                <c:forEach var="qnt" begin="1" end="10" step="1" varStatus ="status">
                                                    <c:choose>
                                                        <c:when test="${quantidade != qnt}">
                                                            <option value="${qnt}">${qnt}</option>
                                                        </c:when>
                                                        <c:otherwise >
                                                            <option value="${qnt}" selected>${qnt}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </form:form>
                                    </td>
                                    <td data-title="Subtotal" class="text-end preco">
                                        R$ ${carrinhoCompras.getValorPorProduto(produto)}
                                    </td>
                                    <td class="text-center">
                                        <form:form action="${s:mvcUrl('CC#remove').arg(0, produto.id).build()}" method="post">
                                            <button type="submit" class="border-0"><i class="fas fa-trash"></i></button>
                                        </form:form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="2"></td>
                                <td class="fw-bold text-center text-nowrap" colspan="2">Quantidade Total:</td>
                                <td class="text-center preco">${carrinhoCompras.quantidadeTotal}</td>
                            </tr>
                                <tr>
                                    <td colspan="2"></td>
                                    <td class="fw-bold text-center text-nowrap" colspan="2">Valor total:</td>
                                    <td class="text-center preco">R$ ${amountInCents/100}</td>
                                </tr>
                            </tfoot>
                        </table>

    <%--                    https://stripe.com/docs/testing--%>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end p-4">
                            <sec:authorize access="isAuthenticated()">
                                <form:form action='${pageContext.request.contextPath}/charge' method='POST' id='checkout-form'>
                                    <input type='hidden' value='${amountInCents}' name='amount' />
                                    <sec:authentication property="principal.email" var="email"/>
                                    <script
                                            src='https://checkout.stripe.com/checkout.js'
                                            class='stripe-button'
                                            data-key='${stripePublicKey}'
                                            data-amount=${amountInCents},
                                            data-currency='${currency}'
                                            data-name='LHC Discos'
                                            data-description='Finalize seu pedido!'
                                            data-email='${email}'
                                            data-image
                                                    ='https://github.com/LHCortez/loja-de-discos/blob/main/src/main/resources/static/img/logo-transparent.png?raw=true'
                                            data-locale='pt-BR'
                                            data-zip-code='false'>
                                    </script>
                                    <script>
                                        // Esconder o botão padrão do stripe
                                        document.getElementsByClassName("stripe-button-el")[0].style.display = 'none';
                                    </script>
                                    <button class="btn col-md-3 botao-destaque p-2" type="submit" >Finalizar compra</button>
                                </form:form>
                            </sec:authorize>
                            <sec:authorize access="!isAuthenticated()">
                                <a class="btn col-md-3 botao-destaque p-2" href="${pageContext.request.contextPath}/usuario/login">
                                    Finalizar compra
                                </a>
                            </sec:authorize>
                        </div>
                    </div>
                    <p class="mt-3 fs-6 text-danger">ATENÇÃO: Esse site foi desenvolvido para a disciplina Trabalho de Conclusão de Curso do curso de especialização
                        em Desenvolvimento Web Full Stack da PUC Minas. Nenhum produto do site está disponível para venda.
                    </p>
                </c:otherwise>
            </c:choose>

        </div>

        <div id="carrinho-sugestoes" class="container-xl rounded mt-4 p-3">
            <h2 class="fs-6 titulo mb-4">Confira alguns de nossos lançamentos!</h2>

            <div class="sugestoes-card-group row row-cols-1 row-cols-sm-2 row-cols-md-4 row-cols-lg-5 g-4 mb-5">
                <c:forEach items="${produtos}" var="produto">
                    <div class="col p-2 capa-card sugestoes-card">
                        <a href="${s:mvcUrl('HC#detalhe').arg(0, produto.id).build()}" class="">
                            <div class="card sugestoes-card">
                                <img src="${pageContext.request.contextPath}${produto.capa}"
                                     class="card-img-top imagem-sugestoes" alt="...">
                                <div class="card-body pb-1">
                                    <h4 class="card-title texto-sugestoes">${produto.nome} - ${produto.tipo} -
                                        <c:choose>
                                            <c:when test="${produto.tipo.equals('Camiseta')}">
                                                ${produto.tamanho} -
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${produto.tipo.equals('Livro')}">
                                                ${produto.autor} -
                                            </c:when>
                                            <c:otherwise>
                                                ${produto.banda.nome} -
                                            </c:otherwise>
                                        </c:choose>
                                        R$ ${produto.preco}</h4>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>

        </div>
    </section>
</tags:pageTemplate>