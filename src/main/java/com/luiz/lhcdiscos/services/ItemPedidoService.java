package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.ItemPedido;
import com.luiz.lhcdiscos.models.Pedido;
import com.luiz.lhcdiscos.repositories.ItemPedidoRepository;
import com.luiz.lhcdiscos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> findAll() {
        return itemPedidoRepository.findAll();
    }

}
