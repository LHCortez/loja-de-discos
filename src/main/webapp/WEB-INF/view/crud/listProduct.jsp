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
        <li class="nav-item" role="presentation">
            <button class="nav-link crud-tabs-link" id="patches-tab" data-bs-toggle="tab"
                    data-bs-target="#patches" type="button" role="tab" aria-controls="patches"
                    aria-selected="false">Patches</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link crud-tabs-link" id="livros-tab" data-bs-toggle="tab"
                    data-bs-target="#livros" type="button" role="tab" aria-controls="livros"
                    aria-selected="false">Livros</button>
        </li>
    </ul>

    <div class="tab-content crud-tab-content py-4 px-sm-4" id="myTabContent">

        <div class="tab-pane fade show active table-responsive" id="albuns" role="tabpanel" aria-labelledby="albuns-tab">

            <div class="row w-100 gap-3">
                <div class="col-sm-12 col-md-6 mx-3">
                    <input id="filtroAlbunsInput" onkeyup="filtro('filtroAlbunsInput', 'tabelaAlbumBody')" class="form-control me-2" type="search" placeholder="Filtrar..." aria-label="Search">
                </div>
                <div class="col">
                    <div class="text-end mb-3 me-3">
                        <a href="${pageContext.request.contextPath}/crud/product/export/album">
                            <i class="fas fa-file-download fs-3"></i> EXPORTAR
                        </a>
                    </div>
                </div>
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
                <tbody id="tabelaAlbumBody">
                <c:forEach items="${albuns}" var="album">
                    <tr>
                        <td><a href="${s:mvcUrl('HC#detalhe').arg(0, album.id).build()}">${album.nome}</a></td>
                        <td class="d-none d-sm-table-cell">${album.banda.nome}</td>
                        <td class="d-none d-md-table-cell">${album.descricao}</td>
                        <td>${album.tipo}</td>
                        <td class="d-none d-sm-table-cell">R$${album.preco}</td>
                        <td class="d-none d-lg-table-cell"><tags:localDate date="${album.lancamento}"/></td>
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

        <div class="tab-pane fade table-responsive" id="camisetas" role="tabpanel" aria-labelledby="camisetas-tab">

            <div class="row w-100 gap-3">
                <div class="col-sm-12 col-md-6 mx-3">
                    <input id="filtroCamisetaInput" onkeyup="filtro('filtroCamisetaInput', 'tabelaCamisetaBody')" class="form-control me-2" type="search" placeholder="Filtrar..." aria-label="Search">
                </div>
                <div class="col">
                    <div class="text-end mb-3 me-3">
                        <a href="${pageContext.request.contextPath}/crud/product/export/camiseta">
                            <i class="fas fa-file-download fs-3"></i> EXPORTAR
                        </a>
                    </div>
                </div>
            </div>

            <table class="table table-hover table-striped">
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
                <tbody id="tabelaCamisetaBody">
                <c:forEach items="${camisetas}" var="camiseta">
                    <tr>
                        <td><a href="${s:mvcUrl('HC#detalhe').arg(0, camiseta.id).build()}">${camiseta.nome}</a></td>
                        <td class="d-none d-sm-table-cell">${camiseta.banda.nome}</td>
                        <td class="d-none d-md-table-cell">${camiseta.descricao}</td>
                        <td>${camiseta.size.toString()}</td>
                        <td class="d-none d-sm-table-cell">R$${camiseta.preco}</td>
                        <td class="d-none d-lg-table-cell"><tags:localDate date="${camiseta.lancamento}"/></td>
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

        <div class="tab-pane fade table-responsive" id="patches" role="tabpanel" aria-labelledby="patches-tab">

            <div class="row w-100 gap-3">
                <div class="col-sm-12 col-md-6 mx-3">
                    <input id="filtroPatchesInput" onkeyup="filtro('filtroPatchesInput', 'tabelaPatchesBody')" class="form-control me-2" type="search" placeholder="Filtrar..." aria-label="Search">
                </div>
                <div class="col">
                    <div class="text-end mb-3 me-3">
                        <a href="${pageContext.request.contextPath}/crud/product/export/patch">
                            <i class="fas fa-file-download fs-3"></i> EXPORTAR
                        </a>
                    </div>
                </div>
            </div>

            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">Título</th>
                    <th scope="col">Banda</th>
                    <th scope="col" class="d-none d-md-table-cell">Descrição</th>
                    <th scope="col" class="d-none d-sm-table-cell">Preço</th>
                    <th scope="col" class="d-none d-lg-table-cell">Lançamento</th>
                    <th scope="col" class="text-center">Editar</th>
                    <th scope="col" class="text-center">Excluir</th>
                </tr>
                </thead>
                <tbody id="tabelaPatchesBody">
                <c:forEach items="${patches}" var="patch">
                    <tr>
                        <td><a href="${s:mvcUrl('HC#detalhe').arg(0, patch.id).build()}">${patch.nome}</a></td>
                        <td>${patch.banda.nome}</td>
                        <td class="d-none d-md-table-cell">${patch.descricao}</td>
                        <td class="d-none d-sm-table-cell">R$${patch.preco}</td>
                        <td class="d-none d-lg-table-cell"><tags:localDate date="${patch.lancamento}"/></td>
                        <td class="text-center">
                            <a class="btn" href="${pageContext.request.contextPath}/crud/product/update/patch/${patch.id}">
                                <i class="fas fa-edit"></i>
                            </a>
                        </td>
                        <td class="text-center">
                            <form:form action="${s:mvcUrl('CPC#deleteProduct').arg(0, patch.id).build()}" method="POST"
                                       id="delete-product-${patch.id}">
                                <input name="id"  type="hidden" alt="Excluir" title="Excluir" />
                                <button class="btn" type="submit" onclick="confirmDeleteProduct(event, ${patch.id},
                                        '<s:message text="${patch.nome}" javaScriptEscape="true"/>')">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="tab-pane fade table-responsive" id="livros" role="tabpanel" aria-labelledby="livros-tab">

            <div class="row w-100 gap-3">
                <div class="col-sm-12 col-md-6 mx-3">
                    <input id="filtroLivrosInput" onkeyup="filtro('filtroLivrosInput', 'tabelaLivrosBody')" class="form-control me-2" type="search" placeholder="Filtrar..." aria-label="Search">
                </div>
                <div class="col">
                    <div class="text-end mb-3 me-3">
                        <a href="${pageContext.request.contextPath}/crud/product/export/livro">
                            <i class="fas fa-file-download fs-3"></i> EXPORTAR
                        </a>
                    </div>
                </div>
            </div>

            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">Título</th>
                    <th scope="col">Banda</th>
                    <th scope="col" class="d-none d-sm-table-cell">Autor</th>
                    <th scope="col" class="d-none d-sm-table-cell">Páginas</th>
                    <th scope="col" class="d-none d-md-table-cell">Descrição</th>
                    <th scope="col" class="d-none d-sm-table-cell">Preço</th>
                    <th scope="col" class="d-none d-lg-table-cell">Lançamento</th>
                    <th scope="col" class="text-center">Editar</th>
                    <th scope="col" class="text-center">Excluir</th>
                </tr>
                </thead>
                <tbody id="tabelaLivrosBody">
                <c:forEach items="${livros}" var="livro">
                    <tr>
                        <td><a href="${s:mvcUrl('HC#detalhe').arg(0, livro.id).build()}">${livro.nome}</a></td>
                        <td>${livro.banda.nome}</td>
                        <td class="d-none d-sm-table-cell">${livro.autor}</td>
                        <td class="d-none d-sm-table-cell">${livro.paginas}</td>
                        <td class="d-none d-md-table-cell">${livro.descricao}</td>
                        <td class="d-none d-sm-table-cell">R$${livro.preco}</td>
                        <td class="d-none d-lg-table-cell"><tags:localDate date="${livro.lancamento}"/></td>
                        <td class="text-center">
                            <a class="btn" href="${pageContext.request.contextPath}/crud/product/update/livro/${livro.id}">
                                <i class="fas fa-edit"></i>
                            </a>
                        </td>
                        <td class="text-center">
                            <form:form action="${s:mvcUrl('CPC#deleteProduct').arg(0, livro.id).build()}" method="POST"
                                       id="delete-product-${livro.id}">
                                <input name="id"  type="hidden" alt="Excluir" title="Excluir" />
                                <button class="btn" type="submit" onclick="confirmDeleteProduct(event, ${livro.id},
                                        '<s:message text="${livro.nome}" javaScriptEscape="true"/>')">
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