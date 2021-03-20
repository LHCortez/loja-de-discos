package com.luiz.lhcdiscos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public abstract class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @NotBlank(message = "Preencha o nome do Produto")
    @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
    private String nome;

    @Column(columnDefinition = "TEXT")
    @JsonIgnore
    private String descricao;

    @NotNull(message = "Preencha o preço")
    @DecimalMin(value = "1", message = "Valor mínimo de R$ 1")
    private BigDecimal preco;

//    @NotBlank(message = "Preencha a capa")
    private String capa;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate lancamento;

    @ManyToOne
    @JoinColumn(name = "banda_id")
    @NotNull(message = "Selecione a banda")
    private Banda banda;

    @PrePersist
    @PreUpdate
    private void prepare(){
        this.descricao = this.descricao.replaceAll("\n","<br />");
    }

    public Produto(){
    }

    public Produto(String nome, String descricao, BigDecimal preco, String capa, Banda banda, LocalDate lancamento) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.capa = capa;
        this.banda = banda;
        this.lancamento = lancamento;
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
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    public void setLancamento(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public abstract String getTipo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
