<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tags:pageTemplate paginatitulo="Cadastro de novo usuário -">

    <section id="cria-conta" class="bg-light p-3 mt-3 rounded">

        <h4 class="titulo mt-4 mb-5">Crie sua conta</h4>

        <form:form  modelAttribute="usuario" action="${pageContext.request.contextPath}/user/create" method="POST"
                   id="cria-conta-form" class="px-md-5">
            <div class="row my-4">
                <label for="nome" class="col-sm-4 col-form-label">Nome completo</label>
                <div class="col-sm-8">
                    <form:input path="nome" size="30" type="text" class="form-control" id="nome" placeholder="Nome completo" />
                    <form:errors path="nome" cssClass="text-danger"/>
                </div>
            </div>
            <div class="row my-4">
                <label for="email" class="col-sm-4 col-form-label">E-mail</label>
                <div class="col-sm-8">
                    <form:input type="email" class="form-control" id="email" placeholder="E-mail" path="email"/>
                    <form:errors path="email" cssClass="text-danger"/>
                </div>
            </div>
            <div class="row my-4">
                <label for="senha" class="col-sm-4 col-form-label">Senha</label>
                <div class="col-sm-8">
                    <form:password path="senha" class="form-control" id="senha" placeholder="Senha"/>
                    <form:errors path="senha" cssClass="text-danger"/>
                </div>
            </div>
            <div class="row my-4">
                <label for="confirmaSenha" class="col-sm-4 col-form-label">Repita a senha</label>
                <div class="col-sm-8">
                    <form:password path="confirmaSenha" class="form-control" id="confirmaSenha" placeholder="Repita a senha"/>
                    <form:errors cssClass="text-danger"/>
                </div>
            </div>
            <button type="submit" class="btn botao-destaque p-2 mt-4 mb-5">Cadastrar</button>
        </form:form>

    </section>

</tags:pageTemplate>