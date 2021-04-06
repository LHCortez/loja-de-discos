package com.luiz.lhcdiscos.service;

import com.luiz.lhcdiscos.model.entity.Usuario;
import com.luiz.lhcdiscos.model.dto.NovoUsuarioLocalDto;
import com.luiz.lhcdiscos.model.enums.ProvedorAutenticacao;
import com.luiz.lhcdiscos.model.enums.Role;
import com.luiz.lhcdiscos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void saveNovoUsuarioLocal(NovoUsuarioLocalDto novoUsuarioLocalDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(novoUsuarioLocalDTO.getNome());
        usuario.setEmail((novoUsuarioLocalDTO.getEmail()));
        usuario.addRoles(novoUsuarioLocalDTO.getRole());
        usuario.setSenha(passwordEncoder.encode(novoUsuarioLocalDTO.getSenha()));
        usuario.setProvedorAutenticacao(ProvedorAutenticacao.LOCAL);
        salva(usuario);
    }

    public void salva(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario buscaPorEmail(String email){
        return usuarioRepository.findUsuarioByEmailIgnoreCase(email);
    }

    public void criaNovoUsuarioAposAutenticacaoOAuth(String email, String name, ProvedorAutenticacao provider) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNome(name);
        usuario.setEnabled(true);
        usuario.addRoles(Role.ROLE_USER);
        usuario.setProvedorAutenticacao(provider);
        salva(usuario);
    }

    public void atualizaUsuarioAposAutenticacaoOAuth(Usuario usuario, String name, ProvedorAutenticacao provider) {
        usuario.setNome(name);
        usuario.setProvedorAutenticacao(provider);
        salva(usuario);
    }
}
