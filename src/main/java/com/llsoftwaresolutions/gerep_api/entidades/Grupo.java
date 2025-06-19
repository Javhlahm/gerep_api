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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //poner esto siempre que vaya a haber relaciones para que no se cicle infinitamente

public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @OneToOne
    @JoinColumn(name = "profesor_id", unique = true)
    @JsonIdentityReference(alwaysAsId = true) //poner esto siempre en un atributo con relacion para que no se cicle infinitamente
    private Profesor profesor;

    @OneToMany(mappedBy = "grupo")
    @JsonIdentityReference(alwaysAsId = true) //poner esto siempre en un atributo con relacion para que no se cicle infinitamente
    private List<Alumno> alumnos;
}
