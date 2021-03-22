package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.Pedido;
import com.luiz.lhcdiscos.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public void save(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }


    public void saveAll(List<Pedido> pedidos) {
        pedidoRepository.saveAll(pedidos);
    }

    public List<Pedido> searchPedidosByClienteEmail(String email) {
        return pedidoRepository.findPedidoByCliente_EmailIgnoreCase(email);
    }


}
