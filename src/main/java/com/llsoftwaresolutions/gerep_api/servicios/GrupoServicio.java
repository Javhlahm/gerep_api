package com.llsoftwaresolutions.gerep_api.servicios;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.entidades.Grupo;
import com.llsoftwaresolutions.gerep_api.entidades.Profesor;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.GrupoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.ProfesorRepositorio;
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
        Grupo grupoGuardado = grupoRepository.save(grupo);

        if (grupoGuardado.getProfesor() != null) {
            Long profesorId = grupoGuardado.getProfesor().getId();
            Optional<Profesor> profesorCompleto = profesorRepository.findById(profesorId);

            profesorCompleto.get().setGrupo(grupoGuardado);
            profesorRepository.save(profesorCompleto.get());
        }
        return grupoGuardado;
    }

    public List<Grupo> obtenerTodosLosGrupos() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> obtenerGrupoPorId(Long id) {
        return grupoRepository.findById(id);
    }

    public void eliminarGrupo(Long id) {
        grupoRepository.deleteById(id);
    }

    public Grupo actualizarGrupo(Long id, Grupo grupoActualizado) {
        Optional<Grupo> grupoOpt = grupoRepository.findById(id);

        if (grupoOpt.isPresent()) {
            Grupo grupoExistente = grupoOpt.get();

            if (grupoActualizado.getNombre() != null && !grupoActualizado.getNombre().isEmpty()) {
                grupoExistente.setNombre(grupoActualizado.getNombre());
            }

            if (grupoActualizado.getDirector() != null) {
                grupoExistente.setDirector(grupoActualizado.getDirector());
            }

            if (grupoActualizado.getProfesor() != null) {
                grupoExistente.setProfesor(grupoActualizado.getProfesor());

                Optional<Profesor> profesor = profesorRepository.findById(grupoActualizado.getProfesor().getId());
                profesor.get().setGrupo(grupoExistente);
                profesorRepository.save(profesor.get());
            }

            if (grupoActualizado.getAlumnos() != null) {
                grupoExistente.setAlumnos(grupoActualizado.getAlumnos());

                for (Alumno alumno : grupoActualizado.getAlumnos()) {
                    alumno.setGrupo(grupoExistente);
                    alumnoRepository.save(alumno);
                }
            }

            return grupoRepository.save(grupoExistente);
        } else {
            throw new RuntimeException("Grupo no encontrado");
        }
    }

}
