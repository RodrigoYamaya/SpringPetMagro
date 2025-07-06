package com.RodriSolution.SpringPetMagro.controllers;

import com.RodriSolution.SpringPetMagro.dtos.UsuarioDto;
import com.RodriSolution.SpringPetMagro.dtos.loginDtoRequest;
import com.RodriSolution.SpringPetMagro.model.Usuario;
import com.RodriSolution.SpringPetMagro.security.JwtUtil;
import com.RodriSolution.SpringPetMagro.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;


    public AuthController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioDto dto) {
        Usuario usuario = usuarioService.registrarUsuario(dto);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody loginDtoRequest request) {
        Optional<Usuario> usuario = usuarioService.buscaPorUsername(request.username());
        if (usuario.isPresent() && passwordEncoder.matches((request.password()), usuario.get().getPassword())) {
            String token = JwtUtil.generateToken(usuario.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));

        }
        return ResponseEntity.status(401).body("Credencias inválidas");


    }
}
