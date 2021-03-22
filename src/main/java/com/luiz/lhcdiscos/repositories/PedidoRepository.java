package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findPedidoByCliente_EmailIgnoreCase(String email);


}
