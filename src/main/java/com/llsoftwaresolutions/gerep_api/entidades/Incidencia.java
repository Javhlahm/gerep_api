package com.llsoftwaresolutions.gerep_api.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "incidencias")
@Data
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private LocalDate fecha;
    private String status;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;
}
