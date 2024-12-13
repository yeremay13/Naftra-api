package com.pgv.naftra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgv.naftra.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    // Métodos personalizados si es necesario
}

