package com.llsoftwaresolutions.gerep_api.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.llsoftwaresolutions.gerep_api.servicios.ReporteExcelUtils;
import com.llsoftwaresolutions.gerep_api.servicios.ReporteServicio;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteControlador {

    @Autowired
    ReporteServicio reporteServicio;

    @GetMapping("/asistencia/diario/excel")
    public ResponseEntity<byte[]> getReporteDiarioAsistenciaExcel(@RequestParam String fecha) {
        LocalDate fechaLocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Map<String, Object>> resultado = reporteServicio.obtenerReporteDiarioDeAsistencia(fechaLocalDate);
        byte[] excelBytes = ReporteExcelUtils.generarExcelReporteDiario(resultado, fecha);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=asistencia_diaria.xlsx")
                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .body(excelBytes);
    }

}
