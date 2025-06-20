package com.llsoftwaresolutions.gerep_api.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "alumnos")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // poner esto siempre que
                                                                                           // vaya a haber relaciones
                                                                                           // para que no se cicle
                                                                                           // infinitamente
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    @Lob
    private String foto;
    private String historialMedico;
    private String tipoSangre;
    private String cuidadosEspeciales;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    @JsonIdentityReference(alwaysAsId = true) // poner esto siempre en un atributo con relacion para que no se cicle
    private Grupo grupo;

    @ManyToMany
    @JoinTable(name = "alumno_padre", joinColumns = @JoinColumn(name = "alumno_id"), inverseJoinColumns = @JoinColumn(name = "padre_id"))
    @JsonIdentityReference(alwaysAsId = true) // poner esto siempre en un atributo con relacion para que no se cicle
    private List<Padre> padres;

    private String contactoEmergencia;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true) // poner esto siempre en un atributo con relacion para que no se cicle
    private List<Incidencia> incidencias;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Asistencia> asistencias;

    @Column(unique = true)
    private String tagUid;
}
