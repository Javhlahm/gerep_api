package com.llsoftwaresolutions.gerep_api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.llsoftwaresolutions.gerep_api.entidades.Incidencia;

public interface IncidenciaRepositorio extends JpaRepository<Incidencia, Long> {

}
