package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "grupos")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Director director;

    @OneToOne
    @JoinColumn(name = "profesor_id", unique = true)
    @JsonIdentityReference(alwaysAsId = true)
    private Profesor profesor;

    @OneToMany(mappedBy = "grupo")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Alumno> alumnos;
}
