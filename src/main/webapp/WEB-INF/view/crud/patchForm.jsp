<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:crudTemplate>

    <script>
        let id = ${patch.id};
        window.onload = function() {
            if (id != null){
                document.getElementById('album').style.display = 'none';
                document.getElementById('camiseta').style.display = 'none';
                document.getElementById('livro').style.display = 'none';
            }
        }
    </script>

    <ul class="nav nav-tabs crud-tabs navbar-dark" id="myTab" role="tablist">
        <li class="nav-item" role="presentation" id="album">
            <a class="btn nav-link crud-tabs-link"
               href="${pageContext.request.contextPath}/crud/product/create/album">Álbum</a>
        </li>
        <li class="nav-item" role="presentation" id="camiseta">
            <a class="btn nav-link crud-tabs-link"
               href="${pageContext.request.contextPath}/crud/product/create/camiseta">Camiseta</a>
        </li>
        <li class="nav-item" role="presentation" id="patch">
            <a class="btn nav-link crud-tabs-link active" style="pointer-events: none;">Patch</a>
        </li>
        <li class="nav-item" role="presentation" id="livro">
            <a class="btn nav-link crud-tabs-link"
               href="${pageContext.request.contextPath}/crud/product/create/livro">Livro</a>
        </li>
    </ul>

    <div class="tab-content crud-tab-content py-4 px-sm-4" id="myTabContent">

        <c:choose>
            <c:when test="${patch.id != null}">
                <h3 class="titulo mb-4">Atualizar Patch</h3>
            </c:when>
            <c:otherwise>
                <h3 class="titulo mb-4">Cadastrar Patch</h3>
            </c:otherwise>
        </c:choose>

        <form:form modelAttribute="patch" action="${pageContext.request.contextPath}/crud/product/create/patch" method="POST">
            <form:errors/>

            <fieldset class="nome row mb-3">
                <label for="produto-nome" class="col-md-2 col-lg-1 col-form-label">Nome</label>
                <div class="col-md-6 col-lg-4">
                    <form:input required="true" path="nome" type="text" class="form-control" id="produto-nome" value="${patch.nome}"/>
                    <form:errors path="nome" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="banda row mb-3">
                <label for="banda" class="col-md-2 col-lg-1 col-form-label">Banda</label>
                <div class="col-md-6 col-lg-4">
                    <form:select path="banda" id="banda" class="form-select" required="true">
                        <option value="" >Selecione a banda</option>
                        <c:forEach items="${bandas}" var="banda" >
                            <c:choose>
                                <c:when test="${banda.nome.equalsIgnoreCase(patch.banda.nome)}">
                                    <option value="${banda.id}" selected>${banda.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${banda.id}">${banda.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <form:errors path="banda" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="descricao row mb-3">
                <label for="produto-descricao" class="col-md-2 col-lg-1 col-form-label">Descrição</label>
                <div class="col-md-6 col-lg-4">
                    <form:textarea required="true" path="descricao" type="text" class="form-control" rows="7"
                                   id="produto-descricao" value="${patch.descricao}"/>
                    <form:errors path="descricao" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="preco row mb-3">
                <label for="produto-preco" class="col-md-2 col-xl-1 col-form-label">Preço</label>
                <div class="col-md-3 col-xl-2">
                    <form:input required="true" path="preco" type="number" min="1" class="form-control" step="any"
                                id="produto-preco" value="${patch.preco}"/>
                    <form:errors path="preco" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="lancamento row mb-3">
                <label for="produto-lancamento" class="col-md-2 col-xl-1 col-form-label">Lançamento</label>
                <div class="col-md-3 col-xl-2">
                    <form:input required="true" path="lancamento" type="date" class="form-control"
                                id="produto-lancamento" value="${patch.lancamento}"/>
                    <form:errors path="preco" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="capa row mb-3">
                <label for="produto-capa" class="col-md-2 col-lg-1 col-form-label">Capa</label>
                <div class="col-md-6 col-lg-4">
                    <form:input required="true" path="capa" type="text" class="form-control" id="produto-capa" value="${patch.capa}"/>
                    <form:errors path="capa" cssClass="error"/>
                </div>
            </fieldset>

        <form:input type="hidden" value="${patch.id}" path="id"/>
        <button type="submit" class="btn my-3 botao-destaque">Enviar</button>
    </form:form>

    </div>

</tags:crudTemplate>