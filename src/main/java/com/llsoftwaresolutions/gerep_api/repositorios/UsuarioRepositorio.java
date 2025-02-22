package com.llsoftwaresolutions.gerep_api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.llsoftwaresolutions.gerep_api.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
