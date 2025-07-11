package com.RodriSolution.SpringPetMagro.services;

import com.RodriSolution.SpringPetMagro.model.dtos.UsuarioDto;
import com.RodriSolution.SpringPetMagro.model.entities.Usuario;
import com.RodriSolution.SpringPetMagro.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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


}
