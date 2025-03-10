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

    public List<Alumno> listarAlumnos() {
        return alumnoRepositorio.findAll();
    }

    public Alumno actualizarAlumno(Long id, Alumno alumnoActualizado) {
        Optional<Alumno> alumno = alumnoRepositorio.findById(id);

        if (alumno.isPresent()) {
            alumno.get().setNombre(alumnoActualizado.getNombre());
            alumno.get().setEdad(alumnoActualizado.getEdad());
            alumno.get().setFoto(alumnoActualizado.getFoto());
            alumno.get().setPadres(alumnoActualizado.getPadres());
            alumno.get().setContactoEmergencia(alumnoActualizado.getContactoEmergencia());
            alumno.get().setHistorialMedico(alumnoActualizado.getHistorialMedico());

            return alumnoRepositorio.save(alumno.get());
        } else {
            throw new RuntimeException("Usuario no Encontrado");
        }
    }

    public void eliminarAlumno(Long id) {
        alumnoRepositorio.deleteById(id);
    }
}
