package com.luiz.lhcdiscos.model.dto;

import com.luiz.lhcdiscos.model.enums.Role;
import com.luiz.lhcdiscos.model.validation.ConfirmPasswords;
import com.luiz.lhcdiscos.model.validation.UniqueUsername;
import com.luiz.lhcdiscos.model.validation.ValidPassword;

import javax.validation.constraints.*;

@ConfirmPasswords
public class NovoUsuarioLocalDto {

    @NotBlank(message = "Preencha seu nome completo")
    @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
    private String nome;

    @NotBlank(message = "Preencha seu e-mail")
    @Email(message = "Insira um endereço de e-mail válido")
    @UniqueUsername
    private String email;

    @ValidPassword
    private String senha;

    private String confirmaSenha;

    private final Role role = Role.ROLE_USER;

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

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public Role getRole() {
        return role;
    }

}
