package com.llsoftwaresolutions.gerep_api.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.llsoftwaresolutions.gerep_api.entidades.Alumno;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {

    Optional<Alumno> findByTagUid(String tagUid);
}
