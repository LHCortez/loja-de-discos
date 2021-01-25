package com.luiz.lhcdiscos.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Banda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @OneToMany(mappedBy = "banda")
    private Set<Album> albums = new HashSet<>();
    @OneToMany(mappedBy = "banda")
    private Set<Camiseta> camisetas = new HashSet<>();

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

    public Set<Album> getDiscos() {
        return albums;
    }

    public void setDiscos(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<Camiseta> gettShirts() {
        return camisetas;
    }

    public void settShirts(Set<Camiseta> camisetas) {
        this.camisetas = camisetas;
    }
}
