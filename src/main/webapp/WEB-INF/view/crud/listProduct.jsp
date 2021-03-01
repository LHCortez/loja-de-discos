<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tags:crudTemplate>

    <c:if test="${not produtoSalvo.isEmpty()}">
        <p class="text-success">Produto salvo! ${tipoSalvo} "${produtoSalvo}"</p>
    </c:if>

    <ul class="nav nav-tabs crud-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link crud-tabs-link active" id="albuns-tab" data-bs-toggle="tab"
                    data-bs-target="#albuns" type="button" role="tab" aria-controls="albuns"
                    aria-selected="true">Álbuns</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link crud-tabs-link" id="camisetas-tab" data-bs-toggle="tab"
                    data-bs-target="#camisetas" type="button" role="tab" aria-controls="camisetas"
                    aria-selected="false">Camisetas</button>
        </li>
    </ul>
    <div class="tab-content crud-tab-content py-4 px-sm-4" id="myTabContent">
        <div class="tab-pane fade show active table-responsive" id="albuns" role="tabpanel" aria-labelledby="home-tab">

            <div class="text-end mb-3 me-3">
                <a href="${pageContext.request.contextPath}/crud/product/export/album">
                    <i class="fas fa-file-download fs-3"></i> EXPORTAR
                </a>
            </div>

            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th scope="col">Título</th>
                        <th scope="col" class="d-none d-sm-table-cell">Banda</th>
                        <th scope="col" class="d-none d-md-table-cell">Descrição</th>
                        <th scope="col">Formato</th>
                        <th scope="col" class="d-none d-sm-table-cell">Preço</th>
                        <th scope="col" class="d-none d-lg-table-cell">Lançamento</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Excluir</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${albuns}" var="album">
                    <tr>
                        <td><a href="${s:mvcUrl('DC#detalhe').arg(0, album.id).build()}">${album.nome}</a></td>
                        <td class="d-none d-sm-table-cell">${album.banda.nome}</td>
                        <td class="d-none d-md-table-cell">${album.descricao}</td>
                        <td>${album.tipo}</td>
                        <td class="d-none d-sm-table-cell">R$${album.preco}</td>
                        <td class="d-none d-lg-table-cell">${album.lancamento}</td>
                        <td class="text-center">
                            <a class="btn" href="${pageContext.request.contextPath}/crud/product/update/album/${album.id}">
                                <i class="fas fa-edit"></i>
                            </a>
                        </td>
                        <td class="text-center">
                            <form:form action="${s:mvcUrl('CPC#deleteProduct').arg(0, album.id).build()}" method="POST" id="delete-product-${album.id}">
                                <input name="id"  type="hidden" alt="Excluir" title="Excluir" />
                                <button class="btn" type="button"
                                        onclick="confirmDeleteProduct(event, ${album.id},
                                        '<s:message text="${album.nome}" javaScriptEscape="true"/>');">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <div class="tab-pane fade" id="camisetas" role="tabpanel" aria-labelledby="profile-tab">

            <div class="text-end mb-3 me-3">
                <a href="${pageContext.request.contextPath}/crud/product/export/camiseta">
                    <i class="fas fa-file-download fs-3"></i> EXPORTAR
                </a>
            </div>

            <table class="table table-hover crud-tabela">
                <thead>
                <tr>
                    <th scope="col">Título</th>
                    <th scope="col" class="d-none d-sm-table-cell">Banda</th>
                    <th scope="col" class="d-none d-md-table-cell">Descrição</th>
                    <th scope="col">Tamanho</th>
                    <th scope="col" class="d-none d-sm-table-cell">Preço</th>
                    <th scope="col" class="d-none d-lg-table-cell">Lançamento</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Excluir</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${camisetas}" var="camiseta">
                    <tr>
                        <td><a href="${s:mvcUrl('DC#detalhe').arg(0, camiseta.id).build()}">${camiseta.nome}</a></td>
                        <td class="d-none d-sm-table-cell">${camiseta.banda.nome}</td>
                        <td class="d-none d-md-table-cell">${camiseta.descricao}</td>
                        <td>${camiseta.size.toString()}</td>
                        <td class="d-none d-sm-table-cell">R$${camiseta.preco}</td>
                        <td class="d-none d-lg-table-cell">${camiseta.lancamento}</td>
                        <td class="text-center">
                            <a class="btn" href="${pageContext.request.contextPath}/crud/product/update/camiseta/${camiseta.id}">
                                <i class="fas fa-edit"></i>
                            </a>
                        </td>
                        <td class="text-center">
                            <form:form action="${s:mvcUrl('CPC#deleteProduct').arg(0, camiseta.id).build()}" method="POST" id="delete-product-${camiseta.id}">
                                <input name="id"  type="hidden" alt="Excluir" title="Excluir" />
                                <button class="btn" type="submit" onclick="confirmDeleteProduct(event, ${camiseta.id},
                                        '<s:message text="${camiseta.nome}" javaScriptEscape="true"/>')">
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


</tags:crudTemplate>