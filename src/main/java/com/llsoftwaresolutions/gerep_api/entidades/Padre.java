package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "padres")
@Data
public class Padre extends Usuario {

    @ManyToMany(mappedBy = "padres")
    @Column(nullable = true)
    private List<Alumno> alumnos;
}
