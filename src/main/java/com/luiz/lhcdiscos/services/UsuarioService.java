package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Usuario;
import com.luiz.lhcdiscos.models.UserDetailsImpl;
import com.luiz.lhcdiscos.dto.UsuarioDto;
import com.luiz.lhcdiscos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByEmailIgnoreCase(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new UserDetailsImpl(usuario);
    }

    public void save(UsuarioDto usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail((usuarioDTO.getEmail()));
        usuario.setSenha((usuarioDTO.getSenha()));
        usuario.addRoles(usuarioDTO.getRole());
        save(usuario);
    }

    public void save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

}
