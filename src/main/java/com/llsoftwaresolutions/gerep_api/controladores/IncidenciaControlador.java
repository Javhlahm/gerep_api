package com.llsoftwaresolutions.gerep_api.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.llsoftwaresolutions.gerep_api.entidades.Incidencia;
import com.llsoftwaresolutions.gerep_api.servicios.IncidenciaServicio;

@RestController
@RequestMapping("/incidencias")
@CrossOrigin(origins = "*")
public class IncidenciaControlador {

    @Autowired
    IncidenciaServicio incidenciaServicio;

    @GetMapping
    public ResponseEntity<List<Incidencia>> listar() {
        return ResponseEntity.ok(incidenciaServicio.listarIncidencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtener(@PathVariable Long id) {
        Optional<Incidencia> incidencia = incidenciaServicio.obtenerPorId(id);
        if (incidencia.isPresent()) {
            return ResponseEntity.ok(incidencia.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Incidencia>> obtenerPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(incidenciaServicio.buscarPorAlumnoId(alumnoId));
    }

    @PostMapping
    public ResponseEntity<Incidencia> crear(@RequestBody Incidencia incidencia) {
        return ResponseEntity.ok(incidenciaServicio.guardarIncidencia(incidencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Incidencia> actualizar(@PathVariable Long id, @RequestBody Incidencia incidencia) {
        Incidencia actualizada = incidenciaServicio.actualizarIncidencia(id, incidencia);
        return actualizada != null ? ResponseEntity.ok(actualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        incidenciaServicio.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }

}
