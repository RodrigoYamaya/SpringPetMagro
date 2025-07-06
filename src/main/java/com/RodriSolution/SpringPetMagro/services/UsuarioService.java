package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.dtos.UsuarioDto;
import com.RodriSolution.SpringPetMagro.enums.RoleEnum;
import com.RodriSolution.SpringPetMagro.model.Usuario;
import com.RodriSolution.SpringPetMagro.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario registrarUsuario(UsuarioDto dto) {
        String senhaCriptografada = passwordEncoder.encode(dto.password());
        Usuario usuario = new Usuario(dto.username(),senhaCriptografada, dto.role());
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscaPorUsername(String username) {
        return usuarioRepository.findByusername(username);
    }
}
