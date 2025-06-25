package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "padres")
@Data
public class Padre extends Usuario {

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "padre_alumno", joinColumns = @JoinColumn(name = "padre_id"), inverseJoinColumns = @JoinColumn(name = "alumno_id"))
    private List<Alumno> alumnos = new ArrayList<>();
}
