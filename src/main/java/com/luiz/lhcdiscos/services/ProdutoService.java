package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Produto;
import com.luiz.lhcdiscos.models.enums.AlbumFormato;
import com.luiz.lhcdiscos.models.enums.Genero;
import com.luiz.lhcdiscos.repositories.AlbumRepository;
import com.luiz.lhcdiscos.repositories.CamisetaRepository;
import com.luiz.lhcdiscos.repositories.ProdutoRepository;
import com.luiz.lhcdiscos.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luiz.lhcdiscos.models.Camiseta;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository<Produto> produtoRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private CamisetaRepository camisetaRepository;

    public Produto searchById(Integer id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        return optional.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public List<Produto> searchAll() {
        return produtoRepository.findAll();
    }

    public List<Produto> buscarPorGenero(Genero genero) {
        return produtoRepository.findProdutoByGenero(genero);
    }

    public List<Album> buscarLancamentosMaisRecente(){
        return albumRepository.findTop20ByOrderByLancamentoDesc();
    }

    public List<? extends Produto> buscarPorCategoria(String categoria) {
        if (AlbumFormato.getFormatos().contains(categoria)){
            return buscarPorFormato(AlbumFormato.valueOf(categoria.toUpperCase()));
        }
        return buscarPorSubclasse(categoria);
    }

    private List<Produto> buscarPorSubclasse(String nomeSubclasse) {
        String nome = "com.luiz.lhcdiscos.models.".concat(nomeSubclasse);
        System.out.println(nome);
        try {
            Class<? extends Produto> clazz =
                    Class.forName(nome).asSubclass(Produto.class);
            return produtoRepository.findProdutoBySubclass(clazz);
        } catch (ClassNotFoundException e) {
            throw new ObjectNotFoundException("Não foi possível encontrar o objeto com os parâmetros fornecidos");
        }
    }

    private List<Album> buscarPorFormato(AlbumFormato formato) {
        return albumRepository.findAlbumsByFormato(formato);
    }

}
