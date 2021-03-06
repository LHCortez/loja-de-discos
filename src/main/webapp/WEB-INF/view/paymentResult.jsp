<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<tags:pageTemplate>

    <c:choose>
        <c:when test="${not empty error}">
            <p>Houve um problema com seu pagamento :(</p>
            <p>Tente novamente <a href='${pageContext.request.contextPath}/cart'>clicando aqui</a></p>
        </c:when>
    <c:otherwise>
        <div>
            <h3 style='color: green;'>Success!</h3>
            <div>Id.: ${id} </div>
            <div>Status: ${status}</div>
            <div>Charge id.: ${chargeId}</div>
            <div>Balance transaction id.: ${balance_transaction}</div>
            <div>Amount: R$ ${amount/100}</div>
        </div>
    </c:otherwise>
    </c:choose>

</tags:pageTemplate>
