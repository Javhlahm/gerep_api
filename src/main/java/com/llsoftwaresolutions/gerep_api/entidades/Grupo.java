package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "grupos")
@Data
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "profesor_id", unique = true)
    private Profesor profesor;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "grupo_id")
    private List<Alumno> alumnos = new ArrayList<>();
}
