package com.pgv.naftra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgv.naftra.ResourceNotFoundException;
import com.pgv.naftra.model.Artista;
import com.pgv.naftra.repository.ArtistaRepository;

import java.util.List;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/api/artista")
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    public List<Artista> obtenerTodosLosArtista() {
        return artistaRepository.findAll();
    }

    @PostMapping
    public Artista crearArtista(@RequestBody Artista artista) {
        return artistaRepository.save(artista);
    }

    @GetMapping("/{id}")
    public Artista obtenerArtistaPorId(@PathVariable("id") Long id) {
        return artistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado"));
    }

    @PutMapping("/{id}")
    public Artista actualizarArtista(@PathVariable("id") Long id, @RequestBody Artista detallesArtista) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado"));

        artista.setNombre(detallesArtista.getNombre());
        artista.setEdad(detallesArtista.getEdad());
        artista.setGeneroMusical(detallesArtista.getGeneroMusical());

        return artistaRepository.save(artista);
    }

    @DeleteMapping("/{id}")
    public Artista eliminarArtista(@PathVariable("id") Long id) {
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artista no encontrado"));
        artistaRepository.deleteById(id);
        return artista;
    }
}
