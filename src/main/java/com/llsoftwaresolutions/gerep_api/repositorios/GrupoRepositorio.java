package com.llsoftwaresolutions.gerep_api.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.llsoftwaresolutions.gerep_api.entidades.Grupo;

@Repository
public interface GrupoRepositorio extends JpaRepository<Grupo, Long> {

    Optional<Grupo> findByProfesor_Id(Long profesorId);

}
