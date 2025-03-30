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

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.servicios.AlumnoServicio;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio alumnoServicio;

    @PostMapping
    public ResponseEntity<Alumno> registrarAlumno(@RequestBody Alumno alumno) {
        Alumno alumnoRegistrado = alumnoServicio.registrarAlumno(alumno);
        return ResponseEntity.ok(alumnoRegistrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> obtenerAlumno(@PathVariable Long id) {
        Optional<Alumno> alumnoEncontrado = alumnoServicio.obtenerAlumnoPorId(id);
        if (alumnoEncontrado.isPresent()) {
            return ResponseEntity.ok(alumnoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tag/{tagUid}")
    public ResponseEntity<Alumno> buscarPorTagUid(@PathVariable String tagUid) {
        Optional<Alumno> alumnoEncontrado = alumnoServicio.buscarPorTagUid(tagUid);
        if (alumnoEncontrado.isPresent()) {
            return ResponseEntity.ok(alumnoEncontrado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> listarAlumnos() {
        return ResponseEntity.ok(alumnoServicio.listarAlumnos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Alumno> alumnoExistente = alumnoServicio.obtenerAlumnoPorId(id);
        if (alumnoExistente.isPresent()) {
            return ResponseEntity.ok(alumnoServicio.actualizarAlumno(id, alumno));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        alumnoServicio.eliminarAlumno(id);
        return ResponseEntity.noContent().build();
    }
}
