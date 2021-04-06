package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.DadosPagamento;
import com.luiz.lhcdiscos.model.entity.ItemPedido;
import com.luiz.lhcdiscos.model.entity.Pedido;
import com.luiz.lhcdiscos.model.entity.Usuario;
import com.luiz.lhcdiscos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public void salva(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public List<Pedido> buscaPorEmailDoCliente(String email) {
        return pedidoRepository.findPedidoByCliente_EmailIgnoreCase(email);
    }

    public Pedido finalizaPedido(List<ItemPedido> itens, Usuario usuario, DadosPagamento pagamento, LocalDateTime dataPedido) {

        Pedido pedido = new Pedido();
        itens.forEach(x -> x.setPedido(pedido));
        pedido.setCliente(usuario);
        pedido.setPagamento(pagamento);
        pedido.setData(dataPedido);
        pedido.addItens(itens);

        salva(pedido);

        return pedido;
    }

}
