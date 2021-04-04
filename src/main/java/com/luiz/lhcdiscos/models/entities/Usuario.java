package com.luiz.lhcdiscos.models.entities;

import com.luiz.lhcdiscos.models.enums.ProvedorAutenticacao;
import com.luiz.lhcdiscos.models.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_provider")
    private ProvedorAutenticacao provedorAutenticacao;
    private boolean enabled = true;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = EnumSet.noneOf(Role.class);

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

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

    public void addPedido(Pedido... pedido) {
        this.pedidos.addAll(Arrays.asList(pedido));
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public ProvedorAutenticacao getProvedorAutenticacao() {
        return provedorAutenticacao;
    }

    public void setProvedorAutenticacao(ProvedorAutenticacao provedorAutenticacao) {
        this.provedorAutenticacao = provedorAutenticacao;
    }

    @PrePersist
    @PreUpdate
    private void prepare(){
        this.email = this.email == null ? null : email.toLowerCase();
    }
}
