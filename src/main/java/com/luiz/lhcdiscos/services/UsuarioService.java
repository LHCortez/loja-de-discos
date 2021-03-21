package com.luiz.lhcdiscos.services;

import com.luiz.lhcdiscos.models.Usuario;
import com.luiz.lhcdiscos.security.local.UserDetailsImpl;
import com.luiz.lhcdiscos.dto.NovoUsuarioLocalDto;
import com.luiz.lhcdiscos.models.enums.AuthenticationProvider;
import com.luiz.lhcdiscos.models.enums.Role;
import com.luiz.lhcdiscos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        usuario.setAuthenticationProvider(AuthenticationProvider.LOCAL);
        save(usuario);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findUsuarioByEmailIgnoreCase(String email){
        return usuarioRepository.findUsuarioByEmailIgnoreCase(email);
    }

    public void createNewUserAfterOAuthLoginSuccess(String email, String name, AuthenticationProvider provider) {
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNome(name);
        usuario.setEnabled(true);
        usuario.addRoles(Role.ROLE_USER);
        usuario.setAuthenticationProvider(provider);
        save(usuario);
    }

    public void updateCustomerAfterOAuthLoginSucces(Usuario usuario, String name, AuthenticationProvider provider) {
        usuario.setNome(name);
        usuario.setAuthenticationProvider(provider);
        save(usuario);
    }
}
