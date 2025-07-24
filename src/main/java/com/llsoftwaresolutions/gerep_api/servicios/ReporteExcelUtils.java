package com.llsoftwaresolutions.gerep_api.servicios;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public class ReporteExcelUtils {

    public static byte[] generarExcelReporteDiario(List<Map<String, Object>> reporte, String fechaStr) {
        String[] encabezado = { "nombreAlumno", "grupo", "estado" };
        return generarExcelGenerico(reporte, encabezado, fechaStr);
    }

    public static byte[] generarExcelGenerico(List<Map<String, Object>> reporte, String[] encabezado, String fechaStr) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet hoja = workbook.createSheet("Reporte");

            Row titulo = hoja.createRow(0);
            titulo.createCell(0).setCellValue("Reporte de asistencias del día " + fechaStr);
            hoja.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, encabezado.length - 1));

            Row encabezado1 = hoja.createRow(1);
            for (int i = 0; i < encabezado.length; i++) {
                encabezado1.createCell(i).setCellValue(encabezado[i]);
            }

            int filaIndice = 2;
            for (Map<String, Object> fila : reporte) {
                Row filaNueva = hoja.createRow(filaIndice++);
                for (int i = 0; i < encabezado.length; i++) {
                    Object valor = fila.get(encabezado[i]);
                    filaNueva.createCell(i).setCellValue(valor != null ? valor.toString() : "");
                }
            }
            workbook.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando Excel", e);
        }
    }

}
