package com.llsoftwaresolutions.gerep_api.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;

@Service
public class AlumnoServicio {

    @Autowired
    AlumnoRepositorio alumnoRepositorio;

    public Alumno registrarAlumno(Alumno alumno) {
        return alumnoRepositorio.save(alumno);
    }

    public Optional<Alumno> obtenerAlumnoPorId(Long id) {
        Optional<Alumno> alumnoEncontrado = alumnoRepositorio.findById(id);
        return alumnoEncontrado;
    }

    public Optional<Alumno> buscarPorTagUid(String tagUid) {
        Optional<Alumno> alumnoEncontrado = alumnoRepositorio.findByTagUid(tagUid);
        return alumnoEncontrado;
    }

    public List<Alumno> listarAlumnos() {
        return alumnoRepositorio.findAll();
    }

    public Alumno actualizarAlumno(Long id, Alumno alumnoActualizado) {
        Optional<Alumno> alumno = alumnoRepositorio.findById(id);

       if (alumno.isPresent()) {
    Alumno existente = alumno.get();

    if (alumnoActualizado.getTagUid() != null && !alumnoActualizado.getTagUid().isEmpty()) {
        existente.setTagUid(alumnoActualizado.getTagUid());
    }
    if (alumnoActualizado.getNombre() != null && !alumnoActualizado.getNombre().isEmpty()) {
        existente.setNombre(alumnoActualizado.getNombre());
    }
    if (alumnoActualizado.getEdad() > 0) {
        existente.setEdad(alumnoActualizado.getEdad());
    }
    if (alumnoActualizado.getFoto() != null && !alumnoActualizado.getFoto().isEmpty()) {
        existente.setFoto(alumnoActualizado.getFoto());
    }
    if (alumnoActualizado.getPadres() != null && !alumnoActualizado.getPadres().isEmpty()) {
        existente.setPadres(alumnoActualizado.getPadres());
    }
    if (alumnoActualizado.getContactoEmergencia() != null && !alumnoActualizado.getContactoEmergencia().isEmpty()) {
        existente.setContactoEmergencia(alumnoActualizado.getContactoEmergencia());
    }
    if (alumnoActualizado.getHistorialMedico() != null && !alumnoActualizado.getHistorialMedico().isEmpty()) {
        existente.setHistorialMedico(alumnoActualizado.getHistorialMedico());
    }
    if (alumnoActualizado.getTipoSangre() != null && !alumnoActualizado.getTipoSangre().isEmpty()) {
        existente.setTipoSangre(alumnoActualizado.getTipoSangre());
    }
    if (alumnoActualizado.getCuidadosEspeciales() != null && !alumnoActualizado.getCuidadosEspeciales().isEmpty()) {
        existente.setCuidadosEspeciales(alumnoActualizado.getCuidadosEspeciales());
    }
if (alumnoActualizado.getGrupo() != null && alumnoActualizado.getGrupo().getId() != null) {
    existente.setGrupo(alumnoActualizado.getGrupo());
}

    if (alumnoActualizado.getAsistencias() != null && !alumnoActualizado.getAsistencias().isEmpty()) {
        existente.setAsistencias(alumnoActualizado.getAsistencias());
    }
    if (alumnoActualizado.getIncidencias() != null && !alumnoActualizado.getIncidencias().isEmpty()) {
        existente.setIncidencias(alumnoActualizado.getIncidencias());
    }
    return alumnoRepositorio.save(existente);
} else {
    throw new RuntimeException("Usuario no Encontrado");
}

    }

    public void eliminarAlumno(Long id) {
        alumnoRepositorio.deleteById(id);
    }
}
