package com.llsoftwaresolutions.gerep_api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.llsoftwaresolutions.gerep_api.entidades.ContactoEmergencia;

@Repository
public interface ContactoRepositorio extends JpaRepository<ContactoEmergencia, Long> {

}
