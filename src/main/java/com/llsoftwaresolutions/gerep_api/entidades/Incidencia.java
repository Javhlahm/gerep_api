package com.llsoftwaresolutions.gerep_api.entidades;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "incidencias")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private LocalDate fecha;
    private String status;
    private String justificante;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Alumno alumno;
}
