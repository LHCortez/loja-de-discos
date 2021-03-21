<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:pageTemplate paginatitulo="Login -">


    <section id="login" class="bg-light d-flex align-items-center justify-content-center mt-3 rounded">

        <div id="login-form-container mx-1">
            <h4 class="text-center my-5">Faça o login!</h4>

            <c:if test="${param.error != null}">
                <p class="text-danger mb-0">Falha ao fazer Login.</p>
                    <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                        <p class="text-danger"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" /></p>
                    </c:if>
            </c:if>

            <c:if test="${novoUsuario == true}">
                <p class="text-success mb-0">Novo usuário cadastrado!</p>
            </c:if>

            <form:form id="login-form" action="${pageContext.request.contextPath}/user/login" method="post">
                <div class="form-floating">
                    <input type="email" name="username" class="form-control my-4" aria-describedby="emailHelp"
                           aria-label="E-mail" id="floatingInput" placeholder="E-mail">
                    <label for="floatingInput">E-mail</label>
                </div>
                <div class="form-floating">
                    <input type="password" name="password" class="form-control my-4" aria-label="Senha"
                           id="floatingPassword" placeholder="Senha">
                    <label for="floatingInput">Senha</label>
                </div>
                <button type="submit" class="btn w-100 mt-0 mb-4 botao-destaque">Entrar</button>
            </form:form>
            <div class="text-center text-muted delimiter mb-3">ou...</div>

            <div class="text-center">
                <a href="${pageContext.request.contextPath}/oauth2/authorization/google"
                      class="btn btn-primary py-0 ps-0 google-login border-2 ">
                    <img class="google-logo rounded" src="${pageContext.request.contextPath}/img/google-logo.png" alt="Google"/>
                    <span class="align-middle px-3">Entre com o Google</span>
                </a>
            </div>


            <div class="text-center mt-5 mb-6 fs-5 "> Ainda não é cadastrado?
                <a href="${s:mvcUrl('UC#createUserForm').build()}" class="texto-cor-especial">Clique aqui</a>.
            </div>
        </div>
    </section>


</tags:pageTemplate>
