<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate>

    <section id="pagamento-resultado" class="container-xl bg-light p-3 mt-3 rounded">

    <c:choose>
        <c:when test="${not empty error}">
            <h3 class="text-danger">Ooops...</h3>
            <p>Houve um problema com seu pagamento :(</p>
            <p>Tente novamente <a href='${pageContext.request.contextPath}/cart'>clicando aqui</a>.</p>
            <p>Caso o problema persista, por favor entre em contato conosco.</p>
        </c:when>
    <c:otherwise>
        <div>
            <h3 class="text-success">Success!</h3>
<%--            <div>Id.: ${id} </div>--%>
<%--            <div>Status: ${status}</div>--%>
<%--            <div>Charge id.: ${chargeId}</div>--%>
<%--            <div>Balance transaction id.: ${balance_transaction}</div>--%>
<%--            <div>Amount: R$ ${amount/100}</div>--%>
        </div>
    </c:otherwise>
    </c:choose>

    </section>

</tags:pageTemplate>
