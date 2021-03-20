<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="carrinhoCompras" scope="session" class="com.luiz.lhcdiscos.models.CarrinhoCompras"/>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate paginatitulo="Meu carrinho -">

    <section id="carrinho" class="container-xl bg-light p-3 mt-3 rounded">
        <h2 class="titulo pb-3">Carrinho</h2>
        <c:choose>
            <c:when test="${carrinhoCompras.quantidadeTotal == 0}">
                <p>O seu carrinho est� vazio!</p>
                <p>Clique aqui e encontre diversos produtos de seu interesse! ;)</p>
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
                                    <a href="${s:mvcUrl('DC#detalhe').arg(0, produto.id).build()}">
                                        <img class="carrinho-imagem"
                                             src="${pageContext.request.contextPath}${produto.capa}"
                                             alt="...">
                                    </a>
                                </td>
                                <td data-title="Produto">
                                    <a href="${s:mvcUrl('DC#detalhe').arg(0, produto.id).build()}">
                                            ${produto.banda.nome} - ${produto.nome} - ${produto.tipo}
                                    </a>
                                    <p class="text-muted">Pre�o unit�rio: R$ ${produto.preco}</p>
                                </td>
                                <td data-title="Quantidade" class="text-center">
                                    <form:form role="form" action="${pageContext.request.contextPath}/cart/setquantidade"
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
                                    <form action="${s:mvcUrl('CC#remove').arg(0, produto.id).build()}" method="post">
                                        <button type="submit" class="border-0"><i class="fas fa-trash"></i></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2"></td>
                                <td class="fw-bold text-end" colspan="2">Valor total:</td>
                                <td class="text-end preco">R$ ${amountInCents/100}</td>
                            </tr>
                            <tr>
                                <td colspan="2"></td>
                                <td class="fw-bold text-end" colspan="2">Quantidade Total:</td>
                                <td class="text-end preco">${carrinhoCompras.quantidadeTotal}</td>
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
                                    // Esconder o bot�o padr�o do stripe
                                    document.getElementsByClassName("stripe-button-el")[0].style.display = 'none';
                                </script>
                                <button class="btn col-md-3 botao-destaque p-2" type="submit" >Finalizar compra</button>
                            </form:form>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                                <a class="btn col-md-3 botao-destaque p-2" href="${pageContext.request.contextPath}/user/login">
                                    Finalizar compra
                                </a>
                        </sec:authorize>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
</tags:pageTemplate>