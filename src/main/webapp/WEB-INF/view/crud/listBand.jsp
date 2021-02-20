<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:crudTemplate>

    <ul class="nav nav-tabs crud-tabs navbar-dark" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button disabled class="nav-link crud-tabs-link active" id="bandas-tab" data-bs-toggle="tab"
                    data-bs-target="#bandas" type="button" role="tab" aria-controls="bandas"
                    aria-selected="true">Bandas</button>
        </li>
    </ul>
    <div class="tab-content crud-tab-content py-4 px-sm-4" id="myTabContent">

        <div class="tab-pane fade show active table-responsive" id="bandas" role="tabpanel" aria-labelledby="home-tab">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Gênero</th>
                    <th scope="col" class="text-center">Editar</th>
                    <th scope="col" class="text-center">Excluir</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bandas}" var="banda">
                    <tr>
                        <td>${banda.nome}</td>
                        <td>${banda.genero.descricao}</td>
                        <td class="text-center"><i class="fas fa-edit"></i></td>
                        <td class="text-center">
                            <form:form action="${s:mvcUrl('CC#deleteBand').arg(0, banda.id).build()}" method="POST" id="delete-band-${banda.id}">
                                <input name="id"  type="hidden" alt="Excluir" title="Excluir" />
                                <button class="btn" type="button" onclick="confirmDelete(event, ${banda.id}, '${banda.nome}')">
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