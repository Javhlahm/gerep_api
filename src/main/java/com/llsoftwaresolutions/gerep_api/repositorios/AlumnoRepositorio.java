package com.llsoftwaresolutions.gerep_api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {

}
