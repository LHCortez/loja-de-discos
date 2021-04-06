package com.luiz.lhcdiscos.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luiz.lhcdiscos.model.enums.Genero;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Produto produto;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer quantidade;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_unitario", nullable = false)
    private BigDecimal valorUnitario;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Genero generoDaBanda;

//    OBS: Alguns atributos do Produto e Banda foram salvos também nessa entidade pois, ao se deletar o produto na
//    tela de CRUD essas informações eram perdidas e causavam exceções na aplicação. Por exemplo,
//    não era mais possível referenciar o nome do Produto a partir do ItemPedido, causando NullPointerException na tela
//    de visualização do pedido pelo cliente. O Gênero da banda foi salvo aqui devido a aparecer nos gráficos de estatística,
//    pois ocorria erro caso o produto ou banda fossem deletados no CRUD.

    public ItemPedido(){
    }

    public ItemPedido (Produto produto, Integer quantidade) {
        setProduto(produto, quantidade);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto, Integer quantidade) {
        this.nome = produto.getNome();
        this.valorUnitario = produto.getPreco();
        this.tipo = produto.getTipo();
        this.produto = produto;
        this.quantidade = quantidade;
        this.generoDaBanda = produto.getBanda().getGenero();

    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Genero getGeneroDaBanda() {
        return generoDaBanda;
    }

    public void setGeneroDaBanda(Genero generoDaBanda) {
        this.generoDaBanda = generoDaBanda;
    }
}
