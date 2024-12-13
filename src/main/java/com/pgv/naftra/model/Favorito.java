package com.pgv.naftra.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @EmbeddedId
    private FavoritoId id; // Clave primaria compuesta

    @ManyToOne
    @MapsId("usuario_id") // Mapea el atributo usuario_id de FavoritoId
    @JoinColumn(name = "usuario_id") // Columna de clave foránea
    private Usuario usuario;

    @ManyToOne
    @MapsId("artista_id") // Mapea el atributo artista_id de FavoritoId
    @JoinColumn(name = "artista_id") // Columna de clave foránea
    private Artista artista;

    private boolean favorito; // Atributo adicional

    // Constructor vacío
    public Favorito() {}

    // Getters y setters
    public FavoritoId getId() {
        return id;
    }

    public void setId(FavoritoId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}