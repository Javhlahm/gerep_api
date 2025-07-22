package com.llsoftwaresolutions.gerep_api.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.entidades.Asistencia;
import com.llsoftwaresolutions.gerep_api.entidades.Incidencia;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.IncidenciaRepositorio;

@Service
public class IncidenciaServicio {

    @Autowired
    private IncidenciaRepositorio incidenciaRepositorio;

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    public List<Incidencia> listarIncidencias() {
        return incidenciaRepositorio.findAll();
    }

    public Optional<Incidencia> obtenerPorId(Long id) {
        return incidenciaRepositorio.findById(id);
    }

    public List<Incidencia> obtenerIncidenciasPorAlumno(Long alumnoId) {
        Alumno alumno = alumnoRepositorio.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        return alumno.getIncidencias();
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

    public Incidencia registrarIncidencia(Long alumnoId, String descripcion, LocalDate fecha, String status,
            String justificante) {
        Alumno alumno = alumnoRepositorio.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Incidencia incidencia = new Incidencia();
        incidencia.setDescripcion(descripcion);
        incidencia.setFecha(fecha);
        incidencia.setStatus(status);
        incidencia.setJustificante(justificante);

        alumno.getIncidencias().add(incidencia);
        alumnoRepositorio.save(alumno);
        return incidencia;
    }

}
