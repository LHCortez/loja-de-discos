<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:crudTemplate>


    <c:if test="${not bandaSalva.isEmpty()}">
        <p class="text-success">Banda salva! "${bandaSalva}"</p>
    </c:if>


    <ul class="nav nav-tabs crud-tabs navbar-dark" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button disabled class="nav-link crud-tabs-link active" id="bandas-tab" data-bs-toggle="tab"
                    data-bs-target="#bandas" type="button" role="tab" aria-controls="bandas"
                    aria-selected="true">Bandas</button>
        </li>
    </ul>

    <div class="tab-content crud-tab-content py-4 px-sm-4" id="myTabContent">
        <div class="tab-pane fade show active table-responsive" id="bandas" role="tabpanel" aria-labelledby="home-tab">

            <div class="row w-100 gap-3">
                <div class="col-sm-12 col-md-6 mx-3">
                    <input id="filtroBandaInput" onkeyup="filtro('filtroBandaInput', 'tabelaBandaBody')"
                           class="form-control me-2" type="search" placeholder="Filtrar..." aria-label="Search">
                </div>
                <div class="col">
                    <div class="text-end mb-3 me-3">
                        <a href="${pageContext.request.contextPath}/crud/bandas/export">
                            <i class="fas fa-file-download fs-3"></i> EXPORTAR
                        </a>
                    </div>
                </div>
            </div>


            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Gênero</th>
                    <th scope="col" class="text-center">Editar</th>
                    <th scope="col" class="text-center">Excluir</th>
                </tr>
                </thead>
                <tbody id="tabelaBandaBody">
                <c:forEach items="${bandas}" var="banda">
                    <tr>
                        <td><a href="#produtos_${banda.id}" data-bs-toggle="modal">
                                ${banda.nome}</a>
                        </td>
                        <td>${banda.genero.descricao}</td>
                        <td class="text-center">
                            <a class="btn" href="${pageContext.request.contextPath}/crud/bandas/update/${banda.id}">
                                <i class="fas fa-edit"></i>
                            </a>
                        </td>
                        <td class="text-center">
                            <form:form action="${s:mvcUrl('CBC#delete').arg(0, banda.id).build()}" method="POST" id="delete-band-${banda.id}">
                                <input name="id"  type="hidden" alt="Excluir" title="Excluir" />
                                <button class="btn" type="submit" onclick="confirmDeleteBand(event, ${banda.id},
                                        '<s:message text="${banda.nome}" javaScriptEscape="true"/>')">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <c:forEach items="${bandas}" var="banda">
        <div class="modal fade" id="produtos_${banda.id}" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">Produtos de ${banda.nome}</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <table class="table table-sm">
                            <thead>
                            <tr>
                                <th scope="col">Produto</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${banda.produtos}" var="produto">
                                <tr>
                                    <td>${produto.nome} (${produto.tipo})</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

</tags:crudTemplate>