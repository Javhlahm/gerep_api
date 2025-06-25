package com.llsoftwaresolutions.gerep_api.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.Incidencia;
import com.llsoftwaresolutions.gerep_api.repositorios.IncidenciaRepositorio;

@Service
public class IncidenciaServicio {

    @Autowired
    private IncidenciaRepositorio incidenciaRepositorio;

    public List<Incidencia> listarIncidencias() {
        return incidenciaRepositorio.findAll();
    }

    public Optional<Incidencia> obtenerPorId(Long id) {
        return incidenciaRepositorio.findById(id);
    }

    public List<Incidencia> buscarPorAlumnoId(Long alumnoId) {
        return incidenciaRepositorio.findByAlumnoId(alumnoId);
    }

    public Incidencia guardarIncidencia(Incidencia incidencia) {
        return incidenciaRepositorio.save(incidencia);
    }

    public Incidencia actualizarIncidencia(Long id, Incidencia incidenciaActualizada) {
        Incidencia incidenciaExistente = incidenciaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada"));

        if (incidenciaActualizada.getDescripcion() != null && !incidenciaActualizada.getDescripcion().isEmpty()) {
            incidenciaExistente.setDescripcion(incidenciaActualizada.getDescripcion());
        }
        if (incidenciaActualizada.getFecha() != null) {
            incidenciaExistente.setFecha(incidenciaActualizada.getFecha());
        }
        if (incidenciaActualizada.getStatus() != null && !incidenciaActualizada.getStatus().isEmpty()) {
            incidenciaExistente.setStatus(incidenciaActualizada.getStatus());
        }
        if (incidenciaActualizada.getJustificante() != null && !incidenciaActualizada.getJustificante().isEmpty()) {
            incidenciaExistente.setJustificante(incidenciaActualizada.getJustificante());
        }

        return incidenciaRepositorio.save(incidenciaExistente);
    }

    public void eliminarIncidencia(Long id) {
        incidenciaRepositorio.deleteById(id);
    }
}
