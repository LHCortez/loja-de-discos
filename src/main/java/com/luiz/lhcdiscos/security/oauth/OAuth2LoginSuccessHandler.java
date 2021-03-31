package com.luiz.lhcdiscos.security.oauth;

import com.luiz.lhcdiscos.models.entities.Usuario;
import com.luiz.lhcdiscos.services.UsuarioService;
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
        Usuario usuario = usuarioService.findUsuarioByEmailIgnoreCase(email);

        if (usuario == null) {
            usuarioService.createNewUserAfterOAuthLoginSuccess(email, oAuth2User.getName(), oAuth2User.getProvider());
        } else {
            usuarioService.updateCustomerAfterOAuthLoginSucces(usuario, oAuth2User.getName(), oAuth2User.getProvider());
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
