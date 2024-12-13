package com.pgv.naftra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgv.naftra.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Métodos personalizados si es necesario
}

