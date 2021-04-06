package com.luiz.lhcdiscos.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER("U"),
    ROLE_ADMIN("A");

    private String code;

    private Role(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public String toString() {
        return name();
    }
}
