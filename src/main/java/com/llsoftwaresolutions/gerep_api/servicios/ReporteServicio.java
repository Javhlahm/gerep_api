package com.llsoftwaresolutions.gerep_api.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;
import com.llsoftwaresolutions.gerep_api.entidades.Asistencia;
import com.llsoftwaresolutions.gerep_api.entidades.Grupo;
import com.llsoftwaresolutions.gerep_api.repositorios.AlumnoRepositorio;
import com.llsoftwaresolutions.gerep_api.repositorios.GrupoRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReporteServicio {

    @Autowired
    GrupoRepositorio grupoRepositorio;

    @Autowired
    AlumnoRepositorio alumnoRepositorio;

    public List<Map<String, Object>> obtenerAsistenciaPorGrupoYFecha(Long grupoId, LocalDate fecha) {
        Optional<Grupo> grupoOpt = grupoRepositorio.findById(grupoId);

        if (grupoOpt.isEmpty()) {
            throw new EntityNotFoundException("Grupo no encontrado con id: " + grupoId);
        }

        Grupo grupo = grupoOpt.get();
        List<Alumno> alumnos = grupo.getAlumnos();

        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Alumno alumno : alumnos) {
            Map<String, Object> asistenciaMap = new HashMap<>();
            asistenciaMap.put("nombreAlumno", alumno.getNombre());
            asistenciaMap.put("grupo", grupo.getNombre());
            asistenciaMap.put("fecha", fecha.toString());

            Optional<Asistencia> asistenciaOpt = alumno.getAsistencias().stream()
                    .filter(a -> a.getFecha().equals(fecha))
                    .findFirst();

            asistenciaMap.put("estado", asistenciaOpt.map(Asistencia::getEstado).orElse("No registrada"));

            resultado.add(asistenciaMap);
        }

        return resultado;
    }

    public List<Map<String, Object>> obtenerAusenciasDelDia(LocalDate fecha, Optional<Long> grupoId) {
        List<Alumno> alumnos;

        if (grupoId.isPresent()) {
            Optional<Grupo> grupoOpt = grupoRepositorio.findById(grupoId.get());
            if (grupoOpt.isEmpty()) {
                throw new EntityNotFoundException("Grupo no encontrado con id: " + grupoId.get());
            }
            alumnos = grupoOpt.get().getAlumnos();
        } else {
            alumnos = alumnoRepositorio.findAll();
        }

        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Alumno alumno : alumnos) {
            Optional<Asistencia> asistenciaOpt = alumno.getAsistencias().stream()
                    .filter(a -> a.getFecha().equals(fecha))
                    .findFirst();

            if (asistenciaOpt.isPresent() && "ausente".equalsIgnoreCase(asistenciaOpt.get().getEstado())) {
                Map<String, Object> map = new HashMap<>();
                map.put("nombreAlumno", alumno.getNombre());
                map.put("grupo", alumno.getGrupo() != null ? alumno.getGrupo().getNombre() : "Sin grupo");
                map.put("fecha", fecha.toString());
                map.put("estado", "ausente");
                resultado.add(map);
            }
        }

        return resultado;
    }

    public List<Map<String, Object>> obtenerHistorialAsistenciaPorAlumno(Long alumnoId) {
        Optional<Alumno> alumnoOpt = alumnoRepositorio.findById(alumnoId);
        if (alumnoOpt.isEmpty()) {
            throw new EntityNotFoundException("Alumno no encontrado con id: " + alumnoId);
        }

        Alumno alumno = alumnoOpt.get();

        return alumno.getAsistencias().stream()
                .sorted(Comparator.comparing(Asistencia::getFecha))
                .map(asistencia -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("fecha", asistencia.getFecha().toString());
                    map.put("estado", asistencia.getEstado());
                    return map;
                }).collect(Collectors.toList());
    }

    public List<Map<String, Object>> obtenerRankingAsistencia(Optional<Long> grupoIdOpt) {
        List<Alumno> alumnos;

        if (grupoIdOpt.isPresent()) {
            Grupo grupo = grupoRepositorio.findById(grupoIdOpt.get())
                    .orElseThrow(() -> new EntityNotFoundException("Grupo no encontrado"));
            alumnos = grupo.getAlumnos();
        } else {
            alumnos = alumnoRepositorio.findAll();
        }

        return alumnos.stream().map(alumno -> {
            long totalAsistencias = alumno.getAsistencias().size();
            long faltas = alumno.getAsistencias().stream()
                    .filter(a -> a.getEstado().equalsIgnoreCase("ausente"))
                    .count();

            Map<String, Object> map = new HashMap<>();
            map.put("alumnoId", alumno.getId());
            map.put("nombre", alumno.getNombre());
            map.put("total", totalAsistencias);
            map.put("faltas", faltas);
            map.put("porcentajeFaltas", totalAsistencias > 0 ? (faltas * 100.0 / totalAsistencias) : 0);

            return map;
        }).sorted((a1, a2) -> Long.compare((Long) a2.get("faltas"), (Long) a1.get("faltas")))
                .collect(Collectors.toList());
    }

    public Map<String, Object> obtenerPorcentajeAsistenciaMensual(int mes, int anio, Optional<Long> grupoIdOpt) {
        List<Alumno> alumnos;

        if (grupoIdOpt.isPresent()) {
            Grupo grupo = grupoRepositorio.findById(grupoIdOpt.get())
                    .orElseThrow(() -> new EntityNotFoundException("Grupo no encontrado"));
            alumnos = grupo.getAlumnos();
        } else {
            alumnos = alumnoRepositorio.findAll();
        }

        long totalAsistencias = 0;
        long presentes = 0;

        for (Alumno alumno : alumnos) {
            for (Asistencia asistencia : alumno.getAsistencias()) {
                if (asistencia.getFecha().getMonthValue() == mes && asistencia.getFecha().getYear() == anio) {
                    totalAsistencias++;
                    if (asistencia.getEstado().equalsIgnoreCase("presente")) {
                        presentes++;
                    }
                }
            }
        }

        double porcentaje = totalAsistencias > 0 ? (presentes * 100.0 / totalAsistencias) : 0;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("mes", mes);
        resultado.put("anio", anio);
        resultado.put("grupo", grupoIdOpt.isPresent() ? grupoIdOpt.get() : "Todos");
        resultado.put("totalAsistencias", totalAsistencias);
        resultado.put("presentes", presentes);
        resultado.put("porcentajeAsistencia", porcentaje);

        return resultado;
    }

}
