package com.llsoftwaresolutions.gerep_api.controladores;

import com.llsoftwaresolutions.gerep_api.entidades.Grupo;
import com.llsoftwaresolutions.gerep_api.servicios.GrupoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
@CrossOrigin(origins = "*")
public class GrupoControlador {

    @Autowired
    private GrupoServicio grupoServicio;

    @PostMapping
    public ResponseEntity<Grupo> crearGrupo(@RequestBody Grupo grupo) {
        Grupo grupoGuardado = grupoServicio.registrarGrupo(grupo);
        return ResponseEntity.ok(grupoGuardado);
    }

    @GetMapping
    public ResponseEntity<List<Grupo>> obtenerTodosLosGrupos() {
        return ResponseEntity.ok(grupoServicio.obtenerTodosLosGrupos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> obtenerGrupoPorId(@PathVariable Long id) {
        Optional<Grupo> grupoEncontrado = grupoServicio.obtenerGrupoPorId(id);
        if (grupoEncontrado.isPresent()) {
            return ResponseEntity.ok(grupoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<Grupo> obtenerGrupoPorProfesor(@PathVariable Long profesorId) {
        Optional<Grupo> grupoEncontrado = grupoServicio.obtenerGrupoPorProfesor(profesorId);
        if (grupoEncontrado.isPresent()) {
            return ResponseEntity.ok(grupoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> actualizarGrupo(@PathVariable Long id, @RequestBody Grupo grupoActualizado) {
        Optional<Grupo> grupoExistente = grupoServicio.obtenerGrupoPorId(id);
        if (grupoExistente.isPresent()) {
            return ResponseEntity.ok(grupoServicio.actualizarGrupo(id, grupoActualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGrupo(@PathVariable Long id) {
        grupoServicio.eliminarGrupo(id);
        return ResponseEntity.noContent().build();
    }
}
