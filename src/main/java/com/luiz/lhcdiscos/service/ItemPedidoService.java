package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.ItemPedido;
import com.luiz.lhcdiscos.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> buscaTodos() {
        return itemPedidoRepository.findAll();
    }

    public Map<String, Long> quantidadeItensCompradosPorTipo() {
        List<ItemPedido> itemPedidos = buscaTodos();
        Map<String, Long> itemCount = new HashMap<>();
        itemPedidos.forEach(x -> {
            String tipo = x.getTipo();
            if (itemCount.containsKey(tipo)) {
                itemCount.put(tipo, (itemCount.get(tipo) + x.getQuantidade()));
            } else {
                itemCount.put(tipo, Long.valueOf(x.getQuantidade()));
            }
        });
        return itemCount;
    }

    public Map<String, Long> quantidadeItensCompradosPorGeneroDaBanda() {
        List<ItemPedido> itemPedidos = buscaTodos();
        Map<String, Long> generos = new HashMap<>();
        itemPedidos.forEach(x -> {
            String genero = x.getGeneroDaBanda().toString();
            if (generos.containsKey(genero)) {
                generos.put(genero, (generos.get(genero) + x.getQuantidade()));
            } else {
                generos.put(genero, Long.valueOf(x.getQuantidade()));
            }
        });
        return generos;
    }

}
