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
    IncidenciaRepositorio incidenciaRepositorio;

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
        Optional<Incidencia> incidenciaOpt = incidenciaRepositorio.findById(id);
        if (incidenciaOpt.isPresent()) {
            Incidencia incidencia = incidenciaOpt.get();

            if (incidenciaActualizada.getDescripcion() != null && !incidenciaActualizada.getDescripcion().isEmpty()) {
                incidencia.setDescripcion(incidenciaActualizada.getDescripcion());
            }
            if (incidenciaActualizada.getFecha() != null) {
                incidencia.setFecha(incidenciaActualizada.getFecha());
            }
            if (incidenciaActualizada.getStatus() != null && !incidenciaActualizada.getStatus().isEmpty()) {
                incidencia.setStatus(incidenciaActualizada.getStatus());
            }
            if (incidenciaActualizada.getJustificante() != null && !incidenciaActualizada.getJustificante().isEmpty()) {
                incidencia.setJustificante(incidenciaActualizada.getJustificante());
            }
            if (incidenciaActualizada.getAlumno() != null) {
                incidencia.setAlumno(incidenciaActualizada.getAlumno());
            }

            return incidenciaRepositorio.save(incidencia);
        } else {
            throw new RuntimeException("Incidencia no encontrada");
        }
    }

    public void eliminarIncidencia(Long id) {
        incidenciaRepositorio.deleteById(id);
    }
}
