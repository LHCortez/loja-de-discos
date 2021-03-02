<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<tags:pageTemplate>


    <section id="login" class="bg-light d-flex align-items-center justify-content-center p-3 mt-3 rounded">
        <div id="login-form-container">
            <h5 class="text-center my-4">Login</h5>
            <form id="login-form" action="${pageContext.request.contextPath}/user/login" method="post">
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
            </form>
            <div class="text-center text-muted delimiter mb-3">ou se logue usando uma rede social</div>

            <a href="${pageContext.request.contextPath}/oauth2/authorization/github">GITHUB</a>

            <a href="${pageContext.request.contextPath}/oauth2/authorization/google">GOOGLE</a>

            <div class="d-flex justify-content-center social-buttons gap-3 my-4">
                <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip"
                        data-placement="top" title="Twitter">
                    <i class="fab fa-twitter"></i>
                </button>
                <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip"
                        data-placement="top" title="Facebook">
                    <i class="fab fa-facebook"></i>
                </button>
                <button type="button" class="btn btn-secondary btn-round" data-toggle="tooltip"
                        data-placement="top" title="Linkedin">
                    <i class="fab fa-linkedin"></i>
                </button>
            </div>
            <div class="text-center mt-4 mb-6"> Ainda não é cadastrado?
                <a href="${s:mvcUrl('UC#createUserForm').build()}" class="text-info">Clique aqui</a>.
            </div>
        </div>
    </section>


</tags:pageTemplate>
