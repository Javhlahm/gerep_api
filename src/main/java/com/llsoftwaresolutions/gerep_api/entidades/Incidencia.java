package com.llsoftwaresolutions.gerep_api.entidades;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    private String status;
    private String justificante;

}
