package com.pgv.naftra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pgv.naftra.ResourceNotFoundException;
import com.pgv.naftra.model.Usuario;
import com.pgv.naftra.repository.UsuarioRepository;

import java.util.List;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuario() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario detallesUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuario.setNombreUsuario(detallesUsuario.getNombreUsuario());
        usuario.setClave(detallesUsuario.getClave());

        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public Usuario eliminarUsuario(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRepository.deleteById(id);
        return usuario;
    }
}
