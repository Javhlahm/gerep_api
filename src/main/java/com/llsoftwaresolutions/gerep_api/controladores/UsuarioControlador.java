package com.llsoftwaresolutions.gerep_api.controladores;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.llsoftwaresolutions.gerep_api.entidades.Director;
import com.llsoftwaresolutions.gerep_api.entidades.Padre;
import com.llsoftwaresolutions.gerep_api.entidades.Profesor;
import com.llsoftwaresolutions.gerep_api.entidades.Usuario;
import com.llsoftwaresolutions.gerep_api.servicios.PadreServicio;
import com.llsoftwaresolutions.gerep_api.servicios.UsuarioServicio;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PadreServicio padreServicio;

    @PostMapping("/registro/profesor")
    public ResponseEntity<Usuario> registrarProfesor(@RequestBody Profesor profesor) {
        profesor.setRol("profesor");
        Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(profesor);
        return ResponseEntity.ok(usuarioRegistrado);
    }

    @PostMapping("/registro/padre")
    public ResponseEntity<Usuario> registrarPadre(@RequestBody Padre padre) {
        padre.setRol("padre");
        Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(padre);
        return ResponseEntity.ok(usuarioRegistrado);
    }

    @PostMapping("/registro/director")
    public ResponseEntity<Usuario> registrarDirector(@RequestBody Director director) {
        director.setRol("director");
        Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(director);
        return ResponseEntity.ok(usuarioRegistrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioEncontrado = usuarioServicio.obtenerUsuarioPorId(id);
        if (usuarioEncontrado.isPresent()) {
            return ResponseEntity.ok(usuarioEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServicio.listarUsuarios());
    }

    @PutMapping("/profesor/{id}")
    public ResponseEntity<Usuario> actualizarProfesor(@PathVariable Long id, @RequestBody Profesor profesor) {
        Optional<Usuario> usuarioExistente = usuarioServicio.obtenerUsuarioPorId(id);
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.ok(usuarioServicio.actualizarUsuario(id, profesor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/padre/{id}")
    public ResponseEntity<Usuario> actualizarPadre(@PathVariable Long id, @RequestBody Padre padre) {
        Optional<Padre> padreExistente = padreServicio.obtenerPadrePorId(id);
        if (padreExistente.isPresent()) {
            return ResponseEntity.ok(padreServicio.actualizarPadre(id, padre));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/director/{id}")
    public ResponseEntity<Usuario> actualizarDirector(@PathVariable Long id, @RequestBody Director director) {
        Optional<Usuario> usuarioExistente = usuarioServicio.obtenerUsuarioPorId(id);
        if (usuarioExistente.isPresent()) {
            return ResponseEntity.ok(usuarioServicio.actualizarUsuario(id, director));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> autenticarUsuario(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String contrasena = datos.get("password");

        Optional<Usuario> autenticado = usuarioServicio.autenticarUsuario(email, contrasena);
        return autenticado.isPresent() ? ResponseEntity.ok(autenticado.get())
                : ResponseEntity.status(401).body(null);
    }
}
