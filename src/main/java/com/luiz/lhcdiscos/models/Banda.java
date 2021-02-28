package com.luiz.lhcdiscos.models;

import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.validation.UniqueBand;
import com.luiz.lhcdiscos.validation.ValueOfEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@UniqueBand
public class Banda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotBlank(message = "Preencha o nome da banda")
    @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
    private String nome;

    @OneToMany(mappedBy = "banda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Produto> produtos = new HashSet<>();

    @ValueOfEnum(enumClass = Genero.class, message = "Escolha o gênero musical")
    private Genero genero;

    public Banda(){
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

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> albums) {
        this.produtos = albums;
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
        return Objects.equals(nome, banda.nome) && genero == banda.genero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, genero);
    }
}
