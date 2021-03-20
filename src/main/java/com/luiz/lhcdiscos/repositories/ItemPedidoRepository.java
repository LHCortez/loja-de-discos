package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.ItemPedido;
import com.luiz.lhcdiscos.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {


}
