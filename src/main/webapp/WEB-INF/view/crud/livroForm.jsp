<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:crudTemplate>

    <script>
        let id = ${livro.id};
        window.onload = function() {
            if (id != null){
                document.getElementById('album').style.display = 'none';
                document.getElementById('camiseta').style.display = 'none';
                document.getElementById('patch').style.display = 'none';
            }
        }

        let capa = '${livro.capa}';
        window.onload = function() {
            if (capa != null){
                document.getElementById('produto-capa').required = false;
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
            <a class="btn nav-link crud-tabs-link"
               href="${pageContext.request.contextPath}/crud/product/create/patch">Patch</a>
        </li>
        <li class="nav-item" role="presentation" id="livro">
            <a class="btn nav-link crud-tabs-link active" style="pointer-events: none;">Livro</a>
        </li>
    </ul>

    <div class="tab-content crud-tab-content py-4 px-sm-4" id="myTabContent">

        <c:choose>
            <c:when test="${livro.id != null}">
                <h3 class="titulo mb-4">Atualizar Livro</h3>
            </c:when>
            <c:otherwise>
                <h3 class="titulo mb-4">Cadastrar Livro</h3>
            </c:otherwise>
        </c:choose>

        <form:form modelAttribute="livro" action="${s:mvcUrl('CPC#saveLivro').build()}" method="POST"
                   enctype="multipart/form-data">
            <form:errors/>

            <fieldset class="nome row mb-3">
                <label for="produto-nome" class="col-md-2 col-lg-1 col-form-label">Nome</label>
                <div class="col-md-6 col-lg-4">
                    <form:input required="true" path="nome" type="text" class="form-control" id="produto-nome" value="${livro.nome}"/>
                    <form:errors path="nome" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="autor row mb-3">
                <label for="produto-autor" class="col-md-2 col-lg-1 col-form-label">Autor</label>
                <div class="col-md-6 col-lg-4">
                    <form:input required="true" path="autor" type="text" class="form-control" id="produto-autor" value="${livro.autor}"/>
                    <form:errors path="autor" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="paginas row mb-3">
                <label for="produto-paginas" class="col-md-2 col-xl-1 col-form-label">Páginas</label>
                <div class="col-md-3 col-xl-2">
                    <form:input required="true" path="paginas" type="number" min="1" class="form-control" step="1"
                                id="produto-paginas" value="${livro.paginas}"/>
                    <form:errors path="paginas" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="banda row mb-3">
                <label for="banda" class="col-md-2 col-lg-1 col-form-label">Banda</label>
                <div class="col-md-6 col-lg-4">
                    <form:select path="banda" id="banda" class="form-select" required="true">
                        <option value="" >Selecione a banda</option>
                        <c:forEach items="${bandas}" var="banda">
                            <c:choose>
                                <c:when test="${banda.nome.equalsIgnoreCase(livro.banda.nome)}">
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
                                   id="produto-descricao" value="${livro.descricao}"/>
                    <form:errors path="descricao" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="preco row mb-3">
                <label for="produto-preco" class="col-md-2 col-xl-1 col-form-label">Preço</label>
                <div class="col-md-3 col-xl-2">
                    <form:input required="true" path="preco" type="number" min="1" class="form-control" step="any"
                                id="produto-preco" value="${livro.preco}"/>
                    <form:errors path="preco" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="lancamento row mb-3">
                <label for="produto-lancamento" class="col-md-2 col-xl-1 col-form-label">Lançamento</label>
                <div class="col-md-3 col-xl-2">
                    <form:input required="true" path="lancamento" type="date" class="form-control"
                                id="produto-lancamento" value="${livro.lancamento}"/>
                    <form:errors path="preco" cssClass="error"/>
                </div>
            </fieldset>

            <fieldset class="capa row mb-3">
                <label for="produto-capa" class="col-md-2 col-lg-1 col-form-label">Capa</label>
                <div class="col-md-6 col-lg-4">
                    <input name="file" required type="file" class="form-control" id="produto-capa" />
                </div>
            </fieldset>

            <form:input type="hidden" value="${livro.capa}" path="capa"/>
            <form:input type="hidden" value="${livro.id}" path="id"/>
            <button type="submit" class="btn my-3 botao-destaque">Enviar</button>

        </form:form>

    </div>

</tags:crudTemplate>