<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:crudTemplate>

    <script>
        let id = '${camiseta.id}';
        let capa = '${camiseta.capa}';
        window.onload = function () {
            if (id) {
                document.getElementById('album').style.display = 'none';
                document.getElementById('patch').style.display = 'none';
                document.getElementById('livro').style.display = 'none';
            }
            if (capa) {
                document.getElementById('produto-capa').required = false;
            }
        }

        function alterarDisable() {
            var radios = document.getElementsByName("capaTipo");

            if (radios[0].checked) {
                document.getElementById("capa-url-input").disabled = false;
                document.getElementById("capa-url-input").required = true;
                document.getElementById("capa-upload-input").disabled = true;
                document.getElementById("capa-upload-input").required = false;
            } else if (radios[1].checked) {
                document.getElementById("capa-url-input").disabled = true;
                document.getElementById("capa-url-input").required = false;
                document.getElementById("capa-upload-input").disabled = false;
                document.getElementById("capa-upload-input").required = true;
            }
        }
    </script>


    <ul class="nav nav-tabs crud-tabs navbar-dark" id="myTab" role="tablist">
        <li class="nav-item" role="presentation" id="album">
            <a class="btn nav-link crud-tabs-link"
               href="${pageContext.request.contextPath}/crud/product/create/album">Álbum</a>
        </li>
        <li class="nav-item" role="presentation" id="camiseta">
            <a class="btn nav-link crud-tabs-link active" style="pointer-events: none;">Camiseta</a>
        </li>
        <li class="nav-item" role="presentation" id="patch">
            <a class="btn nav-link crud-tabs-link"
               href="${pageContext.request.contextPath}/crud/product/create/patch">Patch</a>
        </li>
        <li class="nav-item" role="presentation" id="livro">
            <a class="btn nav-link crud-tabs-link"
               href="${pageContext.request.contextPath}/crud/product/create/livro">Livro</a>
        </li>
    </ul>

    <div class="tab-content crud-tab-content py-4 px-sm-4" id="myTabContent">

        <c:choose>
            <c:when test="${camiseta.id != null}">
                <h3 class="titulo mb-4">Atualizar Camiseta</h3>
            </c:when>
            <c:otherwise>
                <h3 class="titulo mb-4">Cadastrar Camiseta</h3>
            </c:otherwise>
        </c:choose>

        <form:form modelAttribute="camiseta" action="${s:mvcUrl('CPC#saveCamiseta').build()}" method="POST"
                   enctype="multipart/form-data">

            <form:errors cssClass="text-danger"/>

            <fieldset class="nome row mb-3">
                <label for="produto-nome" class="col-md-2 col-lg-1 col-form-label">Nome</label>
                <div class="col-md-6 col-lg-4">
                    <form:input required="true" path="nome" type="text" class="form-control" id="produto-nome"
                                value="${camiseta.nome}"/>
                    <form:errors path="nome" cssClass="text-danger"/>
                </div>
            </fieldset>

            <fieldset class="tamanho row mb-3">
                <label for="tamanho" class="col-md-2 col-lg-1 col-form-label">Tamanho</label>
                <div class="col-md-4 col-xl-3">
                    <form:select path="size" id="tamanho" class="form-select" required="true">
                        <option value="">Selecione o tamanho</option>
                        <c:forEach items="${tamanhos}" var="tamanho">
                            <c:choose>
                                <c:when test="${camiseta.size.toString().equalsIgnoreCase(tamanho)}">
                                    <option value="${tamanho}" selected>${tamanho}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${tamanho}">${tamanho}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <form:errors path="size" cssClass="text-danger"/>
                </div>
            </fieldset>

            <fieldset class="banda row mb-3">
                <label for="banda" class="col-md-2 col-lg-1 col-form-label">Banda</label>
                <div class="col-md-6 col-lg-4">
                    <form:select path="banda" id="banda" class="form-select">
                        <option value="">Selecione a banda</option>
                        <c:forEach items="${bandas}" var="banda">
                            <c:choose>
                                <c:when test="${banda.nome.equalsIgnoreCase(camiseta.banda.nome)}">
                                    <option value="${banda.id}" selected>${banda.nome}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${banda.id}">${banda.nome}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <form:errors path="banda" cssClass="text-danger"/>
                </div>
            </fieldset>

            <fieldset class="descricao row mb-3">
                <label for="produto-descricao" class="col-md-2 col-lg-1 col-form-label">Descrição</label>
                <div class="col-md-6 col-lg-4">
                    <form:textarea required="true" path="descricao" type="text" class="form-control" rows="7"
                                   id="produto-descricao" value="${camiseta.descricao}"/>
                    <form:errors path="descricao" cssClass="text-danger"/>
                </div>
            </fieldset>

            <fieldset class="preco row mb-3">
                <label for="produto-preco" class="col-md-2 col-xl-1 col-form-label">Preço</label>
                <div class="col-md-3 col-xl-2">
                    <form:input required="true" path="preco" type="number" min="1" class="form-control" step="any"
                                id="produto-preco" value="${camiseta.preco}"/>
                    <form:errors path="preco" cssClass="text-danger"/>
                </div>
            </fieldset>

            <fieldset class="lancamento row mb-3">
                <label for="produto-lancamento" class="col-md-2 col-xl-1 col-form-label">Lançamento</label>
                <div class="col-md-3 col-xl-2">
                    <form:input required="true" path="lancamento" type="date" class="form-control"
                                id="produto-lancamento" value="${camiseta.lancamento}"/>
                    <form:errors path="preco" cssClass="text-danger"/>
                </div>
            </fieldset>
            <fieldset class="mb-3 mt-2">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="capaTipo" id="capa-url" value="capa-url"
                           onclick="alterarDisable()" checked>
                    <label class="form-check-label" for="capa-url">
                        Escrever URL da imagem da capa
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="capaTipo" id="capa-upload" value="capa-upload"
                           onclick="alterarDisable()">
                    <label class="form-check-label" for="capa-upload">
                        Fazer upload da capa
                    </label>
                </div>
            </fieldset>

            <fieldset class="capa row mb-3">
                <label for="capa-url-input" class="col-md-2 col-lg-1 col-form-label">Capa (URL)</label>
                <div class="col-md-6 col-lg-4">
                    <input name="capaUrl" type="text" class="form-control" id="capa-url-input" value="${camiseta.capa}"
                           required/>
                </div>
            </fieldset>

            <fieldset class="capa row mb-3">
                <label for="capa-upload-input" class="col-md-2 col-lg-1 col-form-label">Capa (Upload)</label>
                <div class="col-md-6 col-lg-4">
                    <input name="file" type="file" class="form-control" id="capa-upload-input" disabled/>
                </div>
            </fieldset>

            <form:input type="hidden" value="${camiseta.capa}" path="capa"/>
            <form:input type="hidden" value="${camiseta.id}" path="id"/>

            <button type="submit" class="btn my-3 botao-destaque">Enviar</button>

        </form:form>

    </div>

</tags:crudTemplate>