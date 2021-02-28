package com.luiz.lhcdiscos.dto;

import com.luiz.lhcdiscos.models.enums.Role;
import com.luiz.lhcdiscos.validation.UniqueUsername;
import com.luiz.lhcdiscos.validation.ValidPassword;

import javax.validation.constraints.*;

public class UsuarioDto {

    @NotBlank(message = "Preencha seu nome completo")
    @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "Preencha seu e-mail")
    @Email(message = "Insira um endereço de e-mail válido")
    @UniqueUsername
    private String email;

    @ValidPassword
    private String senha;

    private Role role = Role.ROLE_USER;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRoleNome() {
        return role.name();
    }

    public void setRoleNome(String roleString) {
        setRole(roleString);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String roleString) {
        this.role = Role.valueOf(roleString);
    }

}
