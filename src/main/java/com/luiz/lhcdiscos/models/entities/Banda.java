package com.luiz.lhcdiscos.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.validation.UniqueBand;
import com.luiz.lhcdiscos.validation.ValueOfEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@UniqueBand
public class Banda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Preencha o nome da banda")
    @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
    private String nome;

    @OneToMany(mappedBy = "banda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @Column(nullable = false)
    private Set<Produto> produtos = new HashSet<>();

    @ValueOfEnum(enumClass = Genero.class, message = "Escolha o gênero musical")
    @Column(nullable = false)
    private Genero genero;

    public Banda(){
    }

    public Banda(String nome, Genero genero){
        this.nome = nome;
        this.genero = genero;
    }

    public Banda(String nome) {
        this.nome = nome;
    }

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

    @JsonIgnore
    public Set<Produto> getProdutos() {
        return Collections.unmodifiableSet(produtos);
    }

    public void addProdutos(Produto... produtos) {
        this.produtos.addAll(Arrays.asList(produtos));
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banda banda = (Banda) o;
        return Objects.equals(id, banda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
