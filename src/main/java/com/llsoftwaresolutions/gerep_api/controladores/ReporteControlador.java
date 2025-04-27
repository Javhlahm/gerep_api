package com.llsoftwaresolutions.gerep_api.controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llsoftwaresolutions.gerep_api.servicios.ReporteServicio;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteControlador {

    @Autowired
    ReporteServicio reporteServicio;

    @GetMapping("/asistencia/grupo")
    public ResponseEntity<List<Map<String, Object>>> getAsistenciaDiariaPorGrupo(
            @RequestParam Long grupoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Map<String, Object>> resultado = reporteServicio.obtenerAsistenciaPorGrupoYFecha(grupoId, fecha);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/ausencias")
    public ResponseEntity<List<Map<String, Object>>> getAusenciasDelDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) Long grupoId) {
        List<Map<String, Object>> resultado = reporteServicio.obtenerAusenciasDelDia(fecha,
                Optional.ofNullable(grupoId));
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/asistencia/alumno/{id}")
    public ResponseEntity<List<Map<String, Object>>> getHistorialAsistenciaPorAlumno(@PathVariable Long id) {
        List<Map<String, Object>> historial = reporteServicio.obtenerHistorialAsistenciaPorAlumno(id);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/asistencia/ranking")
    public ResponseEntity<List<Map<String, Object>>> getRankingAsistencia() {
        return ResponseEntity.ok(reporteServicio.obtenerRankingAsistencia(Optional.empty()));
    }

    @GetMapping("/asistencia/ranking/grupo/{grupoId}")
    public ResponseEntity<List<Map<String, Object>>> getRankingAsistenciaPorGrupo(@PathVariable Long grupoId) {
        return ResponseEntity.ok(reporteServicio.obtenerRankingAsistencia(Optional.of(grupoId)));
    }

    @GetMapping("/asistencia/porcentaje/mensual")
    public ResponseEntity<Map<String, Object>> getPorcentajeAsistenciaMensual(
            @RequestParam int mes,
            @RequestParam int anio) {
        return ResponseEntity.ok(reporteServicio.obtenerPorcentajeAsistenciaMensual(mes, anio, Optional.empty()));
    }

    @GetMapping("/asistencia/porcentaje/mensual/grupo/{grupoId}")
    public ResponseEntity<Map<String, Object>> getPorcentajeAsistenciaMensualPorGrupo(
            @PathVariable Long grupoId,
            @RequestParam int mes,
            @RequestParam int anio) {
        return ResponseEntity.ok(reporteServicio.obtenerPorcentajeAsistenciaMensual(mes, anio, Optional.of(grupoId)));
    }

}
