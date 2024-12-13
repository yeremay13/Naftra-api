package com.pgv.naftra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgv.naftra.ResourceNotFoundException;
import com.pgv.naftra.model.Favorito;
import com.pgv.naftra.model.FavoritoId;
import com.pgv.naftra.model.Usuario;
import com.pgv.naftra.model.Artista;
import com.pgv.naftra.repository.FavoritoRepository;
import com.pgv.naftra.repository.UsuarioRepository;
import com.pgv.naftra.repository.ArtistaRepository;

import java.util.List;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/api/favorito")
public class FavoritoController {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    // Obtener todos los favoritos
    @GetMapping
    public List<Favorito> obtenerTodosLosFavoritos() {
        return favoritoRepository.findAll();
    }

    // Crear un nuevo favorito
    @PostMapping
    public Favorito crearFavorito(@RequestBody Favorito favorito) {
        // Validar que Usuario y Artista existen antes de guardar el favorito
        Usuario usuario = usuarioRepository.findById(favorito.getUsuario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Artista artista = artistaRepository.findById(favorito.getArtista().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado"));

        favorito.setUsuario(usuario);
        favorito.setArtista(artista);
        favorito.setId(new FavoritoId(usuario.getId(), artista.getId()));

        return favoritoRepository.save(favorito);
    }

    // Obtener un favorito por id_usuario e id_artista (clave compuesta)
    @GetMapping("/{usuario_id}/{artista_id}")
    public Favorito obtenerFavoritoPorId(@PathVariable("usuario_id") Long usuario_id,
                                         @PathVariable("artista_id") Long artista_id) {
        // Construimos la clave primaria compuesta
        FavoritoId favoritoId = new FavoritoId(usuario_id, artista_id);
        return favoritoRepository.findById(favoritoId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito no encontrado"));
    }

    // Actualizar un favorito por id_usuario e id_artista
    @PutMapping("/{usuario_id}/{artista_id}")
    public Favorito actualizarFavorito(@PathVariable("usuario_id") Long usuario_id,
                                        @PathVariable("artista_id") Long artista_id,
                                        @RequestBody Favorito detallesFavorito) {
        // Construimos la clave primaria compuesta
        FavoritoId favoritoId = new FavoritoId(usuario_id, artista_id);
        Favorito favorito = favoritoRepository.findById(favoritoId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito no encontrado"));

        // Actualizamos los campos necesarios
        favorito.setFavorito(detallesFavorito.isFavorito());
        return favoritoRepository.save(favorito);
    }

    // Eliminar un favorito por id_usuario e id_artista
    @DeleteMapping("/{usuario_id}/{artista_id}")
    public String eliminarFavorito(@PathVariable("usuario_id") Long usuario_id,
                                    @PathVariable("artista_id") Long artista_id) {
        // Construimos la clave primaria compuesta
        FavoritoId favoritoId = new FavoritoId(usuario_id, artista_id);
        Favorito favorito = favoritoRepository.findById(favoritoId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorito no encontrado"));

        favoritoRepository.delete(favorito);
        return "Favorito eliminado con éxito";
    }
}