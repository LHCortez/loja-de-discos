package com.luiz.lhcdiscos.security.oauth;

import com.luiz.lhcdiscos.model.entity.Usuario;
import com.luiz.lhcdiscos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        Usuario usuario = usuarioService.buscaPorEmail(email);

        if (usuario == null) {
            usuarioService.criaNovoUsuarioAposAutenticacaoOAuth(email, oAuth2User.getUsuarioName(), oAuth2User.getProvedorAutenticacao());
        } else {
            usuarioService.atualizaUsuarioAposAutenticacaoOAuth(usuario, oAuth2User.getUsuarioName(), oAuth2User.getProvedorAutenticacao());
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
