package com.luiz.lhcdiscos.oauth;

import com.luiz.lhcdiscos.models.enums.AuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oAuth2User;

    private AuthenticationProvider provider;

    public CustomOAuth2User(OAuth2User oAuth2User, String clientName) {
        this.oAuth2User = oAuth2User;
        this.provider = AuthenticationProvider.valueOf(clientName.toUpperCase());
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
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {
//        TODO verificar o OAuth do github, não é esse o atributo
        System.out.println(oAuth2User.getAttributes().toString());
        return oAuth2User.getAttribute("email");
    }

    public AuthenticationProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthenticationProvider provider) {
        this.provider = provider;
    }
}
