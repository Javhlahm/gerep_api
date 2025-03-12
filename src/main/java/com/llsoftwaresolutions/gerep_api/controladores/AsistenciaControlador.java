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

import com.llsoftwaresolutions.gerep_api.entidades.Asistencia;
import com.llsoftwaresolutions.gerep_api.servicios.AsistenciaServicio;

@RestController
@RequestMapping("/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaControlador {

    @Autowired
    private AsistenciaServicio asistenciaServicio;

    @PostMapping("/registrar")
    public ResponseEntity<Asistencia> registrarAsistencia(@RequestBody Asistencia asistencia) {
        return ResponseEntity.ok(asistenciaServicio.registrarAsistencia(asistencia));
    }

    @GetMapping
    public ResponseEntity<List<Asistencia>> listarAsistencias() {
        return ResponseEntity.ok(asistenciaServicio.listarAsistencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> obtenerAsistencia(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaServicio.obtenerAsistenciaPorId(id);
        return asistencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Asistencia>> obtenerAsistenciasPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(asistenciaServicio.obtenerAsistenciasPorAlumno(alumnoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizarAsistencia(@PathVariable Long id, @RequestBody Asistencia asistencia) {
        Asistencia asistenciaActualizada = asistenciaServicio.actualizarAsistencia(id, asistencia);
        return asistenciaActualizada != null ? ResponseEntity.ok(asistenciaActualizada)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsistencia(@PathVariable Long id) {
        asistenciaServicio.eliminarAsistencia(id);
        return ResponseEntity.noContent().build();
    }
}
