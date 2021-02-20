<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:crudTemplate>

    <h3 class="titulo mb-4">Cadastrar Banda</h3>

    <form:form modelAttribute="banda" action="${pageContext.request.contextPath}/crud/create/band" method="POST">
        <div class="row mb-3">
            <label for="banda-nome" class="col-md-1 col-form-label">Nome</label>
            <div class="col-md-4">
                <form:input required="true" path="nome" type="text" class="form-control" id="banda-nome" size="30"/>
                <form:errors path="nome" cssClass="error"/>
            </div>
        </div>
        <div class="row mb-3">
            <label for="banda-genero" class="col-md-1 col-form-label">Gênero</label>
            <div class="col-md-4">
                <form:select path="genero" id="banda-genero" class="form-select" >
                    <option value="" >Selecione o gênero</option>
                    <c:forEach items="${generos}" var="genero">
                        <option value="${genero}">${genero}</option>
                    </c:forEach>
                </form:select>
                <form:errors path="genero" cssClass="error"/>
            </div>
        </div>
        <button type="submit" class="btn my-3 botao-destaque">Cadastrar</button>
    </form:form>


</tags:crudTemplate>