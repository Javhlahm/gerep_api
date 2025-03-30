package com.llsoftwaresolutions.gerep_api.servicios;

import com.llsoftwaresolutions.gerep_api.entidades.Grupo;
import com.llsoftwaresolutions.gerep_api.repositorios.GrupoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoServicio {

    @Autowired
    private GrupoRepositorio grupoRepository;

    public Grupo registrarGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
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
        Optional<Grupo> grupo = grupoRepository.findById(id);

        if (grupo.isPresent()) {
            grupo.get().setNombre(grupoActualizado.getNombre());
            grupo.get().setDirector(grupoActualizado.getDirector());
            grupo.get().setProfesor(grupoActualizado.getProfesor());
            grupo.get().setAlumnos(grupoActualizado.getAlumnos());

            return grupoRepository.save(grupo.get());
        } else {
            throw new RuntimeException("Grupo no Encontrado");
        }

    }
}
