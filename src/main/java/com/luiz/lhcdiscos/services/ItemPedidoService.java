package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.ItemPedido;
import com.luiz.lhcdiscos.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> buscaTodos() {
        return itemPedidoRepository.findAll();
    }

}
