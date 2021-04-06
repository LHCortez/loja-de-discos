<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate paginatitulo="Pagamento -">

    <section id="pagamento-resultado" class="container-xl bg-light p-3 mt-3 rounded pb-5 mb-5">

    <c:choose>
        <c:when test="${error}">
            <h3 class="text-danger mb-4">Ooops...</h3>
            <p>Houve um problema com seu pagamento.</p>
            <p>Tente novamente <a href='${pageContext.request.contextPath}/carrinho' class="texto-cor-especial">clicando aqui</a>.</p>
            <p>Caso o problema persista, por favor entre em contato conosco.</p>
        </c:when>
        <c:otherwise>
            <h3 class="text-success mb-4">Parabéns!</h3>
            <p>O pagamento do seu pedido nº ${pedidoId}, foi realizado com sucesso!</p>
            <p>Valor pago: R$ ${valorPago}</p>
            <p>Enviamos um e-mail com os detalhes do seu pedido e informações sobre a entrega.</p>
            <p>Visualize seus pedidos <a href='${pageContext.request.contextPath}/usuario/pedidos' class="texto-cor-especial">clicando aqui</a>.</p>
        </c:otherwise>
    </c:choose>

    </section>

</tags:pageTemplate>
