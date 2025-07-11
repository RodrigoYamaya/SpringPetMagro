package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.model.entities.Usuario;
import com.RodriSolution.SpringPetMagro.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByusername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario Nao encontrado"));
        return User.builder()
                .username(usuario.getUsername().trim())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();
    }
}
