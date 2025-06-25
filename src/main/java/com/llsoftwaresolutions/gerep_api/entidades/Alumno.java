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
    @Lob
    private String foto;
    private String historialMedico;
    private String tipoSangre;
    private String cuidadosEspeciales;

    private String contactoEmergencia;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "alumno_id")
    private List<Incidencia> incidencias;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "alumno_id")
    private List<Asistencia> asistencias;

    @Column(unique = true)
    private String tagUid;
}
