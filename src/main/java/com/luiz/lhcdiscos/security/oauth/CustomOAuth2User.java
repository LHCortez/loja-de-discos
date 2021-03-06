package com.luiz.lhcdiscos.security.oauth;

import com.luiz.lhcdiscos.model.enums.ProvedorAutenticacao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;

    private ProvedorAutenticacao provedorAutenticacao;

    public CustomOAuth2User(OAuth2User oAuth2User, String clientName) {
        this.oAuth2User = oAuth2User;
        this.provedorAutenticacao = ProvedorAutenticacao.valueOf(clientName.toUpperCase());
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return getEmail();
    }

    public String getUsuarioName() {
        return this.oAuth2User.getAttribute("name");
    }

    public String getEmail() {
        String email = oAuth2User.getAttribute("email") ;
        if (email == null) {
            throw new NullPointerException("CustomOAuth2User: atributo email não encontrado");
        }
        return email;
    }

    public ProvedorAutenticacao getProvedorAutenticacao() {
        return provedorAutenticacao;
    }

    public void setProvedorAutenticacao(ProvedorAutenticacao provedorAutenticacao) {
        this.provedorAutenticacao = provedorAutenticacao;
    }
}
