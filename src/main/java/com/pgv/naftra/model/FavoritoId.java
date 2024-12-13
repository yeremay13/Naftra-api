package com.pgv.naftra.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavoritoId implements Serializable {
    private Long usuario_id;
    private Long artista_id;

    // Constructor vacío
    public FavoritoId() {}

    // Constructor con parámetros
    public FavoritoId(Long usuario_id, Long artista_id) {
        this.usuario_id = usuario_id;
        this.artista_id = artista_id;
    }

    // Getters y setters
    public Long getusuario_id() {
        return usuario_id;
    }

    public void setusuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Long getartista_id() {
        return artista_id;
    }

    public void setartista_id(Long artista_id) {
        this.artista_id = artista_id;
    }

    // Implementación de equals y hashCode (obligatorio para claves primarias compuestas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoritoId that = (FavoritoId) o;
        return Objects.equals(usuario_id, that.usuario_id) &&
                Objects.equals(artista_id, that.artista_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario_id, artista_id);
    }
}