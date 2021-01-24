package com.luiz.lhcdiscos.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Disco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private Double preco;
    private String capa;
    private LocalDate lancamento;
    @ManyToOne
    @JoinColumn(name = "banda_id")
    private Banda banda;

}
