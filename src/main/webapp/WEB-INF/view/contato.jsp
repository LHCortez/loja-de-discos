<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tags:pageTemplate paginatitulo="Cadastro de novo usuário -">

    <section id="cria-conta" class="bg-light p-3 mt-3 rounded">

        <h4 class="titulo mt-4 mb-4">Entre em contato conosco</h4>

        <c:if test="${emailEnviado}">
            <p class="text-success">Mensagem enviada.</p>
        </c:if>

        <form:form action="${pageContext.request.contextPath}/contato/envia" method="POST" id="contato-form" class="px-md-5">
            <fieldset class="row my-4">
                <label for="nome" class="col-sm-4 col-form-label">Nome</label>
                <div class="col-sm-8">
                    <input name="nome" size="30" type="text" class="form-control" id="nome" placeholder="Nome" required/>
                </div>
            </fieldset>

            <fieldset class="row my-4">
                <label for="email" class="col-sm-4 col-form-label">E-mail</label>
                <div class="col-sm-8">
                    <input name ="email" type="email" class="form-control" id="email" placeholder="E-mail" required/>
                </div>
            </fieldset>

            <fieldset class="row my-4">
                <label for="mensagem" class="col-sm-4 col-form-label">Mensagem</label>
                <div class="col-sm-8">
                    <textarea name="mensagem" type="text" class="form-control" id="mensagem" placeholder="Mensagem"
                              rows="8" required></textarea>
                </div>
            </fieldset>

            <button type="submit" class="btn botao-destaque p-2 mt-4 mb-5">Enviar mensagem</button>
        </form:form>

    </section>

</tags:pageTemplate>