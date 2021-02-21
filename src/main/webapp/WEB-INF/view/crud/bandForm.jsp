<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:crudTemplate>

    <c:choose>
        <c:when test="${banda.id != 0}">
            <h3 class="titulo mb-4">Atualizar Banda</h3>
        </c:when>
        <c:otherwise>
            <h3 class="titulo mb-4">Cadastrar Banda</h3>
        </c:otherwise>
    </c:choose>


    <form:form modelAttribute="banda" action="${pageContext.request.contextPath}/crud/band/create" method="POST">
        <div class="row mb-3">
            <label for="banda-nome" class="col-md-1 col-form-label">Nome</label>
            <div class="col-md-4">
                <form:input required="true" path="nome" type="text" class="form-control" id="banda-nome" value="${banda.nome}"/>
                <form:errors path="nome" cssClass="error"/>
                <form:errors />
            </div>
        </div>
        <div class="row mb-3">
            <label for="banda-genero" class="col-md-1 col-form-label">Gênero</label>
            <div class="col-md-4">
                <form:select path="genero" id="banda-genero" class="form-select" >
                    <option value="" >Selecione o gênero</option>
                    <c:forEach items="${generos}" var="genero">
                        <c:choose>
                            <c:when test="${banda.genero.equalsIgnoreCase(genero)}">
                                <option value="${genero}" selected>${genero}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${genero}">${genero}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </form:select>
                <form:errors path="genero" cssClass="error"/>
            </div>
        </div>
        <form:input type="hidden" value="${banda.id}" path="id"/>
        <button type="submit" class="btn my-3 botao-destaque">Enviar</button>
    </form:form>


</tags:crudTemplate>