package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.entities.DadosPagamento;
import com.luiz.lhcdiscos.models.entities.ItemPedido;
import com.luiz.lhcdiscos.models.entities.Pedido;
import com.luiz.lhcdiscos.models.entities.Usuario;
import com.luiz.lhcdiscos.repositories.PedidoRepository;
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
