package com.luiz.lhcdiscos.models.entities;

import com.luiz.lhcdiscos.validation.UniqueLivro;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"nome", "autor"}))
@UniqueLivro
public class Livro extends Produto {

    @Min(10)
    private Integer paginas;

    @NotBlank(message = "Preencha o nome do Autor")
    @Size(max = 30, message = "O nome deve conter no máximo 30 caracteres")
    private String autor;

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
