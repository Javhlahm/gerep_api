package com.llsoftwaresolutions.gerep_api.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.entidades.Asistencia;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.AsistenciaRepositorio;

@Service
public class AsistenciaServicio {

    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    public Asistencia registrarAsistencia(Long alumnoId, LocalDate fecha, String estado) {

        Alumno alumno = alumnoRepositorio.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        boolean existe = asistenciaRepositorio.asistenciaParaAlumnoEnFecha(alumnoId, fecha);
        if (existe) {
            throw new IllegalStateException("Ya existe asistencia para ese alumno y fecha");
        }

        Asistencia nueva = new Asistencia();
        nueva.setFecha(fecha);
        nueva.setEstado(estado);

        alumno.getAsistencias().add(nueva);

        alumnoRepositorio.save(alumno);
        return nueva;
    }

    @Transactional(readOnly = true)
    public List<Asistencia> listarAsistenciasPorAlumno(Long alumnoId) {
        return asistenciaRepositorio.findAllByAlumnoId(alumnoId);
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

    public boolean asistenciaRegistradaFecha(Long alumnoId, LocalDate fecha) {
        return asistenciaRepositorio.asistenciaParaAlumnoEnFecha(alumnoId, fecha);
    }
}
