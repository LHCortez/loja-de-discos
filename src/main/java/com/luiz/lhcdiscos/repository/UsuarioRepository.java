package com.luiz.lhcdiscos.repository;

import com.luiz.lhcdiscos.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findUsuarioByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

}
