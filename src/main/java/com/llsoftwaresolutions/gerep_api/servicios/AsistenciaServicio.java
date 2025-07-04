package com.llsoftwaresolutions.gerep_api.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.Asistencia;
import com.llsoftwaresolutions.gerep_api.repositorios.AsistenciaRepositorio;

@Service
public class AsistenciaServicio {

    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    public Asistencia registrarAsistencia(Asistencia asistencia) {
        return asistenciaRepositorio.save(asistencia);
    }

    public List<Asistencia> listarAsistencias() {
        return asistenciaRepositorio.findAll();
    }

    public Optional<Asistencia> obtenerAsistenciaPorId(Long id) {
        return asistenciaRepositorio.findById(id);
    }

    public Asistencia actualizarAsistencia(Long id, Asistencia asistenciaActualizada) {
        Asistencia asistenciaExistente = asistenciaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia con id " + id + " no encontrada"));

        if (asistenciaActualizada.getFecha() != null) {
            asistenciaExistente.setFecha(asistenciaActualizada.getFecha());
        }
        if (asistenciaActualizada.getEstado() != null && !asistenciaActualizada.getEstado().isEmpty()) {
            asistenciaExistente.setEstado(asistenciaActualizada.getEstado());
        }

        return asistenciaRepositorio.save(asistenciaExistente);
    }

    public void eliminarAsistencia(Long id) {
        asistenciaRepositorio.deleteById(id);
    }
}
