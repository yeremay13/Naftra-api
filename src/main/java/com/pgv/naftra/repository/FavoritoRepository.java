package com.pgv.naftra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pgv.naftra.model.Favorito;
import com.pgv.naftra.model.FavoritoId;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
}
