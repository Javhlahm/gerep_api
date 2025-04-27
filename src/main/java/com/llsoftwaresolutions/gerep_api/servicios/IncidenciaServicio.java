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
        Optional<Incidencia> incidencia = incidenciaRepositorio.findById(id);
        if (incidencia.isPresent()) {
            incidencia.get().setDescripcion(incidenciaActualizada.getDescripcion());
            incidencia.get().setFecha(incidenciaActualizada.getFecha());
            incidencia.get().setStatus(incidenciaActualizada.getStatus());
            incidencia.get().setJustificante(incidenciaActualizada.getJustificante());
            incidencia.get().setAlumno(incidenciaActualizada.getAlumno());
            return incidenciaRepositorio.save(incidencia.get());
        } else {
            throw new RuntimeException("Incidencia no encontrada");
        }
    }

    public void eliminarIncidencia(Long id) {
        incidenciaRepositorio.deleteById(id);
    }
}
