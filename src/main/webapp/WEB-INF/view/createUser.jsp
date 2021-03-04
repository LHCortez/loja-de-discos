<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tags:pageTemplate>

    <section id="cria-conta" class="bg-light p-3 mt-3 rounded">

        <h4 class="titulo mt-4 mb-5">Crie sua conta</h4>

        <form:form  modelAttribute="usuario" action="${pageContext.request.contextPath}/user/create" method="POST"
                   id="cria-conta-form" class="px-md-5">
            <div class="row my-4">
                <label for="nome" class="col-sm-4 col-form-label">Nome completo</label>
                <div class="col-sm-8">
                    <form:input path="nome" size="30" type="text" class="form-control" id="nome" placeholder="Nome completo" />
                    <form:errors path="nome" cssClass="error"/>
                </div>
            </div>
            <div class="row my-4">
                <label for="email" class="col-sm-4 col-form-label">E-mail</label>
                <div class="col-sm-8">
                    <form:input type="email" class="form-control" id="email" placeholder="E-mail" path="email"/>
                    <form:errors path="email" cssClass="error"/>
                </div>
            </div>
            <div class="row my-4">
                <label for="senha" class="col-sm-4 col-form-label">Senha</label>
                <div class="col-sm-8">
                    <form:password path="senha" class="form-control" id="senha" placeholder="Senha"/>
                    <form:errors path="senha" cssClass="error"/>
                </div>
            </div>
<%--            <div class="row mb-3">--%>
<%--                <label for="senha2" class="col-sm-4 col-form-label">Repita a senha</label>--%>
<%--                <div class="col-sm-8">--%>
<%--                    <input type="password" class="form-control" id="senha2" placeholder="Repita a senha"--%>
<%--                           name="senhConfirmation">--%>
<%--                </div>--%>
<%--            </div>--%>
            <fieldset class="row my-4">
                <legend class="col-form-label col-sm-4 pt-0">Tipo de conta</legend>
                <div class="col-sm-8">
                    <div class="form-check">
                        <form:radiobutton path="roleNome" class="form-check-input"  name="gridRadios" id="gridRadios1"
                                          value="ROLE_USER" />
                        <label class="form-check-label" for="gridRadios1">
                            Cliente
                        </label>
                    </div>
                    <div class="form-check">
                        <form:radiobutton path="roleNome" class="form-check-input" name="gridRadios" id="gridRadios2"
                                          value="ROLE_ADMIN"/>
                        <label class="form-check-label" for="gridRadios2">
                            Administrador
                        </label>
                    </div>
                </div>
            </fieldset>
            <button type="submit" class="btn botao-destaque p-2 mt-4 mb-5">Cadastrar</button>
        </form:form>

    </section>

</tags:pageTemplate>