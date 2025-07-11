package com.llsoftwaresolutions.gerep_api.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.llsoftwaresolutions.gerep_api.entidades.Asistencia;

@Repository
public interface AsistenciaRepositorio extends JpaRepository<Asistencia, Long> {

  @Query("""
      SELECT CASE WHEN COUNT(ast) > 0 THEN true ELSE false END
      FROM   Alumno al
      JOIN   al.asistencias ast
      WHERE  al.id = :alumnoId
        AND  ast.fecha = :fecha
      """)
  boolean asistenciaParaAlumnoEnFecha(@Param("alumnoId") Long alumnoId,
      @Param("fecha") LocalDate fecha);

  @Query(value = """
      SELECT *
      FROM   asistencias
      WHERE  alumno_id = :alumnoId
      ORDER  BY fecha DESC
      """, nativeQuery = true)
  List<Asistencia> findAllByAlumnoId(@Param("alumnoId") Long alumnoId);

}
