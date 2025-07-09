package com.llsoftwaresolutions.gerep_api.servicios;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.entidades.Grupo;
import com.llsoftwaresolutions.gerep_api.entidades.Profesor;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.GrupoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.ProfesorRepositorio;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServicio {

    @Autowired
    private GrupoRepositorio grupoRepository;

    @Autowired
    private ProfesorRepositorio profesorRepository;

    @Autowired
    private AlumnoRepositorio alumnoRepository;

    public Grupo registrarGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> obtenerTodosLosGrupos() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> obtenerGrupoPorId(Long id) {
        return grupoRepository.findById(id);
    }

    @Transactional
    public void eliminarGrupo(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        grupoRepository.delete(grupo);
    }

    @Transactional
    public Grupo actualizarGrupo(Long id, Grupo grupoActualizado) {
        Grupo grupoExistente = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        // Actualizar nombre
        if (grupoActualizado.getNombre() != null && !grupoActualizado.getNombre().isEmpty()) {
            grupoExistente.setNombre(grupoActualizado.getNombre());
        }

        // Actualizar profesor
        if (grupoActualizado.getProfesor() != null && grupoActualizado.getProfesor().getId() != null) {
            Profesor profesor = profesorRepository.findById(grupoActualizado.getProfesor().getId())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
            grupoExistente.setProfesor(profesor);
        }

        // Actualizar lista de alumnos
        if (grupoActualizado.getAlumnos() != null) {
            List<Alumno> alumnos = alumnoRepository.findAllById(
                    grupoActualizado.getAlumnos()
                            .stream()
                            .map(Alumno::getId)
                            .toList());
            grupoExistente.setAlumnos(alumnos);
        }

        return grupoRepository.save(grupoExistente);
    }

    public Optional<Grupo> obtenerGrupoPorProfesor(Long profesorId) {
        return grupoRepository.findByProfesor_Id(profesorId);
    }
}
