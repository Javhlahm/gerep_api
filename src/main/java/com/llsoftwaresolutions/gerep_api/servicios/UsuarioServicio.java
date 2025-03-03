package com.llsoftwaresolutions.gerep_api.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.Usuario;
import com.llsoftwaresolutions.gerep_api.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepositorio.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findById(id);
        if (usuarioEncontrado.isPresent()) {
            return usuarioEncontrado;
        } else {
            throw new RuntimeException("Usuario no Encontrado");
        }

    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);

        if (usuario.isPresent()) {
            usuario.get().setNombre(usuarioActualizado.getNombre());
            usuario.get().setTelefono(usuarioActualizado.getTelefono());
            usuario.get().setEmail(usuarioActualizado.getEmail());

            if (usuarioActualizado.getContrasena() != null && !usuarioActualizado.getContrasena().isEmpty()) {
                usuario.get().setContrasena(passwordEncoder.encode(usuarioActualizado.getContrasena()));
            }
            return usuarioRepositorio.save(usuario.get());
        } else {
            throw new RuntimeException("Usuario no Encontrado");
        }

    }

    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }

    public boolean autenticarUsuario(String email, String contrasena) {
        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findByEmail(email);

        if (usuarioEncontrado.isPresent()) {
            return passwordEncoder.matches(contrasena, usuarioEncontrado.get().getContrasena());
        } else {
            throw new RuntimeException("Usuario no Encontrado");
        }

    }
}
