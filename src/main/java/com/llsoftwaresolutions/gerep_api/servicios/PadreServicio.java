package com.llsoftwaresolutions.gerep_api.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.entidades.Padre;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.PadreRepositorio;

@Service
public class PadreServicio {

    @Autowired
    private PadreRepositorio padreRepositorio;

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    public Padre registrarPadre(Padre padre) {
        return padreRepositorio.save(padre);
    }

    public Optional<Padre> obtenerPadrePorId(Long id) {
        return padreRepositorio.findById(id);
    }

    public List<Padre> listarPadres() {
        return padreRepositorio.findAll();
    }

    public Padre actualizarPadre(Long id, Padre padreActualizado) {
        Optional<Padre> padreOpt = padreRepositorio.findById(id);

        if (padreOpt.isPresent()) {
            Padre existente = padreOpt.get();

            if (padreActualizado.getNombre() != null && !padreActualizado.getNombre().isEmpty()) {
                existente.setNombre(padreActualizado.getNombre());
            }
            if (padreActualizado.getEmail() != null && !padreActualizado.getEmail().isEmpty()) {
                existente.setEmail(padreActualizado.getEmail());
            }
            if (padreActualizado.getTelefono() != null && !padreActualizado.getTelefono().isEmpty()) {
                existente.setTelefono(padreActualizado.getTelefono());
            }

            if (padreActualizado.getAlumnos() != null) {
                List<Alumno> alumnos = alumnoRepositorio.findAllById(
                        padreActualizado.getAlumnos().stream()
                                .map(Alumno::getId)
                                .toList());
                existente.setAlumnos(alumnos);
            }

            return padreRepositorio.save(existente);
        } else {
            throw new RuntimeException("Padre no encontrado");
        }
    }

    public void eliminarPadre(Long id) {
        padreRepositorio.deleteById(id);
    }
}
