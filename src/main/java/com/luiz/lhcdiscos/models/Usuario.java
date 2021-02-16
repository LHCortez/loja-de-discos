package com.luiz.lhcdiscos.models;

import com.luiz.lhcdiscos.models.enums.Role;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    private boolean enabled = true;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = EnumSet.noneOf(Role.class);

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

    @PrePersist
    @PreUpdate
    private void prepare(){
        this.email = this.email == null ? null : email.toLowerCase();
    }
}