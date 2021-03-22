package com.luiz.lhcdiscos.repositories;

import com.luiz.lhcdiscos.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findUsuarioByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);

}
