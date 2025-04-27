package com.llsoftwaresolutions.gerep_api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.llsoftwaresolutions.gerep_api.entidades.Incidencia;

@Repository
public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {
    List<Incidencia> findByAlumnoId(Long alumnoId);

}
