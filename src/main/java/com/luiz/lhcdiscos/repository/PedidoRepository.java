package com.luiz.lhcdiscos.repository;

import com.luiz.lhcdiscos.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findPedidoByCliente_EmailIgnoreCase(String email);


}
