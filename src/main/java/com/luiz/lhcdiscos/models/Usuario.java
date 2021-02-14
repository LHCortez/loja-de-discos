package com.luiz.lhcdiscos.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Preencha seu nome")
    @NotEmpty(message = "Preencha seu nome")
    private String nome;
    @Email(message = "Preencha um endereço de e-mail válido")
    @NotEmpty(message = "Preencha seu nome")
    @NotBlank(message = "Preencha seu e-mail")
    private String email;
    @Size(min = 6, max = 15, message = "Sua senha deve ter entre 6 e 15 caracteres")
    @NotBlank(message = "Preencha sua senha")
    @NotEmpty(message = "Preencha seu nome")
    private String senha;
    private boolean enabled = true;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public void addRoles(Role... role) {
        roles.addAll(Arrays.asList(role));
    }
}
