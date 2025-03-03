package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private String foto;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    @ManyToMany
    @JoinTable(name = "alumno_padre", joinColumns = @JoinColumn(name = "alumno_id"), inverseJoinColumns = @JoinColumn(name = "padre_id"))
    private List<Padre> padres;

    @ManyToOne
    @JoinColumn(name = "contacto_emergencia_id")
    private ContactoEmergencia contactoEmergencia;

    @OneToMany(mappedBy = "alumno")
    @Column(unique = true)
    private List<Incidencia> incidencias;

    @OneToOne(mappedBy = "alumno", cascade = CascadeType.ALL)
    private HistorialMedico historialMedico;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;
}
