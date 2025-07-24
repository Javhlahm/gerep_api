package com.llsoftwaresolutions.gerep_api.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.entidades.Asistencia;
import com.llsoftwaresolutions.gerep_api.entidades.Grupo;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.GrupoRepositorio;

@Service
public class ReporteServicio {

    @Autowired
    GrupoRepositorio grupoRepositorio;

    @Autowired
    AlumnoRepositorio alumnoRepositorio;

    public List<Map<String, Object>> obtenerReporteDiarioDeAsistencia(LocalDate fecha) {
        List<Grupo> grupos = grupoRepositorio.findAll();
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Grupo grupo : grupos) {
            String nombreGrupo = grupo.getNombre();
            List<Alumno> alumnos = grupo.getAlumnos();
            for (Alumno alumno : alumnos) {
                Map<String, Object> map = new HashMap<>();
                map.put("nombreAlumno", alumno.getNombre());
                map.put("grupo", nombreGrupo);

                Optional<Asistencia> asistenciaOpt = alumno.getAsistencias().stream()
                        .filter(a -> a.getFecha().equals(fecha))
                        .findFirst();

                String estado;
                if (asistenciaOpt.isPresent()) {
                    estado = "asistió";
                } else {
                    estado = "ausente";
                }

                map.put("estado", estado);
                resultado.add(map);
            }
        }
        return resultado;
    }

}
