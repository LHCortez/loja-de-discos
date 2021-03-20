<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="carrinhoCompras" scope="session" class="com.luiz.lhcdiscos.models.CarrinhoCompras"/>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tags:pageTemplate paginatitulo="Meus pedidos -">

    <section id="carrinho" class="container-xl bg-light p-3 mt-3 rounded">
        <h2 class="titulo">Meus pedidos</h2>
        <c:choose>
            <c:when test="${pedidos.isEmpty()}">
                <p>Você ainda não possui nenhum pedido.</p>
                <p>Clique aqui e encontre diversos produtos de seu interesse!</p>
            </c:when>
            <c:otherwise>

                <c:forEach items="${pedidos}" var="pedido">
                    <fmt:parseDate value="${pedido.data}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" type="both" />
                    <div class="container px-1 px-md-4 mx-auto">
                        <div class="card order-card">
                            <div class="row d-flex justify-content-between px-3 top">
                                <div class="d-flex">
                                    <h5 class="texto-cor-especial">PEDIDO nº ${pedido.id}</h5>
                                </div>
                                <div class="d-flex flex-column text-sm-right">
                                    <p class="mb-0">Realizado em <span><fmt:formatDate pattern="dd/MM/yyyy" value="${parsedDate}" /></span></p>
                                     <c:forEach items="${pedido.itens}" var="item">
                                        <p class="mb-0">&emsp; ${item.quantidade} x ${item.nome} (${item.tipo})</p>
                                     </c:forEach>
                                    <p class="mb-0">Valor total: R$ ${pedido.pagamento.valorPago}</p>
                                </div>
                            </div>

                            <div class="row d-flex justify-content-center">
                                <div class="col-12">
                                    <ol class="progressbar text-center">
                                        <li class="active step0"></li>
                                        <li class="active step0"></li>
                                        <li class="step0"></li>
                                        <li class="step0"></li>
                                    </ol>
                                </div>
                            </div>

                            <div class="row d-flex">
                                <div class="col-12">
                                    <ol class="list-group list-group-horizontal list-inline">
                                        <li class="icon-content m-auto">
                                            <img class="progress-icon" src="https://i.imgur.com/9nnc9Et.png" alt="Pedido processado">
                                            <p class="font-weight-bold progress-descricao processado">Processado</p>
                                        </li>
                                        <li class="icon-content m-auto">
                                            <img class="progress-icon" src="https://i.imgur.com/u1AzR7w.png" alt="Pedido enviado">
                                            <p class="font-weight-bold progress-descricao preparacao">Em preparação</p>
                                        </li>
                                        <li class="icon-content m-auto">
                                            <img class="progress-icon" src="https://i.imgur.com/TkPm63y.png" alt="Pedido em rota">
                                            <p class="font-weight-bold progress-descricao enviado">Enviado</p>
                                        </li>
                                        <li class="icon-content m-auto">
                                            <img class="progress-icon" src="https://i.imgur.com/HdsziHP.png" alt="Pedido entregue">
                                            <p class="font-weight-bold progress-descricao entregue">Entregue</p>
                                        </li>
                                    </ol>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>


    </section>
</tags:pageTemplate>