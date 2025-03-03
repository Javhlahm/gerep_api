package com.llsoftwaresolutions.gerep_api.entidades;

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

    @OneToOne
    @JoinColumn(name = "profesor_id", unique = true)
    private Profesor profesor;

    @OneToMany(mappedBy = "grupo")
    private List<Alumno> alumnos;
}
